package com.starlabs.PaEase.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author francis chulu
 */
@Entity
@Table(name = "transactions")
@EntityListeners(AuditingEntityListener.class)
public class Transactions implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionID;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "serviceID", nullable = false)
    private Services service;
    private String external_transactionID;
    private double amount;
    private Long msisdn;
    private Long b_msisdn;
    private Long account;
    private String extra_data;
    private String narration;
    private int status_code;
    private String source_application;
    private String receipt_number;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date date_created;

    /**
     * @return the transactionID
     */
    public Long getTransactionID() {
        return transactionID;
    }

    /**
     * @param transactionID the transactionID to set
     */
    public void setTransactionID(Long transactionID) {
        this.transactionID = transactionID;
    }

    /**
     * @return the service
     */
    public Services getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(Services service) {
        this.service = service;
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
     * @return the narration
     */
    public String getNarration() {
        return narration;
    }

    /**
     * @param narration the narration to set
     */
    public void setNarration(String narration) {
        this.narration = narration;
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
     * @return the source_application
     */
    public String getSource_application() {
        return source_application;
    }

    /**
     * @param source_application the source_application to set
     */
    public void setSource_application(String source_application) {
        this.source_application = source_application;
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

    /**
     * @return the date_created
     */
    public Date getDate_created() {
        return date_created;
    }

    /**
     * @param date_created the date_created to set
     */
    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

}
