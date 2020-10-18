package com.furkanozbudak.ffm.service;

import com.furkanozbudak.ffm.model.UserEntity;

public interface AdminService {
    UserEntity approveSellerRegistration(UserEntity seller);
}
