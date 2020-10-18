package com.furkanozbudak.ffm.service.imp;

import com.furkanozbudak.ffm.model.UserEntity;
import com.furkanozbudak.ffm.repository.UserRepository;
import com.furkanozbudak.ffm.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImp implements AdminService{
    @Autowired
    UserRepository userRepository;

    @Override
    public UserEntity approveSellerRegistration(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }
}
