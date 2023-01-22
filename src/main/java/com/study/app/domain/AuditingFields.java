package com.study.app.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
*  테이블에 생성시 공통으로 들어가는 컬럼
*  사용법 사용하는 도메인에 extends AuditingFields 추가
* */
@Getter
@ToString
@EntityListeners(AuditingEntityListener.class) // 이거 없으면 테스트 할때 createdAt 때문에 에러남
@MappedSuperclass
public class AuditingFields {
    //메타데이터
    @CreatedDate
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt; // 생성일자

    @LastModifiedDate
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime modifiedAt; // 수정일자



}
