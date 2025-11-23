package com.example.studyspot.review.repository;

import com.example.studyspot.review.dto.ReviewDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.studyspot.review.domain.model.QReview.review;
import static com.example.studyspot.user.domain.model.QUser.user;

@RequiredArgsConstructor
public class ReviewCustomRepositoryImpl implements ReviewCustomRepository {
    private static final long BEST_REVIEW_COUNT = 2;

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ReviewDTO> findBestReviewsByCafeId(Long cafeId) {
        return queryFactory
                .select(Projections.constructor(
                        ReviewDTO.class,
                        review.starRating,
                        user.name.value,
                        review.createdAt,
                        review.content,
                        user.imageUrl.value
                ))
                .from(review)
                .join(user).on(review.cafeId.eq(user.id))
                .where(review.cafeId.eq(cafeId))
                .orderBy(review.starRating.desc(), review.createdAt.desc())
                .limit(BEST_REVIEW_COUNT)
                .fetch();
    }
}