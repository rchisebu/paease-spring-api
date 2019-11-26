/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starlabs.PaEase.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author francis chulu
 */
@Entity
@Table(name = "customerprofiles")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CustomerProfile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileID;
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @JsonIgnore
    private Date date_created;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @JsonIgnore
    private Date date_modified;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customerID", nullable = false)
    private Customer customer;

    public CustomerProfile() {
    }
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "risk_categoryID", nullable = false)
    private RiskCategory riskCategory;
    private Long msisdn;
    private Long alternative_msisdn;
    private String bank_account;
    private Double balance;
    private Double loan_amount;
    private Double min_amount;
    private String percentage_charge;
    @JsonIgnore
    private String PIN;
    private int status;
    private int wrong_pin_attempts;
    private String lastlogin;

    /**
     * @return the profileID
     */
    public Long getProfileID() {
        return profileID;
    }

    /**
     * @param profileID the profileID to set
     */
    public void setProfileID(Long profileID) {
        this.profileID = profileID;
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

    /**
     * @return the date_modified
     */
    public Date getDate_modified() {
        return date_modified;
    }

    /**
     * @param date_modified the date_modified to set
     */
    public void setDate_modified(Date date_modified) {
        this.date_modified = date_modified;
    }

    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return the riskCategory
     */
    public RiskCategory getRiskCategory() {
        return riskCategory;
    }

    /**
     * @param riskCategory the riskCategory to set
     */
    public void setRiskCategory(RiskCategory riskCategory) {
        this.riskCategory = riskCategory;
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
     * @return the alternative_msisdn
     */
    public Long getAlternative_msisdn() {
        return alternative_msisdn;
    }

    /**
     * @param alternative_msisdn the alternative_msisdn to set
     */
    public void setAlternative_msisdn(Long alternative_msisdn) {
        this.alternative_msisdn = alternative_msisdn;
    }

    /**
     * @return the bank_account
     */
    public String getBank_account() {
        return bank_account;
    }

    /**
     * @param bank_account the bank_account to set
     */
    public void setBank_account(String bank_account) {
        this.bank_account = bank_account;
    }

    /**
     * @return the balance
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    /**
     * @return the loan_amount
     */
    public Double getLoan_amount() {
        return loan_amount;
    }

    /**
     * @param loan_amount the loan_amount to set
     */
    public void setLoan_amount(Double loan_amount) {
        this.loan_amount = loan_amount;
    }

    /**
     * @return the min_amount
     */
    public Double getMin_amount() {
        return min_amount;
    }

    /**
     * @param min_amount the min_amount to set
     */
    public void setMin_amount(Double min_amount) {
        this.min_amount = min_amount;
    }

    /**
     * @return the percentage_charge
     */
    public String getPercentage_charge() {
        return percentage_charge;
    }

    /**
     * @param percentage_charge the percentage_charge to set
     */
    public void setPercentage_charge(String percentage_charge) {
        this.percentage_charge = percentage_charge;
    }

    /**
     * @return the PIN
     */
    public String getPIN() {
        return PIN;
    }

    /**
     * @param PIN the PIN to set
     */
    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

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
     * @return the wrong_pin_attempts
     */
    public int getWrong_pin_attempts() {
        return wrong_pin_attempts;
    }

    /**
     * @param wrong_pin_attempts the wrong_pin_attempts to set
     */
    public void setWrong_pin_attempts(int wrong_pin_attempts) {
        this.wrong_pin_attempts = wrong_pin_attempts;
    }

    /**
     * @return the lastlogin
     */
    public String getLastlogin() {
        return lastlogin;
    }

    /**
     * @param lastlogin the lastlogin to set
     */
    public void setLastlogin(String lastlogin) {
        this.lastlogin = lastlogin;
    }

}
