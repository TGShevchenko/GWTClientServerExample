package com.tshevchenko.shared;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;

/**
 * The Employee class
 * 
 * @author Taras Shevchenko, tgshevchenko@gmail.com
 * 
 */
@Entity
@Table(name = "employee")
public class Employee implements java.io.Serializable {	
    private static final long serialVersionUID = 1L;
    
    ///// Two lines below are for Oracle 
    //@SequenceGenerator(name="CUSTOMER_SEQUENCE_GENERATOR",sequenceName="CUSTOMER_SEQ")                 
    //@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CUSTOMER_SEQUENCE_GENERATOR")	   
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long employeeId;
	   
    @Column(name="firstName", nullable = false, length=200)
    private String firstName;
	    
    @Column(name="lastName", nullable = false, length=200)
    private String lastName;

    @Column(name="address", length=200)
    private String address;
    
    @Column(name="birthDate")
    private String birthDate;

    @Column(name="login", length=64)
    private String login;

    @Column(name="password", length=64)
    private String password;
    
    @Column(name="isAdmin")
    private Integer isAdmin;

    public Employee() {			
    }

    public Employee(String login, String password, 
                     String firstName, String lastName, 
                     String address, String birthDate,
                     Integer isAdmin) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.address = address;
        this.birthDate = birthDate;
        this.isAdmin = isAdmin;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }       
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }    

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }    
}
