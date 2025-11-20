package com.example.studyspot.cafe.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location {
    private Point value;

    public Location (Point value) {
        validateNotNull(value);
        this.value = value;
    }

    private static void validateNotNull(Point value) {
        if (value == null)
            throw new IllegalArgumentException("카페 위치는 비어있을 수 없습니다");
    }

    public double[] toCoordinates() {
        return new double[]{value.getY(), value.getX()};
    }
}