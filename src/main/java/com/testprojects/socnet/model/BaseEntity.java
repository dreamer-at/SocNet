package com.testprojects.socnet.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Provides base entity
 *
 * @author askhat.talasbayev
 * @since 0.0.1
 */

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PROTECTED)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

    @JsonIgnore
    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    protected LocalDateTime createdDate;

    @JsonIgnore
    @LastModifiedDate
    @Column(name = "updated_date", nullable = false)
    protected LocalDateTime updatedDate;
}
