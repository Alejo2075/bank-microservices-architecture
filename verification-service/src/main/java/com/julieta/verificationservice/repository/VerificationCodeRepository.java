package com.julieta.verificationservice.repository;

import com.julieta.auth_service.entity.VerificationCode;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface VerificationCodeRepository extends CrudRepository<VerificationCode, String> {

    Optional<VerificationCode> findByEmail(String email);

}
