package com.tshevchenko.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tshevchenko.client.EmployeeService;
import com.tshevchenko.client.InfoService;
import com.tshevchenko.server.dao.EmployeeDao;
import com.tshevchenko.server.dao.HistoryDao;
import com.tshevchenko.shared.Employee;
import com.tshevchenko.shared.History;
import java.util.Date;
import java.util.List;

/**
 * The EmployeeServiceImpl class
 * 
 * @author Taras Shevchenko, tgshevchenko@gmail.com
 * 
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    HistoryDao historyDao;
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Employee addEmployee(String login, String passwd, String firstName,
            String lastName, String address, String birthDate, Integer isAdmin) {
        System.out.println("EmployeeServiceImpl::saveEmployee()");
        Employee employeePersisted = null;
        try {
            employeePersisted = employeeDao.persist(new Employee(login, passwd,
                    firstName, lastName, address, birthDate, isAdmin));
            System.out.println("EMPLOYEE ID: "
                    + employeePersisted.getEmployeeId());
        } catch (Exception e) {
            System.out.println("EmployeeServiceImpl::saveEmployee is FAILED: "
                    + e);
        }
        return employeePersisted;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Employee updateEmployee(Long employeeId, String firstName, String lastName, 
            String address, String birthDate) {
        System.out.println("EmployeeServiceImpl::updateEmployee()");
        Employee employeePersisted = null;
        try {
            employeePersisted = employeeDao.findById(employeeId);
            if(employeePersisted != null){
                employeePersisted.setFirstName(firstName);
                employeePersisted.setLastName(lastName);  
                employeePersisted.setAddress(address);  
                employeePersisted.setBirthDate(birthDate);                 
                employeePersisted = employeeDao.merge(employeePersisted);                
            }
        } catch (Exception e) {
            System.out.println("EmployeeServiceImpl::updateEmployee is FAILED: " + e);
        }
        return employeePersisted;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Boolean deleteEmployee(Long employeeId) {
        System.out.println("HistoryServiceImpl::deleteEmployee()");
        Employee employeePersisted = null;
        try {
            employeePersisted = employeeDao.findById(employeeId);
            if(employeePersisted != null){
                List<History> historyList = employeeDao.getEmployeeHistory(employeeId);
                for(History history : historyList){
                    historyDao.remove(history);
                }
                employeeDao.remove(employeePersisted);                
            }
            return true;
        } catch (Exception e) {
            System.out.println("EmployeeServiceImpl::deleteEmployee is FAILED: " + e);
        }
        return false;
    }        
    
    public Employee getEmployeeDetails(String login) {
        System.out.println("EmployeeServiceImpl::getEmployeeDetails()");
        Employee employee = employeeDao.findByLogin(login);
        return employee;
    }

    public Employee getEmployeeDetails(String login, String password) {
        System.out.println("EmployeeServiceImpl::getEmployeeDetails()");
        Employee employee = employeeDao.findByLoginAndPassword(login, password);
        return employee;
    }

    public List<History> getEmployeeHistory(Long employeeId) {
        System.out.println("EmployeeServiceImpl::getEmployeeHistory()");
        List<History> history = employeeDao.getEmployeeHistory(employeeId);
        return history;
    }
}
