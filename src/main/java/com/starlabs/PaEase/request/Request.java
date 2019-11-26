package com.starlabs.PaEase.request;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author chulu
 */
public class Request {

    private Long msisdn;
    private Long b_msisdn;
    private int pin;
    private String extra_data;
    private int serviceID;
    private double amount;
    private Long account;
    private String transactionID;
    private int status_code;
    private String status_desc;
    private String external_transactionID;
    private String receipt_number;
    @NotBlank(message = "Method cannot be blank!")
    private String method;
    private String hash;

    /**
     * @return the hash
     */
    public String getHash() {
        return hash;
    }

    /**
     * @param hash the hash to set
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

    /**
     * @return the method
     */
    public String getMethod() {
        return method;
    }

    /**
     * @param method the method to set
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * @return the msisdn
     */
    public Long getMsisdn() {
        return msisdn;
    }

    /**
     * @param msisdn the msisdn to set
     */
    public void setMsisdn(Long msisdn) {
        this.msisdn = msisdn;
    }

    /**
     * @return the b_msisdn
     */
    public Long getB_msisdn() {
        return b_msisdn;
    }

    /**
     * @param b_msisdn the b_msisdn to set
     */
    public void setB_msisdn(Long b_msisdn) {
        this.b_msisdn = b_msisdn;
    }

    /**
     * @return the pin
     */
    public int getPin() {
        return pin;
    }

    /**
     * @param pin the pin to set
     */
    public void setPin(int pin) {
        this.pin = pin;
    }

    /**
     * @return the extra_data
     */
    public String getExtra_data() {
        return extra_data;
    }

    /**
     * @param extra_data the extra_data to set
     */
    public void setExtra_data(String extra_data) {
        this.extra_data = extra_data;
    }

    /**
     * @return the serviceID
     */
    public int getServiceID() {
        return serviceID;
    }

    /**
     * @param serviceID the serviceID to set
     */
    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return the account
     */
    public Long getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(Long account) {
        this.account = account;
    }

    /**
     * @return the transactionID
     */
    public String getTransactionID() {
        return transactionID;
    }

    /**
     * @param transactionID the transactionID to set
     */
    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    /**
     * @return the status_code
     */
    public int getStatus_code() {
        return status_code;
    }

    /**
     * @param status_code the status_code to set
     */
    public void setStatus_code(int status_code) {
        this.status_code = status_code;
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

    /**
     * @return the external_transactionID
     */
    public String getExternal_transactionID() {
        return external_transactionID;
    }

    /**
     * @param external_transactionID the external_transactionID to set
     */
    public void setExternal_transactionID(String external_transactionID) {
        this.external_transactionID = external_transactionID;
    }

    /**
     * @return the receipt_number
     */
    public String getReceipt_number() {
        return receipt_number;
    }

    /**
     * @param receipt_number the receipt_number to set
     */
    public void setReceipt_number(String receipt_number) {
        this.receipt_number = receipt_number;
    }

}
