package com.isep.project.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
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
@Table(name = "t_user")
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity implements Serializable
{

    private static final long serialVersionUID = 8741446885207941178L;

    @Column(name = "f_user_name", nullable = false)
    private String userName;

    @Column(name = "f_password", nullable = false)
    private String password;

    /**
     * 0=unset/1=user/2=admin
     */
    @Column(name = "f_role", nullable = false)
    @Schema(allowableValues = {"0",
                               "1"}, description = "0=unset/1=user/2=admin", defaultValue = "0")
    private Integer role = 0;

}
