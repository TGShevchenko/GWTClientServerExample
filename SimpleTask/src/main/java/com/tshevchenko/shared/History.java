package com.tshevchenko.shared;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;
/**
 * The History class 
 * 
 * @author Taras Shevchenko, tgshevchenko@gmail.com
 * 
 */
@Entity
@Table(name = "history")
public class History implements java.io.Serializable { 
    private static final long serialVersionUID = 2L;
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long historyId;
       
    @Column(name="position", nullable = false, length=200)
    private String position;
        
    @Column(name="department", length=200)
    private String department;

    @Column(name="assignDate")
    private Date assignDate;
    
    @Column(name="isCurrent")
    private Integer isCurrent;

    @Column(name="employeeId")
    private Long employeeId;    
    
    public History() {         
    }

    public History(String position, String department, 
                    Date assignDate, Integer isCurrent, 
                    Long employeeId) {
        super();
        this.position = position;
        this.department = department;
        this.assignDate = assignDate;
        this.isCurrent = isCurrent; 
        this.employeeId = employeeId;
    }

    public long getHistoryId() {
        return historyId;
    }

    public void setHistoryId(long historyId) {
        this.historyId = historyId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }       

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }   
    
    public Integer getIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(Integer isCurrent) {
        this.isCurrent = isCurrent;
    }    
    
    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }    
        
}
