package com.jet.devs.repository;

import com.jet.devs.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: ab
 * Date Time: 07/11/23
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByUsername(String username);
}
