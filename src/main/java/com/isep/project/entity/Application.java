package com.isep.project.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/5/5
 */
@Data
@Entity
@Table(name = "t_application")
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class Application extends BaseEntity implements Serializable
{

    private static final long serialVersionUID = -1076269535680923985L;

    @Column(name = "f_source_user_id", nullable = false)
    private Long sourceUserId;

    @Column(name = "f_target_user_id", nullable = false)
    private Long targetUserId;

    @Column(name = "f_house_id", nullable = false)
    private Long houseId;

    /**
     * 0=no/1=yes
     */
    @Schema(allowableValues = {"0", "1"}, description = "0=no/1=yes", defaultValue = "0")
    @Column(name = "f_is_accepted", nullable = false)
    private Integer accepted = 0;

    @Column(name = "f_start_date")
    private Date startDate;

    @Column(name = "f_end_date")
    private Date endDate;

    @Column(name = "f_guest_number", nullable = false)
    private Integer guestNumber = 0;

}
