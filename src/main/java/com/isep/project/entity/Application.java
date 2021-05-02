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
@Table(name = "application")
public class Application implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "from", nullable = false)
    private Integer from;

    @Column(name = "to", nullable = false)
    private Integer to;

    @Column(name = "id_house", nullable = false)
    private Integer idHouse;

    @Column(name = "apply_date", nullable = false)
    private Date applyDate;

    @Column(name = "is_accepted", nullable = false)
    private Boolean accepted;

    @Column(name = "accept_date")
    private Date acceptDate;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "guests")
    private Integer guests;

}
