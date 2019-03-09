package day1230;

import org.apache.flume.Context;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.PollableSource;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.SimpleEvent;
import org.apache.flume.source.AbstractSource;

import java.util.HashMap;


/*
    总结：事件含有两部分信息 头信息和body信息，  头信息是一个map bidy是一个字节数组
 */
public class Mysource2 extends AbstractSource implements Configurable, PollableSource {
    private Long delay ;
    private String field;

    /**
     *
     * @return
     * @throws EventDeliveryException
     */
    @Override
    public Status process() throws EventDeliveryException {
        //创建事件头信息
        HashMap<String, String> hashMap = new HashMap<>();
        //创建事件
        SimpleEvent event= new SimpleEvent();
        //循环封装事件
        for(int i=0; i<5; i++){
            //给事件设置头信息
            event.setHeaders(hashMap);
            //给事件设置内容
            event.setBody((field+i).getBytes());
            //将事件写入channel
            getChannelProcessor().processEvent(event);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return Status.BACKOFF;  //返回状态信息，回滚
            }
        }

        return Status.READY; //正常信息
    }

    @Override
    public long getBackOffSleepIncrement() {
        return 0;
    }

    @Override
    public long getMaxBackOffSleepInterval() {
        return 0;
    }

    /**
     *  获取配置信息--通过context上下文获取配置信息
     * @param context
     */
    @Override
    public void configure(Context context) {
        context.getLong("delay");
        context.getString("field","hello");
    }
}
