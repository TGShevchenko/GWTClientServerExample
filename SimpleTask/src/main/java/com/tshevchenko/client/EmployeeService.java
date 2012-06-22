package com.tshevchenko.client;

import com.tshevchenko.shared.Employee;
import com.tshevchenko.shared.History;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.Date;
import java.util.List;
/**
 * The EmployeeService class
 * 
 * @author Taras Shevchenko, tgshevchenko@gmail.com
 * 
 */
@RemoteServiceRelativePath("services/employeeService")
public interface EmployeeService extends RemoteService {
    public Employee addEmployee(String login, String password, 
                                  String firstName, String lastName, 
                                  String address, String birthDate,
                                  Integer isAdmin);
    public Employee updateEmployee(Long employeeId, 
            String firstName, String lastName, 
            String address, String birthDate);
    public Boolean deleteEmployee(Long employeeId);
    public Employee getEmployeeDetails(String login);
    public Employee getEmployeeDetails(String login, String password);
    public List<History> getEmployeeHistory(Long employeeId);
}
