package com.cg.spbajaxbankingtransactionjwt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@MappedSuperclass
public abstract class BaseEntity {

    @Column(columnDefinition = "boolean default false")
    protected Boolean deleted = false;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    protected Date createdAt;

    @CreatedBy
    @Column(name = "created_by")
    protected Integer createdBy;

    @UpdateTimestamp
    @Column(name = "updated_at")
    protected Date updatedAt;

    @LastModifiedBy
    @Column(name = "updated_by")
    private Integer updatedBy;

}
