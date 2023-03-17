package com.convenience.conveniencestorerecommendation.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity(name = "direction")
public class Direction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 사용자
    private String inputAddress;
    private double inputLatitude;
    private double inputLongitude;

    // 편의점
    private String targetConvenienceStoreName;
    private String targetAddress;
    private double targetLatitude;
    private double targetLongitude;

    // 사용자 주소 와 편의점 주소 사이의 거리
    private double distance;

    @CreatedDate @Column(updatable = false) private LocalDateTime createdDate;
    @LastModifiedDate private LocalDateTime modifiedDate;
}
