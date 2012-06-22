package com.tshevchenko.client.ui;

import com.tshevchenko.client.SimpleTask;
/**
 * The Notifier class
 * 
 * @author Taras Shevchenko, tgshevchenko@gmail.com
 * 
 */
public class Notifier<T>{
    private SimpleTask task;
    public void addObserver(SimpleTask task){
        this.task = task;
    }
    public void notifyClients(T info){
        if(task != null){
            task.updateWatcher(info);
        }
    }
}