package com.example.task.repository;

import com.example.task.repository.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Task repository for task different operations
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    
    @Modifying
    @Transactional
    @Query("UPDATE Task t SET t.content = :content, t.edited = :edited WHERE t.id = :id")
    int updateTaskContentAndEdited(@Param("content") String content,
                                   @Param("edited") Boolean edited,
                                   @Param("id") Integer id);
    
}
