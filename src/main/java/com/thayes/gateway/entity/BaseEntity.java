package com.thayes.gateway.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.UUID;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Setter @Getter @EqualsAndHashCode
public abstract class BaseEntity<U> {

    @CreatedBy
    @Column(name="created_by")
    protected U createBy;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date", nullable = false, updatable = false)
    protected Date createdDate;

    @LastModifiedBy
    @Column(name="last_modified_by")
    protected U lastModifiedBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="last_modified_date")
    protected Date lastModifiedDate;
}
