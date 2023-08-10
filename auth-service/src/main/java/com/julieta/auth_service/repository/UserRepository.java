package com.julieta.auth_service.repository;

import com.julieta.auth_service.entity.UserEntity;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface UserRepository extends CrudRepository<UserEntity, String> {
    Optional<UserEntity> findByPhoneNumber(String phoneNumber);

}
