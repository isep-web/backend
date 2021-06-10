package com.isep.project.repository;

import com.isep.project.entity.Message;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/5/5
 */
public interface MessageRepository extends JpaRepository<Message, Long>,
        JpaSpecificationExecutor<Message>
{
    Optional<Message> findBySourceUserId(Long userId);

    Optional<Message> findByTargetUserId(Long userId);

}