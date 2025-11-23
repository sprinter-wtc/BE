package com.example.studyspot.cafe.repository;

import com.example.studyspot.cafe.domain.enums.Category;
import com.example.studyspot.cafe.domain.enums.Purpose;
import com.example.studyspot.cafe.domain.enums.tags.*;
import com.example.studyspot.cafe.domain.model.Cafe;
import com.example.studyspot.cafe.dto.CafeFilter;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.studyspot.cafe.domain.model.QCafe.cafe;
import static com.example.studyspot.cafe.domain.model.QTag.tag;
import static com.example.studyspot.cafe.domain.model.QPurposeType.purposeType;


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

    @Override
    public List<Cafe> searchByFilter(CafeFilter filter) {
        return queryFactory
                .select(cafe)
                .from(cafe)
                .leftJoin(cafe.tags, tag).fetchJoin()
                .leftJoin(purposeType).on(purposeType.cafe.eq(cafe))
                .where(
                    nameLike(filter.nameOfCafe()),
                    categoryEq(filter.category()),
                    purposeEq(filter.purpose()),
                    lightningEq(filter.lightningLevel()),
                    noiseEq(filter.noiseLevel()),
                    powerOutletEq(filter.powerOutletLevel()),
                    stayDurationEq(filter.stayDurationPolicy()),
                    parkingEq(filter.parkingAvailability()),
                    transportEq(filter.transportLevel()),
                    environmentEq(filter.surroundingEnvironment()),
                    petFriendlyEq(filter.petFriendly())
                )
                .distinct()
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

    private BooleanExpression nameLike(String nameOfCafe) {
        return nameOfCafe == null ? null :
                cafe.name.value.containsIgnoreCase(nameOfCafe);
    }

    private BooleanExpression categoryEq(Category category) {
        return category == null ? null : cafe.category.eq(category);
    }

    private BooleanExpression purposeEq(Purpose purpose) {
        return purpose == null ? null : purposeType.purpose.eq(purpose);
    }

    private BooleanExpression lightningEq(LightningLevel level) {
        return level == null ? null : tag.lightningLevel.eq(level);
    }

    private BooleanExpression noiseEq(NoiseLevel level) {
        return level == null ? null : tag.noiseLevel.eq(level);
    }

    private BooleanExpression powerOutletEq(PowerOutletLevel level) {
        return level == null ? null : tag.powerOutletLevel.eq(level);
    }

    private BooleanExpression stayDurationEq(StayDurationPolicy policy) {
        return policy == null ? null : tag.stayDurationPolicy.eq(policy);
    }

    private BooleanExpression parkingEq(ParkingAvailability availability) {
        return availability == null ? null : tag.parkingAvailability.eq(availability);
    }

    private BooleanExpression transportEq(TransportLevel level) {
        return level == null ? null : tag.transportLevel.eq(level);
    }

    private BooleanExpression environmentEq(SurroundingEnvironment env) {
        return env == null ? null : tag.surroundingEnvironment.eq(env);
    }

    private BooleanExpression petFriendlyEq(PetFriendly petFriendly) {
        return petFriendly == null ? null : tag.petFriendly.eq(petFriendly);
    }

}
