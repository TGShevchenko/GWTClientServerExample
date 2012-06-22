package com.tshevchenko.client;

import com.tshevchenko.shared.Employee;
import com.tshevchenko.shared.History;

import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.Date;
import java.util.List;

/**
 * The EmployeeServiceAsync interface
 * 
 * @author Taras Shevchenko, tgshevchenko@gmail.com
 * 
 */
public interface EmployeeServiceAsync {
    public void addEmployee(String login, String passwd, 
                              String firstName, String lastName, 
                              String address, String birthDate,
                              Integer isAdmin, 
                              AsyncCallback<Employee> callback);
    public void updateEmployee(Long employeeId, 
            String firstName, String lastName, 
            String address, String birthDate,
            AsyncCallback<Employee> callback);
    public void deleteEmployee(Long employeeId,
            AsyncCallback<Boolean> callback);
    public void getEmployeeDetails(String login, 
                              AsyncCallback<Employee> callback);    
    public void getEmployeeDetails(String login, String password, 
                              AsyncCallback<Employee> callback);
    public void getEmployeeHistory(Long employeeId, 
            AsyncCallback<List<History>> callback);
}
