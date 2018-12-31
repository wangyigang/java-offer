package day1230;

import org.apache.flume.Context;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.PollableSource;
import org.apache.flume.channel.ChannelProcessor;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.SimpleEvent;
import org.apache.flume.source.AbstractSource;

/*
使用flume接收数据，并给每条数据添加前缀，输出到控制台。前缀可从flume配置文件中配置。
prefix--前缀
 */
public class Mysource extends AbstractSource implements Configurable, PollableSource {
    private String preffix ;
    private long sleepTime;
    @Override
    public Status process() throws EventDeliveryException {
       //首先获取数据，这里自己造一些假数据
        SimpleEvent event = new SimpleEvent();
        for(int i=0; i<5; i++){
            //尝试不设置headers
            //event.setHeaders();
            event.setBody((preffix+i).getBytes()); //前缀+i
            //传递给channel
            ChannelProcessor processor = getChannelProcessor();
            processor.processEvent(event);

            //sleep一段时间
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return Status.BACKOFF;
            }
        }
        return Status.READY;
    }

    //先不用处理
    @Override
    public long getBackOffSleepIncrement() {
        return 0;
    }
    //暂不处理
    @Override
    public long getMaxBackOffSleepInterval() {
        return 0;
    }

    @Override
    public void configure(Context context) {
        preffix = context.getString("preffix", "rebanvshen:");
        sleepTime = context.getLong("sleepTime",2000L);
    }
}
