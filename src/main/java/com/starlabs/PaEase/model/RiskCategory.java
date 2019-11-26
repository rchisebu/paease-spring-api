package com.starlabs.PaEase.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author francis chulu
 */
@Entity
@Table(name = "riskcategory")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RiskCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long riskID;
    private String name;
    private String description;
    private String max_amount;
    private int active;

    /**
     * @return the riskID
     */
    public Long getRiskID() {
        return riskID;
    }

    /**
     * @param riskID the riskID to set
     */
    public void setRiskID(Long riskID) {
        this.riskID = riskID;
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
     * @return the maximumAmount
     */
    public String getMaximumAmount() {
        return max_amount;
    }

    /**
     * @param maximumAmount the maximumAmount to set
     */
    public void setMaximumAmount(String maximumAmount) {
        this.max_amount = maximumAmount;
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
