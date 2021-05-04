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
import javax.persistence.Table;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Table(name = "t_user")
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "f_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "f_user_name", nullable = false)
    private String userName;

    @Column(name = "f_password", nullable = false)
    private String password;

    /**
     * 0=unset/1=user/2=admin
     */
    @Column(name = "f_role", nullable = false)
    @Schema(allowableValues = "0,1,2", description = "0=unset/1=user/2=admin", defaultValue = "0")
    private Integer role = 0;

    @Column(name = "f_created_time")
    private Date createdTime;

    @Column(name = "f_last_updated_time")
    private Date lastUpdatedTime;

}
