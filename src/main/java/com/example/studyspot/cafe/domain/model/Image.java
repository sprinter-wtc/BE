package com.example.studyspot.cafe.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cafe cafe;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private Long sequence;
}
