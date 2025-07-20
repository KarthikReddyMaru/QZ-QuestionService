package com.qz.questionservice.model;

import com.qz.questionservice.audit.AuditAwareImpl;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class, AuditAwareImpl.class})
public class Auditable {

    @Column(updatable = false, nullable = false)
    @CreatedDate
    private String createdAt;

    @Column(updatable = false, nullable = false)
    @CreatedBy
    private String createdBy;

    @Column(insertable = false)
    @LastModifiedDate
    private String lastModifiedAt;

    @Column(insertable = false)
    @LastModifiedBy
    private String lastModifiedBy;

}
