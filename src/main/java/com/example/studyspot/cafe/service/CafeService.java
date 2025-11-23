package com.example.studyspot.cafe.service;

import com.example.studyspot.cafe.domain.enums.DayOfWeek;
import com.example.studyspot.cafe.domain.model.*;
import com.example.studyspot.cafe.dto.CafeDetailsDTO;
import com.example.studyspot.cafe.dto.CafeFilter;
import com.example.studyspot.cafe.dto.CafeSimpleInfoDTO;
import com.example.studyspot.cafe.exception.CafeErrorType;
import com.example.studyspot.cafe.exception.CafeException;
import com.example.studyspot.cafe.repository.*;
import com.example.studyspot.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class CafeService {
    private final static long REPRESENTATIVE = 1;

    private final ReviewService reviewService;

    private final CafeRepository cafeRepository;
    private final BusinessHourRepository businessHourRepository;
    private final MenuRepository menuRepository;
    private final ImageRepository imageRepository;
    private final PurposeTypeRepository purposeTypeRepository;

    @Transactional(readOnly = true)
    public CafeDetailsDTO getCafeDetails(Long cafeId) {
        Cafe cafe = getCafeById(cafeId);
        List<Menu> menus = getMenusByCafe(cafe);
        List<BusinessHour> businessHours = getBusinessHoursByCafe(cafe);
        List<Image> images = getImagesByCafe(cafe);
        List<PurposeType> purposeTypes = getPurposeTypesByCafe(cafe);

        return CafeDetailsDTO.of(cafe, menus, businessHours, images, purposeTypes);
    }

    @Transactional(readOnly = true)
    public List<CafeSimpleInfoDTO> getRecommendationCafes() {
        return findRecommendationCafes()
                .stream()
                .map(this::toSimpleInfo)
                .toList();
    }

    private Cafe getCafeById(Long cafeId) {
        return cafeRepository.findById(cafeId)
                .orElseThrow(() -> new CafeException(CafeErrorType.CAFE_NOT_FOUND));
    }

    private List<Menu> getMenusByCafe(Cafe cafe) {
        return menuRepository.findByCafe(cafe);
    }

    private List<BusinessHour> getBusinessHoursByCafe(Cafe cafe) {
        return businessHourRepository.findByCafe(cafe);
    }

    private List<Image> getImagesByCafe(Cafe cafe) {
        return imageRepository.findByCafe(cafe);
    }

    private BusinessHour getTodayBusinessHour(Cafe cafe) {
        DayOfWeek dayOfWeek = getTodayDayOfWeek();
        return businessHourRepository.findByCafeAndDayOfWeek(cafe, dayOfWeek)
                .orElseThrow(() -> new CafeException(CafeErrorType.BUSINESS_HOUR_NOT_FOUND));
    }

    private List<PurposeType> getPurposeTypesByCafe(Cafe cafe) {
        return purposeTypeRepository.findByCafe(cafe);
    }


    private DayOfWeek getTodayDayOfWeek() {
        LocalDate today = LocalDate.now();
        String value = today.getDayOfWeek()
                .getDisplayName(TextStyle.FULL, Locale.US);

        return DayOfWeek.valueOf(value.toUpperCase());
    }

    private Boolean isCurrentlyWork(BusinessHour businessHour) {
        LocalTime now =  LocalTime.now();
        LocalTime startAt = businessHour.getStartAt();
        LocalTime endAt = businessHour.getEnd_at();

        return now.isAfter(startAt) && now.isBefore(endAt);
    }

    private Image getRepresentativeImage(Cafe cafe) {
        return imageRepository.findByCafeAndSequence(cafe, REPRESENTATIVE)
                .orElseThrow(() -> new CafeException(CafeErrorType.REPRESENTATIVE_IMAGE_NOT_FOUND));
    }

    private List<Cafe> findRecommendationCafes() {
        return cafeRepository.findRecommendationCafes();
    }

    private CafeSimpleInfoDTO toSimpleInfo(Cafe cafe) {
        BusinessHour businessHour = getTodayBusinessHour(cafe);
        Boolean isWork = isCurrentlyWork(businessHour);
        Image representativeImage = getRepresentativeImage(cafe);
        Double averageStarRating = reviewService.getAverageStarRatingByCafeId(cafe.getId());

        return CafeSimpleInfoDTO.from(
                cafe,
                businessHour,
                isWork,
                representativeImage,
                averageStarRating
        );
    }

    public List<CafeSimpleInfoDTO> searchCafesByFilter(CafeFilter cafeFilter) {
        return cafeRepository.searchByFilter(cafeFilter)
                .stream()
                .map(this::toSimpleInfo)
                .toList();
    }
}
