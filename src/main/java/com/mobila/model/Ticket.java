package com.mobila.model;

import com.mobila.utils.Status;
import com.mobila.utils.YesNo;

import javax.persistence.*;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Stack;

/**
 * Created by ckibuchi on 11/2/2016.
 */
@XmlRootElement
@Entity
@Table(name="tickets")
public class Ticket implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="timedate_created")
    @Temporal(value=TemporalType.TIMESTAMP)
    private Date timedatecreated;

    @Column(name="timedate_paid")
    @Temporal(value=TemporalType.TIMESTAMP)
    private Date timedatepaid;

    @Column(name="timedate_exited")
    @Temporal(value=TemporalType.TIMESTAMP)
    private Date timedateexited;

    @Column(name="scancode")
    private String scancode;


    private Site site;

    @Transient
    Long siteId;

    @Column(name="amount")
    private BigDecimal amount;

    @Column(name="commission")
    private BigDecimal commission;

    @Column(name="duration")
    private BigDecimal duration;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name="paid")
    @Enumerated(EnumType.STRING)
    private YesNo paid;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimedatecreated() {
        return timedatecreated;
    }

    public void setTimedatecreated(Date timedatecreated) {
        this.timedatecreated = timedatecreated;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getScancode() {
        return scancode;
    }

    public void setScancode(String scancode) {
        this.scancode = scancode;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public YesNo getPaid() {
        return paid;
    }

    public void setPaid(YesNo paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", timedatecreated=" + timedatecreated +
                ", timedatepaid=" + timedatepaid +
                ", timedateexited=" + timedateexited +
                ", scancode='" + scancode + '\'' +
                ", site=" + site +
                ", siteId=" + siteId +
                ", amount=" + amount +
                ", commission=" + commission +
                ", duration=" + duration +
                ", paid=" + paid +
                ", status=" + status +
                '}';
    }

    public String toString2() {
        return "{" +
                "id='" + id + '\'' +
                ", timedatecreated='" + timedatecreated + '\'' +
                ", timedatepaid='" + timedatepaid + '\'' +
                ", timedateexited='" + timedateexited + '\'' +
                ", scancode='" + scancode + '\'' +
                ", site='" + (site==null?"":site.toString2()) + '\'' +
                ", siteId='" + siteId + '\'' +
                ", amount='" + amount + '\'' +
                ", commission='" + commission + '\'' +
                ", duration='" + duration + '\'' +
                ", paid='" + paid + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public Date getTimedatepaid() {
        return timedatepaid;
    }

    public void setTimedatepaid(Date timedatepaid) {
        this.timedatepaid = timedatepaid;
    }

    public Date getTimedateexited() {
        return timedateexited;
    }

    public void setTimedateexited(Date timedateexited) {
        this.timedateexited = timedateexited;
    }
}
