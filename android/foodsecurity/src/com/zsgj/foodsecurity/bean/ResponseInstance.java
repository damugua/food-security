package com.zsgj.foodsecurity.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyq on 2015/3/19.
 */
public class ResponseInstance {
    private BusinessException BusinessExceptionInstance = new BusinessException();
    private List<BusinessWarning> BusinessWarningList = new ArrayList<BusinessWarning>();
    private int EntityIdInstance;

    public BusinessException getBusinessExceptionInstance() {
        return BusinessExceptionInstance;
    }

    public void setBusinessExceptionInstance(BusinessException businessExceptionInstance) {
        BusinessExceptionInstance = businessExceptionInstance;
    }

    public List<BusinessWarning> getBusinessWarningList() {
        return BusinessWarningList;
    }

    public void setBusinessWarningList(List<BusinessWarning> businessWarningList) {
        BusinessWarningList = businessWarningList;
    }

    public int getEntityIdInstance() {
        return EntityIdInstance;
    }

    public void setEntityIdInstance(int entityIdInstance) {
        EntityIdInstance = entityIdInstance;
    }
}
