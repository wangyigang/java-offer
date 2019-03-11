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
@SuppressWarnings("all")
public class MyFileSource2 extends AbstractSource implements Configurable, EventDrivenSource {
    //模拟实现多点续传功能
    //logger日志
    private static final Logger logger = LoggerFactory.getLogger(MyFileSource2.class);
    //文件路径
    private String filePath;
    //offsetPath --偏移量
    private String offsetPath;
    //interval 间隙
    private Long interval;
    //charset;
    private String charset;
    //juc 线程池
    private ExecutorService executorService;
    //内部类对象引用
    private  FileSourceRunnable runnable;

    /**
     * 获取配置信息
     *
     * @param context
     */
    @Override
    public void configure(Context context) {
        filePath = context.getString("filePath");
        offsetPath = context.getString("offsetPath");
        interval = context.getLong("interval", 1000L);
        charset = context.getString("charset", "UTF-8");
    }

    //start()方法
    @Override
    public synchronized void start() {
        //创建一个线程池，得到一个channel对象
        executorService = Executors.newSingleThreadExecutor();
        //获取channel
        ChannelProcessor channelProcessor = getChannelProcessor();
        //创建一个内部类对象
        runnable= new FileSourceRunnable(filePath, offsetPath, interval, charset, channelProcessor);
        //将executor放入线程池中，进行执行
        executorService.execute(runnable);

        //调用start方法
        super.start();
    }

    /**
     * 关闭方法
     */
    @Override
    public synchronized void stop() {
        //线程停止
        runnable.setFlag(false);
        //停掉线程池
        executorService.shutdown();
        while(!executorService.isTerminated()){
            logger.debug("Waiting for exec executor service to stop");
            try {
                //阻塞知道所有任务完成
                executorService.awaitTermination(500, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                logger.debug("Interrupted while waiting for exec executor service "
                        + "to stop. Just exiting.");
                Thread.currentThread().interrupt();
            }
        }
        super.stop();

    }

    @SuppressWarnings("all")
    //新增一个内部类
    public static class FileSourceRunnable implements Runnable {
        private String filepath;
        private String offsetPath;
        private Long interval;
        private String charset;
        private ChannelProcessor channelProcessor;

        private Long offset = 0L;
        private File osfile;
        private boolean flag = true;
        private RandomAccessFile raf;

        public FileSourceRunnable(String filepath, String offsetPath, Long interval, String charset, ChannelProcessor channelProcessor) {
            this.filepath = filepath;
            this.offsetPath = offsetPath;
            this.interval = interval;
            this.charset = charset;
            this.channelProcessor = channelProcessor;

            //将偏移量文件装进file对象里
            osfile = new File(offsetPath);
            //判断是否有偏移量文件，如果不存在就创建一个
            if (!osfile.exists()) {
                try {
                    osfile.createNewFile();
                } catch (IOException e) {
                    logger.debug("create osfile error", e);
                }
            }
            //如果存在，判断文件里有没有内容，先得到文件中的内容转为string
            try {
                String offsetStr = FileUtils.readFileToString(osfile);
                //判断是否有内容
                if (offsetStr != null && !"".equals(offsetStr)) {
                    offset = Long.parseLong(offsetStr);
                }
            } catch (IOException e) {
                logger.debug("read offset error", e);
            }
            //如果有偏移量，就接着读，没有的话从头读，new 一个随机读取文件内容的对象
            try {
                raf = new RandomAccessFile(filepath, "r");
                raf.seek(offset);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                logger.debug("file not found error", e);
            } catch (IOException e) {
                e.printStackTrace();
                logger.debug("read offset error", e);
            }
        }

        @Override
        public void run() {
            //flag--定期读取文件，判断是否有新内容
            while (flag) {
                try {
                    String line = raf.readLine();
                    //将数据封装成event对象
                    if (line != null) {
                        Event event = EventBuilder.withBody(line, Charset.forName(charset));
                        //event对象发送给channel
                        channelProcessor.processEvent(event);
                        //获取新的偏移量，在更新偏移量
                        offset = raf.getFilePointer(); //获取新的品一辆
                        FileUtils.writeStringToFile(osfile
                                , offset + ""); //转为字符串类型
                    } else {
                        Thread.sleep(interval);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.debug("read line error", e);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    logger.debug("thread sleep error", e);
                }

            }
        }
        //对外提供一个接口，用于设置是否定期读取文件
        public void setFlag(boolean flag) {
            this.flag = flag;
        }
    }
}