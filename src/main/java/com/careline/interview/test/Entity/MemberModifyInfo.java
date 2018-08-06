package com.careline.interview.test.Entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class MemberModifyInfo {
    @Id
    @GeneratedValue
    private UUID id;

    @CreatedDate
    private LocalDateTime modifyTime;

    private String modifyInfo;

    @CreatedBy
    private UUID modifyBy;
}
