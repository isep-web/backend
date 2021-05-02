package com.isep.project.entity;

import java.io.Serializable;
import java.sql.Date;
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
@Table(name = "message")
public class Message implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from", nullable = false)
    private Integer from;

    @Column(name = "to", nullable = false)
    private Integer to;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "is_readed", nullable = false)
    private Boolean readed;

    @Column(name = "send_time", nullable = false)
    private Date sendTime;

    @Column(name = "content", nullable = false)
    private String content;

}
