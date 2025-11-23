package com.example.studyspot.cafe.domain.model;

import com.example.studyspot.cafe.domain.enums.tags.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LightningLevel lightningLevel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NoiseLevel noiseLevel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ParkingAvailability parkingAvailability;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PetFriendly petFriendly;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PowerOutletLevel powerOutletLevel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StayDurationPolicy stayDurationPolicy;

    @Enumerated(EnumType.STRING)
    private SurroundingEnvironment surroundingEnvironment;

    @Enumerated(EnumType.STRING)
    private TransportLevel transportLevel;
}
