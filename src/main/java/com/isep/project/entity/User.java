package com.isep.project.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Xuan MIAO
 */
@Data
@Entity
@Table(name = "t_user")
@EqualsAndHashCode(callSuper = true, exclude = {"sentApplications", "receivedApplications",
                                                "avatar"})
public class User extends BaseEntity implements Serializable
{


    private static final long serialVersionUID = 5780310943968550L;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "f_username", nullable = false)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "f_password", nullable = false)
    private String password;

    /**
     * 0=banned/1=normal
     */
    @Column(name = "f_status", nullable = false)
    private Integer status = 1;

    @Column(name = "f_display_name", nullable = false)
    private String displayName = "";

    @Column(name = "f_email", nullable = false)
    private String email = "";

    @Column(name = "f_phone", nullable = false)
    private String phone = "";

    @Column(name = "f_sex", nullable = false)
    private Integer sex = 0;

    @Column(name = "f_language", nullable = false)
    private String language = "";

    @Column(name = "f_description", nullable = false)
    private String description = "";

    @Column(name = "f_location")
    private String location;

    @OneToMany(mappedBy = "sourceUser", fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Application> sentApplications;

    @OneToMany(mappedBy = "targetUser", fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Application> receivedApplications;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Picture avatar;

//    @ManyToMany(targetEntity = Role.class, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
//    @JoinTable(name = "t_user__role",
//            joinColumns = {@JoinColumn(name = "f_user_id", referencedColumnName = "f_id")},
//            inverseJoinColumns = {@JoinColumn(name = "f_role_id", referencedColumnName = "f_id")}
//    )
//    private Set<Role> roles;

//    @Override
//    public String toString()
//    {
//        return "User{" +
//                "username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", status=" + status +
//                ", displayName='" + displayName + '\'' +
//                ", email='" + email + '\'' +
//                ", phone='" + phone + '\'' +
//                ", sex=" + sex +
//                ", language='" + language + '\'' +
//                ", description='" + description + '\'' +
//                ", location='" + location + '\'' +
//                '}';
//    }
}
