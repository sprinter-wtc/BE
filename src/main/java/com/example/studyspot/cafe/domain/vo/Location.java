    package com.example.studyspot.cafe.domain.vo;

    import org.locationtech.jts.geom.Point;

    public record Location(Point value) {

        public Location {
            validateNotNull(value);
        }

        private static void validateNotNull(Point value) {
            if (value == null)
                throw new IllegalArgumentException("카페 위치는 비어있을 수 없습니다");
        }

        public double[] toCoordinates() {
            return new double[]{value.getY(), value.getX()};
        }
    }
