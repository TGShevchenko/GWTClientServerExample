package com.tshevchenko.client;

import com.tshevchenko.shared.History;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.Date;
/**
 * The HistoryService interface
 * 
 * @author Taras Shevchenko, tgshevchenko@gmail.com
 * 
 */
@RemoteServiceRelativePath("services/historyService")
public interface HistoryService extends RemoteService {
    public History addHistory(String position, String department, 
            Date assignDate, Integer isCurrent, Long employeeFK);
    public History updateHistory(Long historyId, String position, String department);
    public Boolean deleteHistory(Long historyId);
}
