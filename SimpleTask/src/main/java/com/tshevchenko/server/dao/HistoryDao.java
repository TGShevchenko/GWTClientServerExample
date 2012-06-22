package com.tshevchenko.server.dao;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tshevchenko.shared.History;
import com.tshevchenko.server.dao.JpaDao;
/**
 * The HistoryDao class 
 * 
 * @author Taras Shevchenko, tgshevchenko@gmail.com
 * 
 */
@Repository("historyDAO")
public class HistoryDao extends JpaDao<Long, History>{
    @Autowired
    EntityManagerFactory entityManagerFactory;
 	
    @PostConstruct
    public void init() {
        super.setEntityManagerFactory(entityManagerFactory);
    }
}
