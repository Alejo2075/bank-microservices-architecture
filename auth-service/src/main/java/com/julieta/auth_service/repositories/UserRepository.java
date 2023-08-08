package com.julieta.auth_service.repositories;

import com.julieta.auth_service.entities.UserEntity;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface UserRepository extends CrudRepository<UserEntity, String> {
}
