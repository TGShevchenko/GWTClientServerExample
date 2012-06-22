package com.tshevchenko.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface InfoServiceAsync {	
    public void helloWorld(String name, AsyncCallback<String> callback);
}
