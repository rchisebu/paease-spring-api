package com.starlabs.PaEase.request;

/**
 *
 * @author chulu
 */
public class ServiceRequest {

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

    private Long msisdn;
    private int serviceID;
    private double amount;
    private Long account;
    private String transactionID;
    private String nrc;
    private double balance;
    private String percentageCharge;
    private String extra_data;

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
     * @return the nrc
     */
    public String getNrc() {
        return nrc;
    }

    /**
     * @param nrc the nrc to set
     */
    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    /**
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * @return the percentageCharge
     */
    public String getPercentageCharge() {
        return percentageCharge;
    }

    /**
     * @param percentageCharge the percentageCharge to set
     */
    public void setPercentageCharge(String percentageCharge) {
        this.percentageCharge = percentageCharge;
    }

}
