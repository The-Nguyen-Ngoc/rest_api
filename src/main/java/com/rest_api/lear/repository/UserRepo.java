package com.rest_api.lear.repository;

import com.rest_api.lear.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

    User findByEmail(String email);
}
