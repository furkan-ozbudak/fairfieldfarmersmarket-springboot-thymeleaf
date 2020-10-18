package com.furkanozbudak.ffm.repository;

import com.furkanozbudak.ffm.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findAllByRole(String role);
    UserEntity findByIdAndRole(Long id, String role);
    UserEntity findByEmailAndPassword(String email, String password);

}
