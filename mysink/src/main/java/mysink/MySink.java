package mysink;

import org.apache.flume.*;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
使用flume接收数据，并在Sink端给每条数据添加前缀和后缀，
输出到控制台。前后缀可在flume任务配置文件中配置。

 */
public class MySink extends AbstractSink implements Configurable {
    private Logger logger = LoggerFactory.getLogger(MySink.class);
    private String prefix;
    private String suffix;

    @Override
    public Status process() throws EventDeliveryException {
        //获取channel
        Channel channel = getChannel();//获取channel
        //获取事件
        Event event;
        //声明事件
        Transaction transaction = channel.getTransaction();//获取事务
        //开启事务
        transaction.begin();
        //读取channel中的事件--循环读取
        while(true){
            event = channel.take();
            if(event!=null){
                break;
            }
        }
        //事件打印logger
        logger.info(prefix+new String(event.getBody())+suffix);
        //事务提交
        try {
            transaction.commit();
        } catch (Exception e) {
            //回滚事务
            transaction.rollback();
            e.printStackTrace();
            return Status.BACKOFF;
        } finally {
            //关闭事务
            transaction.close();
        }
        return Status.READY;
    }

    /*
    获取配置文件信息
     */
    @Override
    public void configure(Context context) {
        prefix = context.getString("prefix", "dili");
        suffix = context.getString("suffix", "reba");
    }
}
