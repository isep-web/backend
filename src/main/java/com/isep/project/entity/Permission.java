package com.isep.project.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Xuan MIAO
 */
@Data
@Entity
@Table(name = "t_permission")
@EqualsAndHashCode(callSuper = true)
public class Permission extends BaseEntity implements Serializable, Cloneable
{


    private static final long serialVersionUID = 1348353645294712197L;

    @Column(name = "f_name", nullable = false)
    private String name;

    @Column(name = "f_url")
    private String url;

//    /**
//     * 1=page/2=button
//     */
//    @Column(name = "f_type", nullable = false)
//    private Integer type;
//
//    @Column(name = "f_permission")
//    private String permission;

    @Column(name = "f_method")
    private String method;

//    @Column(name = "f_sort", nullable = false)
//    private String sort;
//
//    @Column(name = "f_parent_id", nullable = false)
//    private Long parentId;

//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @ManyToMany(mappedBy = "permissions", fetch = FetchType.EAGER)
//    private Set<Role> roles;

    @Override
    public Object clone()
    {
        Permission p = null;
        try
        {
            p = (Permission) super.clone();
        } catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        return p;
    }
}
