package com.jet.devs.service;

import com.jet.devs.dto.UsersDTO;

/**
 * Author: ab
 * Date Time: 07/11/23
 */
public interface UsersService {

    boolean isUserAdmin(String username);

    UsersDTO getUserDetail(String username);
}
