package com.isep.project.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : Xuan MIAO
 * @version : 3.0.0
 * @date : 2021/5/5
 */
@Data
@Entity
@Table(name = "t_application")
@EqualsAndHashCode(callSuper = true)
public class Application extends BaseEntity implements Serializable
{

    private static final long serialVersionUID = -1076269535680923985L;

    /**
     * 0=no/1=yes
     */
    @Schema(allowableValues = {"0", "1"}, description = "0=no/1=yes", defaultValue = "0")
    @Column(name = "f_is_accepted", nullable = false, columnDefinition = "TINYINT(1)")
    private Integer accepted = 0;

    @Column(name = "f_start_date")
    private Date startDate;

    @Column(name = "f_end_date")
    private Date endDate;

    @Column(name = "f_guest_number", nullable = false)
    private Integer guestNumber = 0;

    @ManyToOne
    @JoinColumn(name = "f_source_user_id")
    private User sourceUser;

    @ManyToOne
    @JoinColumn(name = "f_target_user_id")
    private User targetUser;

    @ManyToOne
    @JoinColumn(name = "f_house_id")
    private House house;
}
