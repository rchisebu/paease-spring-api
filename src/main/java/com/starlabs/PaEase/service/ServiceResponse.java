/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starlabs.PaEase.service;

/**
 *
 * @author chulu
 */
public class ServiceResponse {

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
   private int status;
   private String status_desc; 
}
