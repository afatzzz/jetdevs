package com.jet.devs.service.impl;

import com.jet.devs.dto.UsersDTO;
import com.jet.devs.model.Users;
import com.jet.devs.repository.UsersRepository;
import com.jet.devs.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Author: ab
 * Date Time: 07/11/23
 */
@Service
@Slf4j
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public boolean isUserAdmin(String username) {
        Users users = usersRepository.findByUsername(username);
        boolean isAdmin = false;
        if (users != null) {
            if (users.getRole().equals("ADMIN")) {
                log.info("You're login as ADMIN");
                isAdmin = true;
            } else {
                log.info("You're login as non ADMIN");
            }
        }
        return isAdmin;
    }

    @Override
    public UsersDTO getUserDetail(String username) {
        return null;
    }
}
