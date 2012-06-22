package com.tshevchenko.client;

import com.tshevchenko.shared.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.Date;

/**
 * The HistoryServiceAsync interface
 * 
 * @author Taras Shevchenko, tgshevchenko@gmail.com
 * 
 */
public interface HistoryServiceAsync {
    public void addHistory(String position, String department, 
            Date assignDate, Integer isCurrent, 
            Long employeeFK, AsyncCallback<History> callback);
    public void updateHistory(Long historyId, String position, String department, 
            AsyncCallback<History> callback);
    public void deleteHistory(Long historyId,
            AsyncCallback<Boolean> callback);
}
