package com.example.studyspot.review.repository;

import com.example.studyspot.review.domain.model.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class JdbcReviewRepository implements ReviewRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Review save(Review review) {
        String sql = """
                INSERT INTO reviews (uuser_id, cafe_id, star_rating, created_at, content)
                VALUES (?,?,?,?,?)
                RETURNING id
                """;
        Long id = jdbcTemplate.queryForObject(
                sql,
                Long.class,
                review.getUuserId(),
                review.getCafeId(),
                review.getStarRating(),
                review.getCreatedAt(),
                review.getContent()
        );
        review.setId(id);
        return review;
    }

    @Override
    public Optional<Review> findById(Long reviewId) {
        return Optional.empty();
    }

    @Override
    public List<Review> findAllByCafeId(Long cafeId) {
        return List.of();
    }

    @Override
    public void delete(Review review) {

    }

    @Override
    public void clear() {

    }
}
