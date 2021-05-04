package com.isep.project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/5/5
 */
@Data
@Entity
@IdClass(HouseConstraintPk.class)
@Table(name = "t_house__constraint")
@EntityListeners(AuditingEntityListener.class)
public class HouseConstraint implements Serializable
{

    private static final long serialVersionUID = 1251154320182031702L;

    @Id
    @Column(name = "f_house_id", nullable = false)
    private Long houseId;

    @Id
    @Column(name = "f_constraint_id", nullable = false)
    private Long constraintId;

    @Column(name = "f_created_time")
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

}
