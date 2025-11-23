package com.example.studyspot.cafe.repository;

import com.example.studyspot.cafe.domain.enums.tags.ParkingAvailability;
import com.example.studyspot.cafe.domain.enums.tags.PowerOutletLevel;
import com.example.studyspot.cafe.domain.model.Cafe;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.studyspot.cafe.domain.model.QCafe.cafe;
import static com.example.studyspot.cafe.domain.model.QTag.tag;


@RequiredArgsConstructor
public class CafeCustomRepositoryImpl implements CafeCustomRepository {
    private final long RECOMMENDATION_CAFE_COUNT = 3;

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Cafe> findRecommendationCafes() {
        return queryFactory
                .select(cafe)
                .from(cafe)
                .join(cafe.tags, tag)
                .on(cafe.tags.id.eq(tag.id))
                .orderBy(parkingScore.add(outletScore).desc())
                .limit(RECOMMENDATION_CAFE_COUNT)
                .fetch();
    }

    private NumberExpression<Integer> parkingScore = new CaseBuilder()
            .when(tag.parkingAvailability.eq(ParkingAvailability.AVAILABLE)).then(2)
            .when(tag.parkingAvailability.eq(ParkingAvailability.PAID)).then(1)
            .otherwise(0);

    private NumberExpression<Integer> outletScore = new CaseBuilder()
            .when(tag.powerOutletLevel.eq(PowerOutletLevel.MANY)).then(2)
            .when(tag.powerOutletLevel.eq(PowerOutletLevel.SOME)).then(1)
            .otherwise(0);

}
