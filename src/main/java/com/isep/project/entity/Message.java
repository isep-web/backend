package com.isep.project.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Table(name = "t_message")
@EntityListeners(AuditingEntityListener.class)
public class Message implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "f_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "f_source_user_id", nullable = false)
    private Long sourceUserId;

    @Column(name = "f_target_user_id", nullable = false)
    private Long targetUserId;

    /**
     * 0=unset; 1=text
     */
    @Schema(allowableValues = "0,1", description = "0=unset/1=text", defaultValue = "0")
    @Column(name = "f_type", nullable = false)
    private Integer type = 0;

    /**
     * 0=no/1=yes
     */
    @Schema(allowableValues = "0,1", description = "0=no/1=yes", defaultValue = "0")
    @Column(name = "f_is_readed", nullable = false)
    private Integer readed = 0;

    @Column(name = "f_content", nullable = false)
    private String content = "";

    @Column(name = "f_created_time")
    private Date createdTime;

    @Column(name = "f_last_updated_time")
    private Date lastUpdatedTime;

}
