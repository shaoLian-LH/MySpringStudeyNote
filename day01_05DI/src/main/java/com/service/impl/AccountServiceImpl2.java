package com.service.impl;

import com.service.IAccountService;

import java.lang.reflect.Array;
import java.util.*;

public class AccountServiceImpl2 implements IAccountService {

    private String[] myStr;
    private List<String> myList;
    private Map<String,String> myMap;
    private Properties properties;

    public void setMyStr(String[] myStr) {
        this.myStr = myStr;
    }

    public void setMyList(List<String> myList) {
        this.myList = myList;
    }

    public void setMyMap(Map<String, String> myMap) {
        this.myMap = myMap;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public void saveAccount() {
        System.out.println(Arrays.toString(myStr));
        System.out.println(myList);
        System.out.println(myMap);
        System.out.println(properties);
    }
}
