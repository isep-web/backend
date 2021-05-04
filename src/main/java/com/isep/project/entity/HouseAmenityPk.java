package com.isep.project.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/5/4
 */
@Data
public class HouseAmenityPk implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Long houseId;

    private Long amenityId;
}
