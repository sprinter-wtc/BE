package com.example.studyspot.common.converter;

import com.example.studyspot.cafe.domain.enums.BaseEnum;
import com.example.studyspot.common.exception.CommonErrorType;
import com.example.studyspot.common.exception.StudySpotException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

@Component
public class StringToBaseEnumConverter implements ConverterFactory<String, BaseEnum> {

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return new BaseEnumConverter<>(targetType);
    }

    private static class BaseEnumConverter<T extends BaseEnum> implements Converter<String, T> {
        private final Class<T> targetType;

        public BaseEnumConverter(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        public T convert(String value) {
            for (T constant : targetType.getEnumConstants()) {
                if (constant.getValue().equals(value)) {
                    return constant;
                }
            }
            throw new StudySpotException(CommonErrorType.ENUM_CANNOT_CONVERT);
        }
    }
}
