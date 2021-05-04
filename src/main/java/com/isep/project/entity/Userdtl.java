package com.isep.project.entity;

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
@Table(name = "t_userdtl")
@EntityListeners(AuditingEntityListener.class)
public class Userdtl implements Serializable
{

    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "f_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Column(name = "f_created_time")
    private Date createdTime;

    @Column(name = "f_last_updated_time")
    private Date lastUpdatedTime;

    @Column(name = "f_location")
    private String location;

    @Column(name = "f_picture")
    private String picture;

}
