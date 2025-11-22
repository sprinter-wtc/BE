package com.example.studyspot.review.repository;

import com.example.studyspot.common.exception.StudySpotException;
import com.example.studyspot.review.domain.model.Review;
import com.example.studyspot.review.exception.ReviewErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class JdbcReviewRepository implements ReviewRepository {

    private final JdbcTemplate jdbcTemplate;

    private RowMapper<Review> reviewRowMapper() {
        return (rs, rowNum) -> Review.fromRow(
                rs.getLong("id"),
                rs.getLong("uuser_id"),
                rs.getLong("cafe_id"),
                rs.getDouble("star_rating"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getString("content")
                );
    }

    @Override
    public void update(Review review){
        String sql = """
                UPDATE reviews
                SET star_rating = ?,
                    content     = ?
                WHERE id = ?
                """;
        int updatedRows = jdbcTemplate.update(
                sql,
                review.getStarRating(),
                review.getContent(),
                review.getId()
        );

        if (updatedRows == 0){
            throw new StudySpotException(ReviewErrorType.REVIEW_NOT_FOUND);
        }
    }

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
        String sql = """
                SELECT id, uuser_id, cafe_id, star_rating, created_at, content
                FROM reviews
                WHERE id = ?
                """;
        List<Review> result = jdbcTemplate.query(sql, reviewRowMapper(),reviewId);
        return result.stream().findFirst();
    }

    @Override
    public List<Review> findAllByCafeId(Long cafeId) {
        String sql = """
                SELECT id, uuser_id, cafe_id, star_rating, created_at, content
                FROM reviews
                WHERE cafe_id = ?
                """;
        return jdbcTemplate.query(sql, reviewRowMapper(),cafeId);
    }

    @Override
    public void delete(Review review) {
        String sql = """
                DELETE FROM reviews 
                WHERE id = ?
                """;
        jdbcTemplate.update(sql,review.getId());
    }

    @Override
    public void clear() {

    }
}
