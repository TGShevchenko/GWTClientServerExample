package com.tshevchenko.client.ui;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;

import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionModel.AbstractSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent;

import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.Random;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Window;

import java.util.Date;
import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;

import com.tshevchenko.client.ui.IDataModelInfo;
import com.tshevchenko.client.ui.Notifier;

/**
 * The PaginatedDataGrid abstract class that represents a basic paginating 
 * and some notifications
 * 
 * @author Taras Shevchenko, tgshevchenko@gmail.com
 * 
 */
abstract class PaginatedDataGrid<T> extends Composite{
    private DataGrid<T> dataGrid;
    private SimplePager pager;
    private String height;
    private String width;
    private ListDataProvider<T> dataProvider;
    private List<T> dataList;
    private DockPanel dock = new DockPanel();
    private T selectedRow = null; 
    Notifier<T> notifier = new Notifier<T>();
    public PaginatedDataGrid(IDataModelInfo modelInfo) {
        initWidget(dock);
        dataGrid = new DataGrid<T>();
        dataGrid.setWidth("90%");

        SimplePager.Resources pagerResources = GWT
                .create(SimplePager.Resources.class);
        pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0,
                true);
        pager.setDisplay(dataGrid);
        dataProvider = new ListDataProvider<T>();
        dataProvider.setList(new ArrayList<T>());
        dataGrid.setEmptyTableWidget(new HTML("No Data to Display"));
        ListHandler<T> sortHandler = new ListHandler<T>(dataProvider.getList());

        initTableColumns(dataGrid, sortHandler, modelInfo);

        dataGrid.addColumnSortHandler(sortHandler);

        dataProvider.addDataDisplay(dataGrid);
        pager.setVisible(true);
        dataGrid.setVisible(true);

        dock.add(dataGrid, DockPanel.CENTER);
        dock.add(pager, DockPanel.SOUTH);
        dock.setWidth("100%");
        dock.setCellWidth(dataGrid, "100%");
        dock.setCellWidth(pager, "100%");

        // Add a selection model to handle user selection.
        final SingleSelectionModel<T> selectionModel = new SingleSelectionModel<T>();
        dataGrid.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
          public void onSelectionChange(SelectionChangeEvent event) {
              selectedRow = selectionModel.getSelectedObject();
              notifier.notifyClients(selectedRow);              
          }
        });
    }
    public void setEmptyTableWidget() {
        dataGrid.setEmptyTableWidget(new HTML(
                "The current request has taken longer than the allowed time limit. " +
                "Please try your report query again."));
    }

    /**
     * 
     * Abstract Method to implements for adding Column into Grid
     * 
     * @param dataGrid
     * @param sortHandler
     */
    public abstract void initTableColumns(DataGrid<T> dataGrid,
            ListHandler<T> sortHandler, 
            IDataModelInfo modelInfo);

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
        dataGrid.setHeight(height);
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
        dataGrid.setWidth(width);
    }
    
    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
    	dataProvider.setList(new ArrayList<T>());
    	//dataGrid.setEmptyTableWidget(new HTML("No Data to Display"));
        this.dataList = dataList;
        List<T> list = dataProvider.getList();
        list.addAll(this.dataList);
        dataProvider.refresh();
    }

    public ListDataProvider<T> getDataProvider() {
        return dataProvider;
    }

    public void setDataProvider(ListDataProvider<T> dataProvider) {
        this.dataProvider = dataProvider;
    }

    public T getSelectedRow(){
        return selectedRow;
    }
    public Notifier getNotifier(){
        return notifier;
    }
}

/**
 * TRGrid extends PagingDataGrid
 * 
 * @author Taras Shevchenko
 * 
 * @param <T>
 */
public class TRGrid<T> extends PaginatedDataGrid<T> {
    public TRGrid(IDataModelInfo modelInfo) {
        super(modelInfo);    
    }
    
    @Override
    public void initTableColumns(DataGrid<T> dataGrid,
            ListHandler<T> sortHandler, 
            IDataModelInfo modelInfo) {
        for( final String colName : modelInfo.getNames() ){
            Column<T, String> aColumn = new Column<T, String>(
                    new TextCell()) {
                @Override
                public String getValue(T object) {
                    return ((IDataModelInfo) object).getValue(colName);
                }
            };
            aColumn.setSortable(true);
            sortHandler.setComparator(aColumn, new Comparator<T>() {
                public int compare(T o1, T o2) {
                    return ((IDataModelInfo) o1).getValue(colName).compareTo(
                            ((IDataModelInfo) o2).getValue(colName));
                }
            });
            dataGrid.addColumn(aColumn, modelInfo.getTitle(colName));
            dataGrid.setColumnWidth(aColumn, 20, Unit.PCT);
        }
    }

    public void setData(List<T> dataList){
    	setDataList(dataList);
    }
}