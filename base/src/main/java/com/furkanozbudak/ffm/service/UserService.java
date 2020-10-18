package com.furkanozbudak.ffm.service;

import com.furkanozbudak.ffm.model.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> findAll();
    UserEntity findById(Long id);
    List<UserEntity>findAllByRole(String role);
    UserEntity findByIdAndRole(Long id, String role);
    UserEntity save(UserEntity userEntity);
    void deleteById(Long id);
    UserEntity findByEmailAndPassword(String email, String password);

}
