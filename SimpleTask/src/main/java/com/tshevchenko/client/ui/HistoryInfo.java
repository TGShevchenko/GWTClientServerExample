package com.tshevchenko.client.ui;
import com.tshevchenko.client.ui.IDataModelInfo;
import com.google.gwt.view.client.ProvidesKey;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
/**
 * The HistoryInfo class 
 * 
 * @author Taras Shevchenko, tgshevchenko@gmail.com
 * 
 */
public class HistoryInfo extends GeneralInfo
            implements Comparable<HistoryInfo> {
    
    public HistoryInfo() {
    	super();
        colNames.add("position");
        colNames.add("department");
        colNames.add("assignDate");
        
        colTitles.put("position", "Position");
        colTitles.put("department", "Department");
        colTitles.put("assignDate", "Date of assignment");
        
        dataMap.put("position", "");
        dataMap.put("department", "");
        dataMap.put("assignDate", "");
    }

    public int compareTo(HistoryInfo o) {
        return (o == null || o.getValue("position") == null) ? -1 : -o.getValue("position")
                .compareTo(getValue("position"));
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof HistoryInfo) {
            return id == ((IDataModelInfo) o).getId();
        }
        return false;
    }
}
