package com.buildweekjava.watermyplant2.models;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
abstract class Auditable
{
//  username of who created the row   /**

    @CreatedBy
    protected String createdBy;

// timestamp of when row was created
    @CreatedDate
    @Temporal(TIMESTAMP)
    protected Date createdDate;

// who last modified the row
    @LastModifiedBy
    protected String lastModifiedBy;

// timestamp of when it was last modified
    @LastModifiedDate
    @Temporal(TIMESTAMP)
    protected Date lastModifiedDate;
}