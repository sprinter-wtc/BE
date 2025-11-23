package com.example.studyspot.cafe.dto;

import com.example.studyspot.cafe.domain.enums.Category;
import com.example.studyspot.cafe.domain.enums.Purpose;
import com.example.studyspot.cafe.domain.enums.tags.*;

public record CafeFilter(
   String nameOfCafe,
   Category category,
   Purpose purpose,
   LightningLevel lightningLevel,
   NoiseLevel noiseLevel,
   PowerOutletLevel powerOutletLevel,
   StayDurationPolicy stayDurationPolicy,
   ParkingAvailability parkingAvailability,
   TransportLevel transportLevel,
   SurroundingEnvironment surroundingEnvironment,
   PetFriendly petFriendly
) {
}
