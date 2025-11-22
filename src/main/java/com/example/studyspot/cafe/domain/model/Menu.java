package com.example.studyspot.cafe.domain.model;

import com.example.studyspot.cafe.domain.vo.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cafe cafe;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "price", nullable = false))
    private Price price;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "menu_name", nullable = false))
    private MenuName menuName;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "description", nullable = false))
    private Description description;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "image_url", nullable = false))
    private ImageUrl imageUrl;
}
