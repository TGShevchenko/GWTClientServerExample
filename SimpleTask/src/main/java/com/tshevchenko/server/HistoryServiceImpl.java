package com.tshevchenko.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tshevchenko.client.HistoryService;
import com.tshevchenko.client.InfoService;
import com.tshevchenko.server.dao.HistoryDao;
import com.tshevchenko.shared.History;
import java.util.Date;
/**
 * The HistoryServiceImpl class 
 * 
 * @author Taras Shevchenko, tgshevchenko@gmail.com
 * 
 */
@Service("historyService")
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    HistoryDao historyDao;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public History addHistory(String position, String department,
            Date assignDate, Integer isCurrent, Long employeeFK) {
        System.out.println("HistoryServiceImpl::addHistory()");
        History historyPersisted = null;
        try {
            historyPersisted = historyDao.persist(new History(position,
                    department, assignDate, isCurrent, employeeFK));
        } catch (Exception e) {
            System.out.println("HistoryServiceImpl::saveHistory is FAILED: " + e);
        }
        return historyPersisted;
    }
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public History updateHistory(Long historyId, String position, String department) {
        System.out.println("HistoryServiceImpl::updateHistory()");
        History historyPersisted = null;
        try {
            historyPersisted = historyDao.findById(historyId);
            if(historyPersisted != null){
                historyPersisted.setPosition(position);
                historyPersisted.setDepartment(department);                
                historyPersisted = historyDao.merge(historyPersisted);                
            }
        } catch (Exception e) {
            System.out.println("HistoryServiceImpl::updateHistory is FAILED: " + e);
        }
        return historyPersisted;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Boolean deleteHistory(Long historyId) {
        System.out.println("HistoryServiceImpl::deleteHistory()");
        History historyPersisted = null;
        try {
            historyPersisted = historyDao.findById(historyId);
            if(historyPersisted != null){
                historyDao.remove(historyPersisted);                
            }
            return true;
        } catch (Exception e) {
            System.out.println("HistoryServiceImpl::deleteHistory is FAILED: " + e);
        }
        return false;
    }    
}
