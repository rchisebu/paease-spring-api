/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starlabs.PaEase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author francis chulu
 */
@Entity
@Table(name = "clients")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"date_created", "date_modified"},
        allowGetters = true)
public class Clients implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientID;

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

    @NotBlank
    private String name;

    @Size(max = 250)
    private String address;
    private String primary_contact_person;
    private long primary_contact;
    private long alternative_contact;
    private String email;
    private String country;
    private int active;

    /**
     * @return the clientID
     */
    public Long getClientID() {
        return clientID;
    }

    /**
     * @param clientID the clientID to set
     */
    public void setClientID(Long clientID) {
        this.clientID = clientID;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the primary_contact_person
     */
    public String getPrimary_contact_person() {
        return primary_contact_person;
    }

    /**
     * @param primary_contact_person the primary_contact_person to set
     */
    public void setPrimary_contact_person(String primary_contact_person) {
        this.primary_contact_person = primary_contact_person;
    }

    /**
     * @return the primary_contact
     */
    public long getPrimary_contact() {
        return primary_contact;
    }

    /**
     * @param primary_contact the primary_contact to set
     */
    public void setPrimary_contact(long primary_contact) {
        this.primary_contact = primary_contact;
    }

    /**
     * @return the alternative_contact
     */
    public long getAlternative_contact() {
        return alternative_contact;
    }

    /**
     * @param alternative_contact the alternative_contact to set
     */
    public void setAlternative_contact(long alternative_contact) {
        this.alternative_contact = alternative_contact;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the active
     */
    public int getActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(int active) {
        this.active = active;
    }

}
