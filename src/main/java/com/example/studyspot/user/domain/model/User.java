package com.example.studyspot.user.domain.model;

import com.example.studyspot.user.domain.vo.ImageUrl;
import com.example.studyspot.user.domain.vo.Name;
import com.example.studyspot.user.util.RandomNameGenerator;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name="uuser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ssaid;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "name", unique = true, nullable = false))
    private Name name;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "image_url"))
    private ImageUrl imageUrl;

    public static User createUserOfRandomName(String ssaid) {
        String name = RandomNameGenerator.generate();
        return new User(null, ssaid, new Name(name), null);
    }
}