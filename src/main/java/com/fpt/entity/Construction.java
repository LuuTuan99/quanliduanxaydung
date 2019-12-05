package com.fpt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Construction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id", example = "push")
    private long id;
    @ApiModelProperty(value = "name", example = "push")
    private String name;
    @JsonFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "startDate", example = "push")
    private Date startDate;
    @ApiModelProperty(value = "endDate", example = "push")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endDate;
    @ApiModelProperty(value = "estimatedDate", example = "push")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date estimatedDate;
    @ApiModelProperty(value = "reason", example = "push")
    private String reason;
    @ApiModelProperty(value = "status", example = "push")
    private int status;

    @JsonManagedReference
    @OneToMany(mappedBy = "construction", cascade = CascadeType.ALL)
    private Set<Category> categories = new HashSet<>();

    public enum Status {
        ACTIVE(1), DEACTIVE(0),DELETED(-1);

        private int value;

        Status(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEstimatedDate() {
        return estimatedDate;
    }

    public void setEstimatedDate(Date estimatedDate) {
        this.estimatedDate = estimatedDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
