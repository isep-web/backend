package com.isep.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : Xuan MIAO
 * @version : 4.0.0
 * @date : 2021/5/18
 */
@Data
@Entity
@Table(name = "t_house")
@EqualsAndHashCode(callSuper = true, exclude = {"applications", "photos"})
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
    private Integer published = 1;

    @Column(name = "f_guest_number", nullable = false)
    private Integer guestNumber = 0;

    @Column(name = "f_location")
    private String location;

    @ManyToMany(targetEntity = Service.class, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "t_house__service",
            joinColumns = {@JoinColumn(name = "f_house_id", referencedColumnName = "f_id")},
            inverseJoinColumns = {@JoinColumn(name = "f_service_id", referencedColumnName = "f_id")}
    )
    private Set<Service> services;

    @JsonBackReference
    @ManyToMany(targetEntity = Amenity.class, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "t_house__amenity",
            joinColumns = {@JoinColumn(name = "f_house_id", referencedColumnName = "f_id")},
            inverseJoinColumns = {@JoinColumn(name = "f_amenity_id", referencedColumnName = "f_id")}
    )
    private Set<Amenity> amenities;

    @ManyToMany(targetEntity = Constraint.class, cascade = {CascadeType.PERSIST,
                                                            CascadeType.REFRESH})
    @JoinTable(name = "t_house__constraint",
            joinColumns = {@JoinColumn(name = "f_house_id", referencedColumnName = "f_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "f_constraint_id", referencedColumnName = "f_id")}
    )
    private Set<Constraint> constraints;

    @OneToMany(mappedBy = "house", fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Application> applications;

    @OneToMany(mappedBy = "house", fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Picture> photos;
}
