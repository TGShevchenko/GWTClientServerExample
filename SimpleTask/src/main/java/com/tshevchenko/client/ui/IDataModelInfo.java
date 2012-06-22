package com.tshevchenko.client.ui;
import java.util.List;
/**
 * The IDataModelInfo interface that represents 
 * a very basic set of methods for a data row
 * 
 * @author Taras Shevchenko, tgshevchenko@gmail.com
 * 
 */
public interface IDataModelInfo{
    public List<String> getNames();
    public String getValue(String colName);
    public String getTitle(String colName);    
    public void setValue(String colName, String value);   
    public Long getId();
    public void setId(Long id);
}