package com.isep.project.repository;

import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/5/5
 */
@NoRepositoryBean
public interface OnlyGetAllRepository<T, ID> extends JpaRepository<T, ID>
{

    @Override
    @RestResource(exported = false)
    void delete(@NotNull T a);

    @Override
    @RestResource(exported = false)
    <S extends T> S save(@NotNull S var1);

    @Override
    @RestResource(exported = false)
    Optional<T> findById(@NotNull ID id);

}