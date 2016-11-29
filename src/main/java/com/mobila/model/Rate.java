package com.mobila.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by ckibuchi on 11/2/2016.
 */
@XmlRootElement
@Entity
@Table(name="rates")
public class Rate implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="site")
    private Site site;

    @Transient
    Long siteId;

    @Column(name="minduration")
    private BigDecimal minduration;

    @Column(name="maxduration")
    private BigDecimal maxduration;

    @Column(name="amount")
    private BigDecimal amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public BigDecimal getMinduration() {
        return minduration;
    }

    public void setMinduration(BigDecimal minduration) {
        this.minduration = minduration;
    }

    public BigDecimal getMaxduration() {
        return maxduration;
    }

    public void setMaxduration(BigDecimal maxduration) {
        this.maxduration = maxduration;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
