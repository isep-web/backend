package com.isep.project.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/10
 */
@Data
@Entity
@Table(name = "t_role")
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity implements Serializable
{

    private static final long serialVersionUID = 8104475506421418332L;

    @Column(name = "f_name", nullable = false)
    private String name;

    @Column(name = "f_description")
    private String description;

//    @ManyToMany(targetEntity = Permission.class, cascade = {CascadeType.PERSIST,
//                                                            CascadeType.REFRESH})
//    @JoinTable(name = "t_role__permission",
//            joinColumns = {@JoinColumn(name = "f_role_id", referencedColumnName = "f_id")},
//            inverseJoinColumns = {
//                    @JoinColumn(name = "f_permission_id", referencedColumnName = "f_id")}
//    )
//    private Set<Permission> permissions;
//
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
//    private Set<User> users;
}
