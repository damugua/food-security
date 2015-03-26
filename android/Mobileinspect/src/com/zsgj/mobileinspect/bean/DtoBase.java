package com.zsgj.mobileinspect.bean;
//import com.zsgj.csabicmobile.rest.Response;

import java.util.ArrayList;
import java.util.List;

public abstract class DtoBase {
    private int Id;
    private int TotalCount;
    private List<JsonMap> ExternParameters = new ArrayList<JsonMap>();

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(int totalCount) {
        TotalCount = totalCount;
    }

    public List<JsonMap> getExternParameters() {
        return ExternParameters;
    }

    public void setExternParameters(List<JsonMap> externParameters) {
        ExternParameters = externParameters;
    }
}
