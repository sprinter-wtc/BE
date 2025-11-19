package com.example.studyspot.cafe.domain.model;

import com.example.studyspot.cafe.domain.enums.Category;
import com.example.studyspot.cafe.domain.enums.Purpose;
import com.example.studyspot.cafe.domain.vo.LimitTime;
import com.example.studyspot.cafe.domain.vo.Location;
import com.example.studyspot.cafe.domain.vo.CafeName;
import com.example.studyspot.cafe.domain.vo.PhoneNumber;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cafe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "name", unique = true, nullable = false))
    private CafeName name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "limt_time", nullable = false))
    private LimitTime limitTime;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "location", nullable = false))
    private Location location;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "phone_number", nullable = false))
    private PhoneNumber phoneNumber;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "purpose", joinColumns = @JoinColumn(name = "cafe_id"))
    @Column(name = "value")
    @Enumerated(EnumType.STRING)
    private Set<Purpose> purposes;

    @OneToOne(cascade =  CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "tag_id")
    private Tag tags;
}
