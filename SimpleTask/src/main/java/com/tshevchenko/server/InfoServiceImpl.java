package com.tshevchenko.server;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tshevchenko.client.EmployeeService;
import com.tshevchenko.client.HistoryService;
import com.tshevchenko.client.InfoService;

@Service("infoService")
public class InfoServiceImpl implements InfoService{
    //@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
    public String helloWorld(String name) {
        System.out.println("InfoServiceImpl => helloWorld()");	
        return name + " from InfoServiceImpl";
    }
}
