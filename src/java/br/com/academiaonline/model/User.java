/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.academiaonline.model;

import java.io.InputStream;
import java.util.Date;

/**
 *
 * @author Diego
 */
public class User extends Person{

    private Integer id;
    private Date registratioDate;
    private InputStream profilePicture;
    private int fileItem;

    /**
     * @return the id
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the registratioDate
     */
    public Date getRegistratioDate() {
        return registratioDate;
    }

    /**
     * @param registratioDate the registratioDate to set
     */
    public void setRegistratioDate(Date registratioDate) {
        this.registratioDate = registratioDate;
    }

    /**
     * @return the profilePicture
     */
    public InputStream getProfilePicture() {
        return profilePicture;
    }

    /**
     * @param profilePicture the profilePicture to set
     */
    public void setProfilePicture(InputStream profilePicture) {
        this.profilePicture = profilePicture;
    }

    /**
     * @return the fileItem
     */
    public int getFileItem() {
        return fileItem;
    }

    /**
     * @param fileItem the fileItem to set
     */
    public void setFileItem(int fileItem) {
        this.fileItem = fileItem;
    }
}
