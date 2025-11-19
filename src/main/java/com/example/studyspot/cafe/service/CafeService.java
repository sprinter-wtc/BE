package com.example.studyspot.cafe.service;

import com.example.studyspot.cafe.domain.model.*;
import com.example.studyspot.cafe.dto.CafeDetailsDTO;
import com.example.studyspot.cafe.exception.CafeErrorType;
import com.example.studyspot.cafe.exception.CafeException;
import com.example.studyspot.cafe.repository.BusinessHourRepository;
import com.example.studyspot.cafe.repository.CafeRepository;
import com.example.studyspot.cafe.repository.ImageRepository;
import com.example.studyspot.cafe.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CafeService {
    private final CafeRepository cafeRepository;
    private final BusinessHourRepository businessHourRepository;
    private final MenuRepository menuRepository;
    private final ImageRepository imageRepository;

    @Transactional(readOnly = true)
    public CafeDetailsDTO getCafeDetails(Long cafeId) {
        Cafe cafe = getCafeById(cafeId);
        List<Menu> menus = getMenusByCafe(cafe);
        List<BusinessHour> businessHours = getBusinessHoursByCafe(cafe);
        List<Image> images = getImagesByCafe(cafe);

        return CafeDetailsDTO.of(cafe, menus, businessHours, images);
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

}
