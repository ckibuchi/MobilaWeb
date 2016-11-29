package com.mobila.model;

/**
 * Created by ckibuchi on 10/28/2016.
 */

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;/*
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "user")*/
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(name="date_created")
    @Temporal(value=TemporalType.DATE)
    private Date datecreated;
    public void setId(Long id) {
        this.id = id;
    }
    @Column(name="name")
    private String name;
    @Column(name="profession")
    private String profession;
    @Column(name="phone",unique = true)
    private String phone;
    @Column(name="gender")
    private String gender;
    @Column(name="email")
    private String email;
    @Column(name="address")
    private String address;
    @Column(name="password")
    private String password;
    @Column(name="password2")
    private String password2;

    private Site site;

    @Transient
    Long siteId;

    public User(){}

    public User(long id, String name, String profession){
        this.id = id;
        this.name = name;
        this.profession = profession;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;

    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", datecreated=" + datecreated +
                ", name='" + name + '\'' +
                ", profession='" + profession + '\'' +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", site=" + (site!=null?site.toString():"") +
                ", siteId=" + siteId +
                '}';
    }
    public String toString2() {
        return "{" +
                "id='" + id + '\'' +
                ", datecreated='" + datecreated + '\'' +
                ", name='" + name + '\'' +
                ", profession='" + profession + '\'' +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", site='" + (site!=null?site.toString2():"") + '\'' +
                ", siteId='" + siteId + '\'' +
                '}';
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
}