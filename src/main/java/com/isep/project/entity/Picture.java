package com.isep.project.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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


}