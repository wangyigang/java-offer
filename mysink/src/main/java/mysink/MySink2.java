package mysink;

import org.apache.flume.*;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySink2 extends AbstractSink implements Configurable {
//    private Logger
    private Long delay;
    private String suffix;
    private  Logger logger;

    @Override
    public Status process() throws EventDeliveryException {
        //声明返回状态信息
        Status status;
        //获取当前sink绑定的channel
        Channel ch = getChannel();

        //获取事务
        Transaction transaction = ch.getTransaction();
        //声明事件
        Event event;
        //开启事务
        transaction.begin();
        //读取channel中的事件
        while(true){
            event = ch.take();
            if(event!= null){
                break;
            }
        }
       logger= LoggerFactory.getLogger(MySink2.class);
        try {
            logger.info(suffix+ new String(event.getBody()));
            //提交事务
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //遇到异常，事务回滚
            transaction.rollback();
            return Status.BACKOFF;
        } finally {
            //关闭事务
            transaction.close();
        }
        return Status.READY;

    }

    @Override
    public void configure(Context context) {
        suffix = context.getString("suffix");
        delay = context.getLong("delay");
    }
}
