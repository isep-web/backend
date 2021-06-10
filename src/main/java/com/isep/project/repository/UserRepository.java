package com.isep.project.repository;

import com.isep.project.entity.House;
import com.isep.project.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>
{

    @RestResource(exported = false)
    Optional<User> findByUsernameOrEmailOrPhone(String username, String email, String phone);

    @RestResource(exported = false)
    User findByUsername(String username);

    @RestResource(exported = false)
    @Query(value = "select u from User u where u.username = :name")
    User selectByName(@Param("name") String name);
}