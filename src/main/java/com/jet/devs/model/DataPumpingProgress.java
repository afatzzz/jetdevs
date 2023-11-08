package com.jet.devs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Author: ab
 * Date Time: 07/11/23
 */
@Entity
@Table(name = "data_pumping_progress")
public class DataPumpingProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "excel_name")
    private String excelName;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "generated_by")
    private String generatedBy;
    @Column(name = "last_view")
    private Date lastView;
    @Column(name = "view_by")
    private String viewBy;


    public String getExcelName() {
        return excelName;
    }

    public void setExcelName(String excelName) {
        this.excelName = excelName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getGeneratedBy() {
        return generatedBy;
    }

    public void setGeneratedBy(String generatedBy) {
        this.generatedBy = generatedBy;
    }

    public Date getLastView() {
        return lastView;
    }

    public void setLastView(Date lastView) {
        this.lastView = lastView;
    }

    public String getViewBy() {
        return viewBy;
    }

    public void setViewBy(String viewBy) {
        this.viewBy = viewBy;
    }
}
