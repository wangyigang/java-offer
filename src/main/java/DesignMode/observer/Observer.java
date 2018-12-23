package DesignMode.observer;

public abstract  class Observer {
    protected Subject subject = new Subject();
    protected abstract  void update();
}




