package com.projectManagement.service;

import com.projectManagement.modal.User;

public interface UserService {
    User findUserProfilByJwt(String jwt)throws Exception;

    User findUserByEmail(String email)throws Exception;

    User findUserById(Long userId)throws Exception;

    User updateUsersProjectSize(User user,int number);
}
