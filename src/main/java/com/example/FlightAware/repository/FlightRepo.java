package com.example.FlightAware.repository;

import com.example.FlightAware.model.UserIp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepo extends CrudRepository<UserIp, Long> {
}
