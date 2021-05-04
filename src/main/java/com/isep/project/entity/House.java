package com.isep.project.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Table(name = "t_house")
@EntityListeners(AuditingEntityListener.class)
public class House implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "f_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "f_user_id", nullable = false)
    private Long userId;

    @Column(name = "f_title", nullable = false)
    private String title = "";

    @Column(name = "f_area", nullable = false)
    private Integer area = 0;

    @Column(name = "f_description", nullable = false)
    private String description = "";

    /**
     * 0=no/1=yes
     */
    @Schema(allowableValues = "0,1", description = "0=no/1=yes", defaultValue = "0")
    @Column(name = "is_published", nullable = false)
    private Boolean published = Boolean.FALSE;

    @Column(name = "f_guest_number", nullable = false)
    private Integer guestNumber = 0;

    @Column(name = "f_created_time")
    private Date createdTime;

    @Column(name = "f_last_updated_time")
    private Date lastUpdatedTime;

    @Column(name = "f_location")
    private String location;

    @Column(name = "f_picture")
    private String picture;

}
