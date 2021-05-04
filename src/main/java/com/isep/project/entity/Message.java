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
@Table(name = "t_message")
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class Message extends BaseEntity implements Serializable
{

    private static final long serialVersionUID = -2704401027540169412L;

    @Column(name = "f_source_user_id", nullable = false)
    private Long sourceUserId;

    @Column(name = "f_target_user_id", nullable = false)
    private Long targetUserId;

    /**
     * 0=no/1=yes
     */
    @Schema(allowableValues = {"0", "1"}, description = "0=no/1=yes", defaultValue = "0")
    @Column(name = "f_is_read", nullable = false)
    private Integer read = 0;

    @Column(name = "f_content", nullable = false)
    private String content = "";

}
