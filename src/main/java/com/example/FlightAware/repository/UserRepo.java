package com.example.FlightAware.repository;

import com.example.FlightAware.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    User findByEmailIdIgnoreCase(String emailId);
}
