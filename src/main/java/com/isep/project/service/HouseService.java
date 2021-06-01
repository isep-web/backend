package com.isep.project.service;

import com.isep.project.repository.HouseRepository;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/5/31
 */
@Service
public class HouseService
{

    @Resource
    private HouseRepository houseRepository;

    public List<Long> advancedSearch(String areaMin, String areaMax, String title,
            String guestNumber,
            String amenities, Pageable page)
    {
        String[] amenitiesList = amenities.split(",");
        List<Long> amenitiesLong = new ArrayList<>();
        for (String service : amenitiesList)
        {
            amenitiesLong.add(Long.parseLong(service));
        }
        return houseRepository
                .advSearch(areaMin, areaMax, title, guestNumber, amenitiesLong,
                        (long) amenitiesLong.size(), page);
    }

    public List<Long> advancedSearchWithoutAmenities(String areaMin, String areaMax, String title,
            String guestNumber, Pageable page)
    {
        return houseRepository
                .advSearchWithoutAnemities(areaMin, areaMax, title, guestNumber, page);
    }
}
