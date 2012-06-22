package com.tshevchenko.client;

import com.tshevchenko.shared.Employee;
import com.tshevchenko.shared.History;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.CheckBox;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;

import com.google.gwt.i18n.client.Constants;

import com.google.gwt.user.client.Window;
import com.tshevchenko.client.ui.TRGrid;
import com.tshevchenko.client.ui.EmployeeInfo;
import com.tshevchenko.client.ui.HistoryInfo;
import com.tshevchenko.client.ui.Notifier;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * The Simple Task main class
 * 
 * @author Taras Shevchenko, tgshevchenko@gmail.com
 * 
 */
public class SimpleTask implements EntryPoint {

    private final EmployeeServiceAsync employeeServiceAsync = GWT
            .create(EmployeeService.class);
    private final HistoryServiceAsync historyServiceAsync = GWT
            .create(HistoryService.class);

    final Label personalLabel = new Label("Details:");
    final Label historyLabel = new Label("History:");

    // Objects to define data grid contents
    final EmployeeInfo eInfo = new EmployeeInfo();
    final HistoryInfo hInfo = new HistoryInfo();

    // Panel that includes login and sign up fields and buttons
    final HorizontalPanel loginPanel = new HorizontalPanel();

    // Panel that includes data grid to show an employee's personal details
    final HorizontalPanel personalDataPanel = new HorizontalPanel();

    // Panel that includes fields and buttons to manipulate with employee's
    // personal details
    final HorizontalPanel detailsOptionsPanel = new HorizontalPanel();

    // Panel that includes data grid to show an employee's history
    final HorizontalPanel historyPanel = new HorizontalPanel();

    // Panel that includes fields and buttons to manipulate with employee's
    // history
    final HorizontalPanel historyOptionsPanel = new HorizontalPanel();

    final Label loginLabel = new Label("User:");
    final Label passwdLabel = new Label("Password:");

    final CheckBox managerFlag = new CheckBox("Register as a manager");
    final Label loggedAsLabel = new Label("Logged as:");
    final Button loginButton = new Button("Login");
    final Button signUpButton = new Button("Sign up");

    final Button retrieveDetailsButton = new Button("Retrieve Details");
    final Button modifyDetailsButton = new Button("Modify Details");
    final Button deleteDetailsButton = new Button("Delete Card");

    final Label firstNameLabel = new Label("First name:");
    final Label lastNameLabel = new Label("Last name:");
    final Label addressLabel = new Label("Address:");
    final Label birthDateLabel = new Label("Date of birth:");

    final TextBox firstNameField = new TextBox();
    final TextBox lastNameField = new TextBox();
    final TextBox addressField = new TextBox();
    final TextBox birthDateField = new TextBox();

    final Button assignPositionButton = new Button("Assign position");
    final Button deletePositionButton = new Button("Delete position");
    final Label positionLabel = new Label("Position:");
    final Label departmentLabel = new Label("Department:");
    final TextBox departmentField = new TextBox();
    final TextBox positionField = new TextBox();
    final TextBox loginField = new TextBox();
    final TextBox passwdField = new TextBox();

    final TRGrid<EmployeeInfo> personalGrid = new TRGrid<EmployeeInfo>(eInfo);
    final TRGrid<HistoryInfo> historyGrid = new TRGrid<HistoryInfo>(hInfo);
    final int lspace = 20;
    final String relativeWidth = "100%";
    final String gridDetailsHeightPix = "100px";
    final String gridHistoryHeightPix = "200px";

    Long employeeIdLogIn = 0L;
    Employee employeeLogIn = null;

    Long employeeIdHistory = 0L;
    Employee employeeHistory = null;

    Long historyId = 0L;
    History history = null;

    Notifier personalNotifier;
    Notifier historyNotifier;
 
    enum Operation {
        NO_OPERATION, LOGIN, SIGNUP, GET_DETAILS, GET_HISTORY, SAVE_DETAILS, SAVE_HISTORY

    }

    Operation operation = Operation.NO_OPERATION;

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        personalNotifier = personalGrid.getNotifier();
        historyNotifier = historyGrid.getNotifier();
        
        personalGrid.setHeight(gridDetailsHeightPix);
        historyGrid.setHeight(gridHistoryHeightPix);

        loginPanel.setSpacing(lspace);
        loginPanel.add(loginLabel);
        loginPanel.add(loginField);
        loginPanel.add(passwdLabel);
        loginPanel.add(passwdField);
        loginPanel.add(loginButton);
        loginPanel.add(signUpButton);
        loginPanel.add(managerFlag);        
        detailsOptionsPanel.setSpacing(lspace);

        detailsOptionsPanel.add(retrieveDetailsButton);
        detailsOptionsPanel.add(firstNameLabel);
        firstNameField.setWidth(relativeWidth);
        detailsOptionsPanel.add(firstNameField);
        detailsOptionsPanel.add(lastNameLabel);
        lastNameField.setWidth(relativeWidth);
        detailsOptionsPanel.add(lastNameField);
        detailsOptionsPanel.add(addressLabel);
        addressField.setWidth(relativeWidth);
        detailsOptionsPanel.add(addressField);
        detailsOptionsPanel.add(birthDateLabel);
        birthDateField.setWidth(relativeWidth);
        detailsOptionsPanel.add(birthDateField);

        detailsOptionsPanel.add(modifyDetailsButton);
        detailsOptionsPanel.add(deleteDetailsButton);

        historyOptionsPanel.setSpacing(lspace);
        historyOptionsPanel.add(positionLabel);
        historyOptionsPanel.add(positionField);
        historyOptionsPanel.add(departmentLabel);
        historyOptionsPanel.add(departmentField);
        historyOptionsPanel.add(assignPositionButton);
        historyOptionsPanel.add(deletePositionButton);

        personalDataPanel.setSpacing(lspace);
        personalDataPanel.add(personalLabel);
        personalDataPanel.add(personalGrid);
        historyPanel.setSpacing(lspace);
        historyPanel.add(historyLabel);
        historyPanel.add(historyGrid);

        loginPanel.setWidth(relativeWidth);
        personalDataPanel.setWidth(relativeWidth);
        detailsOptionsPanel.setWidth(relativeWidth);
        historyPanel.setWidth(relativeWidth);
        historyOptionsPanel.setWidth(relativeWidth);

        RootPanel.get().add(loggedAsLabel);
        RootPanel.get().add(loginPanel);
        RootPanel.get().add(personalDataPanel);
        RootPanel.get().add(detailsOptionsPanel);
        RootPanel.get().add(historyPanel);
        RootPanel.get().add(historyOptionsPanel);

        loginButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                processLogin();
            }
        });

        signUpButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                processSignUp();
            }
        });

        retrieveDetailsButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                processRetrieveDetails();
            }
        });

        modifyDetailsButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                processModifyDetails();
            }
        });

        deleteDetailsButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                processDeleteDetails();
            }
        });

        assignPositionButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                processAssignPosition();
            }
        });

        deletePositionButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                processDeletePosition();
            }
        });
        
        personalNotifier.addObserver(this);        
        historyNotifier.addObserver(this);       
    }

    public void updateWatcher(Object arg) {
        if(arg != null){
            if(arg instanceof HistoryInfo){
                historyId = ((HistoryInfo)arg).getId(); 
                System.out.println("updateWatcher() has been called from History, id is " + historyId);
                departmentField.setText(((HistoryInfo)arg).getValue("department"));
                positionField.setText(((HistoryInfo)arg).getValue("position"));
            }
            if(arg instanceof EmployeeInfo){
                firstNameField.setText(((EmployeeInfo)arg).getValue("firstName"));
                lastNameField.setText(((EmployeeInfo)arg).getValue("lastName"));
                addressField.setText(((EmployeeInfo)arg).getValue("address"));
                birthDateField.setText(((EmployeeInfo)arg).getValue("birthDate"));                
            }
        }
    } 
    
    private void processLogin() {
        operation = Operation.LOGIN;
        final String login = loginField.getText();
        final String password = passwdField.getText();
        if (login.isEmpty() || password.isEmpty()) {
            Window.alert("Your login and password should be provided!");
        } else {
            employeeIdLogIn = 0L;
            employeeIdHistory = 0L;
            employeeLogIn = null;
            employeeHistory = null;
            getEmployeeDetails();
        }
    }

    private void processSignUp() {
        operation = Operation.SIGNUP;
        final String login = loginField.getText(); // "taras";
        final String password = passwdField.getText(); // "pswd";
        if (login.isEmpty() || password.isEmpty()) {
            Window.alert("Your login and password should be provided!");
        } else {
            getEmployeeDetails();
        }
    }

    private void processRetrieveDetails() {
        operation = Operation.GET_DETAILS;
        final String login = loginField.getText();
        if (login.isEmpty()) {
            Window.alert("A login should be provided!");
        } else {
            if(checkPrivileges()){
                getEmployeeDetails();
            }else{
                Window.alert("There are no sufficient privileges for this operation");
            }
        }
    }

    private void processModifyDetails() {
        final String firstName = firstNameField.getText();
        final String lastName = lastNameField.getText();
        final String address = addressField.getText();
        final String birthDate = birthDateField.getText();
        if (employeeHistory == null) {
            employeeHistory = employeeLogIn;
        }   
        if(employeeHistory == null){
            Window.alert("An employee should be provided! Please, retrieve the details at first.");
            return;            
        }
        final Long employeeID = employeeHistory.getEmployeeId();
        operation = Operation.SAVE_DETAILS;
        employeeServiceAsync.updateEmployee(employeeID, firstName, lastName,
                address, birthDate, new AsyncCallback<Employee>() {

                    public void onFailure(Throwable caught) {
                        System.out.println("onFailure... " + caught);
                        Window.alert("Saving an employee has failed: " + caught);
                        getEmployeeDetails();
                    }

                    public void onSuccess(Employee result) {
                        System.out.println("onSuccess...");                     
                        fillPersonalData(result);
                        getEmployeeHistory();
                    }
                });
    }

    private void processDeleteDetails() {
        EmployeeInfo selectedRow = personalGrid.getSelectedRow();
        if(selectedRow == null){
            Window.alert("A detail line should be selected");
            return;
        }
        if(!checkPrivileges()){
            Window.alert("There are no sufficient privileges for this operation:" + selectedRow.getId());
        }       
        
        if (employeeHistory == null) {
            employeeHistory = employeeLogIn;
        }   
        if(employeeHistory == null){
            Window.alert("An employee should be provided! Please, retrieve the details at first.");
            return;            
        }
        final Long employeeID = employeeHistory.getEmployeeId();
        operation = Operation.GET_DETAILS;
        employeeServiceAsync.deleteEmployee(employeeID, new AsyncCallback<Boolean>() {
                    public void onFailure(Throwable caught) {
                        System.out.println("onFailure... " + caught);
                        Window.alert("Deleting an employee has failed: " + caught);
                        getEmployeeDetails();
                    }

                    public void onSuccess(Boolean result) {
                        if(!result){
                            Window.alert("Deleting an employee has failed");                            
                        }else{
                            System.out.println("onSuccess...");
                        }                      
                        getEmployeeDetails();
                    }
                });        
    }
  
    /*
     * Method checks for privileges that a logged in user has got
     */
    private boolean checkPrivileges(){
        boolean isPrivileged = false;
        if(employeeLogIn != null){
            Integer isManager = employeeLogIn.getIsAdmin();
            if((isManager != null) && ( 1 == isManager )){
                isPrivileged = true;
            }
        }
        return isPrivileged;
    }
    /*
     * Method to add or modify a history line
     */
    private void processAssignPosition() {
        if(!checkPrivileges()){
            Window.alert("There are no sufficient privileges for this operation");
            return;
        }           
        String position = positionField.getText();
        String department = departmentField.getText();
        if (employeeHistory == null) {
            Window.alert("An employee should be provided! Please, retrieve the details at first.");
            return;
        }
        if (position.isEmpty() || department.isEmpty()) {
            Window.alert("A position and department should be provided!");
            return;
        }
        
        operation = Operation.SAVE_HISTORY;
        Date assignDate = new Date();
        Long employeeFK = employeeHistory.getEmployeeId();
        Integer isCurrent = 1;
        historyServiceAsync.addHistory(position, department, assignDate,
                isCurrent, employeeFK, new AsyncCallback<History>() {

                    public void onFailure(Throwable caught) {
                        System.out.println("onFailure... " + caught);
                        getEmployeeHistory();
                    }

                    public void onSuccess(History result) {
                        System.out.println("onSuccess...");
                        getEmployeeHistory();
                    }
                });
    }

    private void processDeletePosition() {
        HistoryInfo selectedRow = historyGrid.getSelectedRow();
        if(selectedRow == null){
            Window.alert("A history line should be selected!");
            return;
        }
        if(!checkPrivileges()){
            Window.alert("There are no sufficient privileges for this operation!");
            return;
        }
        final Long historyID = selectedRow.getId();
        operation = Operation.GET_DETAILS;
        historyServiceAsync.deleteHistory(historyID, new AsyncCallback<Boolean>() {
                    public void onFailure(Throwable caught) {
                        System.out.println("onFailure... " + caught);
                        Window.alert("Deleting a history has failed: " + caught);
                        getEmployeeDetails();
                    }

                    public void onSuccess(Boolean result) {
                        if(!result){
                            Window.alert("Deleting a history has failed");                            
                        }else{
                            System.out.println("onSuccess...");
                        }                      
                        getEmployeeHistory();
                    }
                }); 
    }    
    
    /*
     * Method to check an employee for existance and using him up if there is no
     * one in the database with such login and password
     */
    public void signUpRequest() {
        final String login = loginField.getText();
        final String password = passwdField.getText();

        final String firstName = firstNameField.getText();
        final String lastName = lastNameField.getText();
        final String address = addressField.getText();
        final String birthDate = birthDateField.getText();
        final Integer isAdmin = managerFlag.isChecked() ? 1 : 0;
        operation = Operation.LOGIN;

        employeeServiceAsync.addEmployee(login, password, firstName, lastName,
                address, birthDate, isAdmin, new AsyncCallback<Employee>() {

                    public void onFailure(Throwable caught) {
                        System.out.println("onFailure... " + caught);
                        // Window.alert("Saving an employee has failed: " +
                        // caught);
                        getEmployeeDetails();
                    }

                    public void onSuccess(Employee result) {
                        System.out.println("onSuccess...");
                        Long employeeID = result.getEmployeeId();
                        String employeeSurname = result.getLastName();
                        getEmployeeDetails();
                    }
                });
    }

    /*
     * Method to get an employee's personal details and fill a grid up
     */
    public void getEmployeeDetails() {
        final String login = loginField.getText();
        final String password = passwdField.getText();
        if (operation == Operation.GET_DETAILS) {
            employeeServiceAsync.getEmployeeDetails(login,
                    new AsyncCallback<Employee>() {
                        public void onFailure(Throwable caught) {
                            System.out.println("onFailure... " + caught);
                            Window.alert("Getting employee's details have failed: "
                                    + caught);
                        }

                        public void onSuccess(Employee result) {
                            System.out
                                    .println("getEmployeeDetails::onSuccess...");
                            if (result != null) {
                                employeeIdHistory = result.getEmployeeId();
                                employeeHistory = result;
                                fillPersonalData(result);
                                getEmployeeHistory();
                            } else {
                                Window.alert("There are no records for this user");
                                fillPersonalData(null);
                                fillHistoryData(null);
                            }
                                                        
                        }
                    });
        } else {
            getEmployeeDetailsWithLoginAndPasswordAsynch(login, password);
        }
    }

    private void getEmployeeDetailsWithLoginAndPasswordAsynch(
                    final String login, final String password) {
        if (operation == Operation.LOGIN) {
            employeeServiceAsync.getEmployeeDetails(login, password,
                    new AsyncCallback<Employee>() {
                        public void onFailure(Throwable caught) {
                            System.out.println("onFailure... " + caught);
                            if (operation == Operation.LOGIN) {
                                Window.alert("Getting employee's details has failed: "
                                        + caught);
                            }
                        }

                        public void onSuccess(Employee result) {
                            System.out
                                    .println("getEmployeeDetails::onSuccess...");
                            if (result != null) {
                                employeeIdLogIn = result.getEmployeeId();
                                employeeLogIn = result;
                                fillPersonalData(result);
                                getEmployeeHistory();
                                Integer isAdmin = result.getIsAdmin();                                
                                if((isAdmin != null) && (1 == isAdmin)){
                                    loggedAsLabel.setText("   Logged as: " + login + " , PRIVILEGED");                                    
                                }else{
                                    loggedAsLabel.setText("   Logged as: " + login);                                    
                                }
                            } else {
                                Window.alert("There are no records for this user");
                                fillPersonalData(null);                                
                                fillHistoryData(null);
                            }
                        }
                    });
        } else {
            if (operation == Operation.SIGNUP) {
                employeeServiceAsync.getEmployeeDetails(login,
                        new AsyncCallback<Employee>() {
                            public void onFailure(Throwable caught) {
                                System.out.println("onFailure... " + caught);
                                // signUpRequest();
                                Window.alert("Sing up of an employee has failed: "
                                        + caught);
                            }

                            public void onSuccess(Employee result) {
                                System.out
                                        .println("getEmployeeDetails::onSuccess...");
                                if (result == null) {
                                    signUpRequest();
                                } else {
                                    Window.alert("This employee is already registered. Try to login.");
                                }
                            }
                        });
            }
        }
    }

    /*
     * Method to get an employee's history and fill a grid up
     */
    public void getEmployeeHistory() {
        if (employeeHistory == null) {
            employeeHistory = employeeLogIn;
        }
        employeeServiceAsync.getEmployeeHistory(
                employeeHistory.getEmployeeId(),
                new AsyncCallback<List<History>>() {
                    public void onFailure(Throwable caught) {
                        System.out.println("onFailure... " + caught);
                        Window.alert("Getting employee's history has failed: "
                                + caught);
                    }

                    public void onSuccess(List<History> result) {
                        System.out.println("getEmployeeHistory::onSuccess...");
                        historyId = 0L;
                        history = null;
                        fillHistoryData(result);
                    }
                });
    }

    /*
     * Method fills the personal data grid
     */
    private void fillPersonalData(Employee employee) {
        List<EmployeeInfo> dataList = new ArrayList<EmployeeInfo>();
        EmployeeInfo employeeInfo = new EmployeeInfo();
        if(employee != null){
            employeeInfo.setValue("firstName", employee.getFirstName());
            employeeInfo.setValue("lastName", employee.getLastName());
            employeeInfo.setValue("address", employee.getAddress());
            employeeInfo.setId(employee.getEmployeeId());
            employeeInfo.setValue("birthDate", employee.getBirthDate());
        }
        dataList.add(employeeInfo);
        personalGrid.setData(dataList);
    }

    /*
     * Method fills the history data grid
     */
    private void fillHistoryData(List<History> historyList) {
        List<HistoryInfo> dataList = new ArrayList<HistoryInfo>();
        if(historyList != null){
            for (History historyElement : historyList) {
                HistoryInfo historyInfo = new HistoryInfo();
                historyInfo.setValue("position", historyElement.getPosition());
                historyInfo.setValue("department", historyElement.getDepartment());
                historyInfo.setId(historyElement.getHistoryId());
                Date assignDate = historyElement.getAssignDate();
                if (assignDate != null) {
                    historyInfo.setValue("assignDate", assignDate.toString());
                }
                dataList.add(historyInfo);
            }
        }else{
            dataList.add(new HistoryInfo()); 
        }
        historyGrid.setData(dataList);
    }

}
