package com.project.member2.board.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity {
    @CreationTimestamp //생성됐을떄 시간
    @Column(updatable = false) //수정 시 관여 X
    private LocalDateTime createdTime;

    @UpdateTimestamp //수정했을 때 시간
    @Column(insertable = false) //입력 시 관여 X
    private LocalDateTime updatedTime;
}
