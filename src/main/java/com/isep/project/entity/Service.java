package com.isep.project.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author : Xuan MIAO
 * @version : 2.0.0
 * @date : 2021/5/18
 */
@Data
@Entity
@Table(name = "t_service")
@EqualsAndHashCode(callSuper = true, exclude = "houses")
@EntityListeners(AuditingEntityListener.class)
public class Service extends BaseEntity implements Serializable
{

    private static final long serialVersionUID = 6240737854638474134L;

    @Column(name = "f_name", nullable = false)
    private String name = "";

    @Column(name = "f_detail", nullable = false)
    private String detail = "";

    @ManyToMany(mappedBy = "services", fetch = FetchType.LAZY)
    private Set<House> houses;

}
