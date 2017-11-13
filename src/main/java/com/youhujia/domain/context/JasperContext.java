package com.youhujia.domain.context;

import com.youhujia.domain.object.CTest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lilac on 13/11/2017.
 */
public class JasperContext {

    private String URI;
    private Map<String, String[]> parameterMap;
    private CTest cTest;

    public CTest getcTest() {
        return cTest;
    }

    public void setcTest(CTest cTest) {
        this.cTest = cTest;
    }

    public JasperContext() {
        this.parameterMap = new HashMap<>();
    }

    public String getURI() {
        return URI;
    }

    public void setURI(String URI) {
        this.URI = URI;
    }

    public Map<String, String[]> getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map<String, String[]> parameterMap) {
        this.parameterMap = parameterMap;
        this.cTest = new CTest();
    }
}

