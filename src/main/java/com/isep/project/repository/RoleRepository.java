package com.isep.project.repository;

import com.isep.project.entity.Role;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/5
 */
@RepositoryRestResource(exported = false)
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role>
{

    /**
     * Find role by user id
     *
     * @param userId user id
     * @return role list
     */
    @Query(value = "SELECT t_role.* FROM t_role,t_user,t_user__role WHERE t_user.f_id = "
            + "t_user__role.f_user_id AND t_role.f_id = t_user__role.f_role_id AND t_user.f_id = "
            + ":userId"
            , nativeQuery = true)
    List<Role> selectByUserId(@Param("userId") Long userId);

}