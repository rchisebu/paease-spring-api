package com.starlabs.PaEase.response;

import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author chulu
 */
public class Response {

    /**
     * @return the data
     */
    public ArrayList getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(ArrayList data) {
        this.data = data;
    }

    private int status;
    private String extraData;
    private String status_desc;
    private ArrayList data=new ArrayList();

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the extraData
     */
    public String getExtraData() {
        return extraData;
    }

    /**
     * @param extraData the extraData to set
     */
    public void setExtraData(String extraData) {
        this.extraData = extraData;
    }

    /**
     * @return the status_desc
     */
    public String getStatus_desc() {
        return status_desc;
    }

    /**
     * @param status_desc the status_desc to set
     */
    public void setStatus_desc(String status_desc) {
        this.status_desc = status_desc;
    }

    

}
