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
@Table(name = "t_amenity")
@EntityListeners(AuditingEntityListener.class)
public class Amenity implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "f_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "f_name", nullable = false)
    private String name = "";

    @Column(name = "f_detail", nullable = false)
    private String detail = "";

    @Column(name = "f_created_time")
    private Date createdTime;

    @Column(name = "f_last_updated_time")
    private Date lastUpdatedTime;

}
