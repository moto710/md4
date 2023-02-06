package com.spbproductmanagementjwt.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Column(columnDefinition = "boolean default false", updatable = false)
    protected Boolean deleted = false;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    protected Date createdAt;

    @CreatedBy
    @Column(name = "created_by")
    protected String createdBy;

    @UpdateTimestamp
    @Column(name = "updated_at")
    protected Date updatedAt;

    @LastModifiedBy
    @Column(name = "updated_by")
    protected String updatedBy;
}
