package com.julieta.auth_service.repository;

import com.julieta.auth_service.entity.VerificationCode;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface VerificationCodeRepository extends CrudRepository<VerificationCode, String> {

}
