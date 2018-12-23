package DesignMode.observer;

public class BinaryObserver extends Observer {

    public  BinaryObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    protected void update() {
        System.out.println("binaryObserver changer..."+this.subject.getState());
    }
}
