package com.starlabs.PaEase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author chulu
 */
@Entity
@Table(name = "services")
@EntityListeners(AuditingEntityListener.class)
public class Services implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceID;
    private String service_name;
    @JsonIgnore
    private String short_name;
    @JsonIgnore
    private double minmum_amount;
    @JsonIgnore
    private double maximum_amount;
    @JsonIgnore
    private String description;
    @JsonIgnore
    private int active;
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

    /**
     * @return the serviceID
     */
    public Long getServiceID() {
        return serviceID;
    }

    /**
     * @param serviceID the serviceID to set
     */
    public void setServiceID(Long serviceID) {
        this.serviceID = serviceID;
    }

    /**
     * @return the service_name
     */
    public String getService_name() {
        return service_name;
    }

    /**
     * @param service_name the service_name to set
     */
    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    /**
     * @return the short_name
     */
    public String getShort_name() {
        return short_name;
    }

    /**
     * @param short_name the short_name to set
     */
    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    /**
     * @return the minmum_amount
     */
    public double getMinmum_amount() {
        return minmum_amount;
    }

    /**
     * @param minmum_amount the minmum_amount to set
     */
    public void setMinmum_amount(double minmum_amount) {
        this.minmum_amount = minmum_amount;
    }

    /**
     * @return the maximum_amount
     */
    public double getMaximum_amount() {
        return maximum_amount;
    }

    /**
     * @param maximum_amount the maximum_amount to set
     */
    public void setMaximum_amount(double maximum_amount) {
        this.maximum_amount = maximum_amount;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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

}
