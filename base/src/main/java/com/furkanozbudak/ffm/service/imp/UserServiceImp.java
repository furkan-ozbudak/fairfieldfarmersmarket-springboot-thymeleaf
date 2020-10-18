package com.furkanozbudak.ffm.service.imp;

import com.furkanozbudak.ffm.model.UserEntity;
import com.furkanozbudak.ffm.repository.UserRepository;
import com.furkanozbudak.ffm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<UserEntity> findAllByRole(String role) {
        return userRepository.findAllByRole(role);
    }

    @Override
    public UserEntity findByIdAndRole(Long id, String role) {
        return userRepository.findByIdAndRole(id, role);
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserEntity findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}
