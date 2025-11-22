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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "name", unique = true, nullable = false))
    private Name name;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "image_url", unique = true, nullable = false))
    private ImageUrl imageUrl;

    public static User createUserOfRandomName() {
        String name = RandomNameGenerator.generate();
        return new User(null, new Name(name), null);
    }
}