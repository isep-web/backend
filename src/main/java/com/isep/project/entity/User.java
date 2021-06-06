package com.isep.project.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author : Xuan MIAO
 * @version : 3.0.0
 * @date : 2021/5/18
 */
@Data
@Entity
@Table(name = "t_user")
@EqualsAndHashCode(callSuper = true, exclude = {"sentApplications", "receivedApplications"})
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity implements Serializable
{

    private static final long serialVersionUID = -182216524554701152L;

    @Column(name = "f_user_name", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String userName;

    @Column(name = "f_password", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * 0=unset/1=user/2=admin
     */
    @Column(name = "f_role", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer role = 0;

    @Column(name = "f_display_name", nullable = false)
    private String displayName = "";

    @Column(name = "f_email", nullable = false)
    private String email = "";

    @Column(name = "f_phone", nullable = false)
    private String phone = "";

    @Column(name = "f_gender", nullable = false)
    private String gender = "";

    @Column(name = "f_language", nullable = false)
    private String language = "";

    @Column(name = "f_description", nullable = false)
    private String description = "";

    @Column(name = "f_icon", nullable = false)
    private String icon = "";

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

}
