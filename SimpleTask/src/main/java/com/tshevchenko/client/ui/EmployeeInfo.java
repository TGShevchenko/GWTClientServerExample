package com.tshevchenko.client.ui;
import com.tshevchenko.client.ui.IDataModelInfo;
import com.google.gwt.view.client.ProvidesKey;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class EmployeeInfo extends GeneralInfo
             implements Comparable<EmployeeInfo> {

    
    public EmployeeInfo() {
    	super();
        colNames.add("firstName");
        colNames.add("lastName");
        colNames.add("address");
        colNames.add("birthDate");
        
        colTitles.put("firstName", "First Name");
        colTitles.put("lastName", "Last Name");
        colTitles.put("address", "Address");
        colTitles.put("birthDate", "Birth Date");
        
        dataMap.put("firstName", "");
        dataMap.put("lastName", "");
        dataMap.put("address", "");
        dataMap.put("birthDate", "");
    }

    public int compareTo(EmployeeInfo o) {
        return (o == null || o.getValue("firstName") == null) ? -1 : -o.getValue("firstName")
                .compareTo(getValue("firstName"));
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof EmployeeInfo) {
            return id == ((IDataModelInfo) o).getId();
        }
        return false;
    }
}
