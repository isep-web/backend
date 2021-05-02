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
@Table(name = "house")
public class House implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_user", nullable = false)
    private Integer idUser;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "is_verified", nullable = false)
    private Boolean verified;

    @Column(name = "guests", nullable = false)
    private Integer guests;

    @Column(name = "publish_date", nullable = false)
    private Date publishDate;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "rooms", nullable = false)
    private String rooms;

    @Column(name = "amenities", nullable = false)
    private String amenities;

    @Column(name = "rules")
    private String rules;

}
