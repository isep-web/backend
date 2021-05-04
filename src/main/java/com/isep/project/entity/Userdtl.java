package com.isep.project.entity;

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
@Table(name = "t_userdtl")
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class Userdtl extends BaseEntity implements Serializable
{

    private static final long serialVersionUID = 6948443191489746155L;

    @Column(name = "f_user_id", nullable = false)
    private Long userId;

    @Column(name = "f_display_name", nullable = false)
    private String displayName = "";

    @Column(name = "f_email", nullable = false)
    private String email = "";

    @Column(name = "f_phone", nullable = false)
    private String phone = "";

    @Column(name = "f_birth_date")
    private Date birthDate;

    @Column(name = "f_gender", nullable = false)
    private String gender = "";

    @Column(name = "f_language", nullable = false)
    private String language = "";

    @Column(name = "f_description", nullable = false)
    private String description = "";

    @Column(name = "f_location")
    private String location;

    @Column(name = "f_picture")
    private String picture;

}
