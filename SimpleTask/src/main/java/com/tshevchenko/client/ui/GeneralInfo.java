package com.tshevchenko.client.ui;
import com.tshevchenko.client.ui.IDataModelInfo;
import com.google.gwt.view.client.ProvidesKey;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
/**
 * The GeneralInfo class to generalize a data row in a DataGrid
 * 
 * @author Taras Shevchenko, tgshevchenko@gmail.com
 * 
 */
public class GeneralInfo implements IDataModelInfo {

    public static final ProvidesKey<IDataModelInfo> KEY_PROVIDER = 
            new ProvidesKey<IDataModelInfo>() {
        public Object getKey(IDataModelInfo item) {
            return item == null ? null : item.getId();
        }
    };

    private static Long nextId = 0L;
    protected Long id;
    protected List<String> colNames = new ArrayList<String>();
    protected HashMap<String, String> colTitles = new HashMap<String, String>();
    protected HashMap<String, String> dataMap = new HashMap<String, String>();
    
    public GeneralInfo() {
        this.id = nextId;
        nextId++;
    }

    @Override
    public List<String> getNames(){
        return colNames;
    }
    
    @Override
    public String getValue(String colName){
        return dataMap.get(colName);
    }
    
    @Override
    public String getTitle(String colName){
        return colTitles.get(colName); 
    }
    
    @Override
    public void setValue(String colName, String value){
        dataMap.put(colName, value);
    }
    
    @Override
    public Long getId() {
        return this.id;
    }
    
    @Override
    public void setId(Long id) {
        this.id = id;
    }
    
    @Override
    public int hashCode() {
        return id.intValue();
    }
}
