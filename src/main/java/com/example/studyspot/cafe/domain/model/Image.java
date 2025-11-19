package com.example.studyspot.cafe.domain.model;

import com.example.studyspot.cafe.domain.vo.ImageUrl;
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

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "image_url", nullable = false))
    private ImageUrl imageUrl;

    @Column(nullable = false)
    private Long sequence;
}
