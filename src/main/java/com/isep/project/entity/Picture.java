package com.isep.project.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.content.commons.annotations.ContentId;
import org.springframework.content.commons.annotations.ContentLength;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author Xuan MIAO
 */
@Data
@Entity
@Table(name = "t_picture")
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class Picture extends BaseEntity implements Serializable
{

    private static final long serialVersionUID = -9057574425621950191L;

    @ContentId
    @Column(name = "f_content_id")
    private String contentId;

    @ContentLength
    @Column(name = "f_content_length")
    private long contentLength;

    @Column(name = "f_mime_type", nullable = false)
    private String mimeType = "text/plain";

    @Schema(allowableValues = {"0", "1"}, description = "0=avatar/1=house_photo", defaultValue = "1")
    @Column(name = "f_type",nullable = false)
    private int type = 1;

    @OneToOne
    @JoinColumn(name="f_user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="f_house_id")
    private House house;

}