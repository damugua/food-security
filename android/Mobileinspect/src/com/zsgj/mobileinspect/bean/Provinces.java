package com.zsgj.mobileinspect.bean;

import java.util.List;

/**
 * Created by hyq on 2015/3/19.
 */
public class Provinces extends DtoBase {
    private List<Province> Provinces;

    public List<Province> getProvinces() {
        return Provinces;
    }

    public void setProvinces(List<Province> provinces) {
        Provinces = provinces;
    }
}
