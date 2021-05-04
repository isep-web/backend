package com.isep.project.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@IdClass(HouseServicePK.class)
@Table(name = "t_house__service")
@EntityListeners(AuditingEntityListener.class)
public class HouseService implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "f_house_id", nullable = false)
    private Long houseId;

    @Id
    @Column(name = "f_service_id", nullable = false)
    private Long serviceId;

    @Column(name = "f_created_time")
    private Date createdTime;

}
