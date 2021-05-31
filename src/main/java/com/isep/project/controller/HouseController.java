package com.isep.project.controller;

import com.isep.project.service.HouseService;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/5/31
 */
@RestController
@RequestMapping("/houses")
public class HouseController
{

    @Resource
    private HouseService houseService;

    @GetMapping("/advancedSearch")
    public List<Long> advancedSearch(
            @Parameter(description = "Search for houses with an area larger than or equal to the "
                    + "minimum area, require areaMax", allowEmptyValue = true)
            @RequestParam(value = "areaMin", required = false, defaultValue = "") String areaMin,
            @Parameter(description = "Search for houses with an area less than or equal to the "
                    + "maximum area, require areaMin", allowEmptyValue = true)
            @RequestParam(value = "areaMax", required = false, defaultValue = "") String areaMax,
            @Parameter(description = "Search title like %:title%", allowEmptyValue = true)
            @RequestParam(value = "title", required = false, defaultValue = "") String title,
            @Parameter(description = "Search for houses that can receive more than or equal to "
                    + "guest number", allowEmptyValue = true)
            @RequestParam(value = "guestNumber", required = false, defaultValue = "") String guestNumber,
            @Parameter(description = "Search for houses with these amenities, splitting multiple "
                    + "amenityId's by ','", allowEmptyValue = true, example = "1,2,3")
            @RequestParam(value = "amenities", required = false, defaultValue = "") String amenities,
            Pageable page)
    {
        return houseService.advancedSearch(areaMin, areaMax, title, guestNumber, amenities, page);
    }

}
