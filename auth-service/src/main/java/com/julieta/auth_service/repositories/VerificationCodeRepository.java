package com.julieta.auth_service.repositories;

import com.julieta.auth_service.entities.VerificationCode;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.Instant;
import java.util.List;

@EnableScan
public interface VerificationCodeRepository extends CrudRepository<VerificationCode, String> {

}
