package MyFilesource;

import org.apache.commons.io.FileUtils;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.EventDrivenSource;
import org.apache.flume.channel.ChannelProcessor;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.EventBuilder;
import org.apache.flume.source.AbstractSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 自定义source，实现多点续传
 */
public class MyFileSource extends AbstractSource implements Configurable, EventDrivenSource {

    private static final Logger logger = LoggerFactory.getLogger(MyFileSource.class);

    private String filePath;
    private String offsetPath;
    private Long interval;
    private String charset;
    private ExecutorService executor;
    private MyRunnable myRunner;

    public void configure(Context context) {
        filePath = context.getString("filePath");
        offsetPath = context.getString("offsetPath");
        interval = context.getLong("interval", 1000L);
        charset = context.getString("charset", "UTF-8");
    }

    public synchronized void start() {

        //创建一个线程池       |      得到一个Channel对象
        executor = Executors.newSingleThreadExecutor();
        ChannelProcessor channelProcessor = getChannelProcessor();

        //new一个Runnable的对象
        myRunner = new MyRunnable(filePath, offsetPath, interval, charset, channelProcessor);

        //将exector放入线程池中
        executor.execute(myRunner);

        super.start();

    }

    public synchronized void stop() {
        //停掉线程
        myRunner.setFlag(false);
        //停掉线程池
        executor.shutdown();

        while (!executor.isTerminated()) {
            logger.debug("Waiting for exec executor service to stop");
            try {
                executor.awaitTermination(500, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                logger.debug("Interrupted while waiting for exec executor service "
                        + "to stop. Just exiting.");
                Thread.currentThread().interrupt();
            }
        }

        super.stop();
    }

    public static class MyRunnable implements Runnable {

        private String filePath;
        private String offsetPath;
        private Long interval;
        private String charset;
        private ChannelProcessor channelProcessor;

        private Long offset = 0L;
        private File osfile;
        private boolean flag = true;
        private RandomAccessFile raf;

        public MyRunnable(String filePath, String offsetPath, Long interval, String charset, ChannelProcessor channelProcessor) {
            this.filePath = filePath;
            this.offsetPath = offsetPath;
            this.interval = interval;
            this.charset = charset;
            this.channelProcessor = channelProcessor;

            //把偏移量文件装进File对象里
            osfile = new File(offsetPath);

            //判断是否有偏移量文件,如果不存在就创建一个
            if (!osfile.exists()) {
                try {
                    osfile.createNewFile();
                } catch (IOException e) {
                    logger.debug("create osfile error", e);
                }
            }
            //存在的话，判断文件里有没有内容,先得到文件中的内容转为String
            try {
                String offsetStr = FileUtils.readFileToString(osfile);
                //如果有内容的话，就把内容转为Long类型,没有的话就初值
                if (offsetStr != null && !"".equals(offsetStr)) {
                    offset = Long.parseLong(offsetStr);
                }
            } catch (IOException e) {
                logger.debug("read offset error", e);
            }

            //如果有偏移量，就接着读，没有的话就从头读      new一个随机读取文件内容的对象
            try {
                raf = new RandomAccessFile(filePath, "r");
                raf.seek(offset);
            } catch (FileNotFoundException e) {
                logger.debug("file not found error", e);
            } catch (IOException e) {
                logger.debug("read offset error", e);
            }
        }

        public void run() {

            while (flag) {
                //定期读取文件，是否有新内容
                try {
                    String line = raf.readLine();
                    //将数据封装进event对象
                    if (line != null) {
                        Event event = EventBuilder.withBody(line, Charset.forName(charset));
                        //event对象发送给channel
                        channelProcessor.processEvent(event);
                        //获取新的偏移量，再更新偏移量
                        offset = raf.getFilePointer();
                        FileUtils.writeStringToFile(osfile, offset + "");
                    } else {
                        Thread.sleep(interval);
                    }


                } catch (IOException e) {
                    logger.debug("read line error", e);
                } catch (InterruptedException e) {
                    logger.debug("thread sleep error", e);
                }
            }
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }
    }
}