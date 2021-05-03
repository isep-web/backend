package com.isep.project.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "application")
public class Application implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from", nullable = false)
    private Long from;

    @Column(name = "to", nullable = false)
    private Long to;

    @Column(name = "id_house", nullable = false)
    private Long idHouse;

    @Column(name = "is_accepted", nullable = false)
    private Boolean accepted = Boolean.FALSE;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "guests")
    private Integer guests;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Column(name = "last_updated_date")
    private Date lastUpdatedDate;

}
