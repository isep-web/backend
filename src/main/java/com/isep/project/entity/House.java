package com.isep.project.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author : Xuan MIAO
 * @version : 2.0.0
 * @date : 2021/5/18
 */
@Data
@Entity
@Table(name = "t_house")
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class House extends BaseEntity implements Serializable
{

    private static final long serialVersionUID = -6690194373523902545L;

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
    @Schema(allowableValues = {"0", "1"}, description = "0=no/1=yes", defaultValue = "0")
    @Column(name = "is_published", nullable = false)
    private Boolean published = Boolean.FALSE;

    @Column(name = "f_guest_number", nullable = false)
    private Integer guestNumber = 0;

    @Column(name = "f_location")
    private String location;

    @Column(name = "f_picture")
    private String picture;

    @ManyToMany(targetEntity = Service.class, cascade = CascadeType.ALL)
    @JoinTable(name = "t_house__service",
            joinColumns = {@JoinColumn(name = "f_house_id", referencedColumnName = "f_id")},
            inverseJoinColumns = {@JoinColumn(name = "f_service_id", referencedColumnName = "f_id")}
    )
    private Set<Service> services;

    @ManyToMany(targetEntity = Amenity.class, cascade = CascadeType.ALL)
    @JoinTable(name = "t_house__amenity",
            joinColumns = {@JoinColumn(name = "f_house_id", referencedColumnName = "f_id")},
            inverseJoinColumns = {@JoinColumn(name = "f_amenity_id", referencedColumnName = "f_id")}
    )
    private Set<Amenity> amenities;

    @ManyToMany(targetEntity = Constraint.class, cascade = CascadeType.ALL)
    @JoinTable(name = "t_house__constraint",
            joinColumns = {@JoinColumn(name = "f_house_id", referencedColumnName = "f_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "f_constraint_id", referencedColumnName = "f_id")}
    )
    private Set<Constraint> constraints;
}
