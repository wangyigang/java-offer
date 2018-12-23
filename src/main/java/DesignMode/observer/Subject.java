package DesignMode.observer;

import java.util.ArrayList;
import java.util.List;


/*
观察者在对象处于一对多的情况下进行使用

subject:注册观察对象
observer:观察者，如果发生变化，就将改变返回给
 */
public class Subject {
    //成员变量--list里面存放被注册的列表
    private List<Observer> list = new ArrayList<>();
    private int state ;
    //获取状态
    public int getState(){
        return state;
    }
    //设置状态
    public void setState(int state){
        this.state = state;
        //一旦状态发生改变，就通知所有人
        notifyAllObserbers();
    }
    //进行注册
    public void  attach(Observer ob){
        list.add(ob);
    }
    //一旦注册消息的时间发生改变，就通知所有注册人员
    public void notifyAllObserbers(){
        for (Observer observer : list) {
            observer.update();
        }
    }
}
