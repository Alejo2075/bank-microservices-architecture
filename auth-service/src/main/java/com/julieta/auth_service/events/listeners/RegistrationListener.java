package com.julieta.auth_service.events.listeners;

import com.julieta.auth_service.entities.VerificationCode;
import com.julieta.auth_service.events.OnRegistrationCompleteEvent;
import com.julieta.auth_service.models.User;
import com.julieta.auth_service.repositories.VerificationCodeRepository;
import com.julieta.auth_service.services.SmsService;
import com.julieta.auth_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    private final SmsService smsService;
    private final VerificationCodeRepository codeRepository;

    @Autowired
    public RegistrationListener(SmsService smsService, VerificationCodeRepository codeRepository) {
        this.smsService = smsService;
        this.codeRepository = codeRepository;
    }

    @Override
    public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
        confirmRegistration(event);
    }

    private void confirmRegistration(final OnRegistrationCompleteEvent event) {
        final User user = event.getUser();
        final String code = generateVerificationCode();

//        smsService.sendVerificationCode(user.getPhoneNumber(), code);

        VerificationCode verificationCode = VerificationCode.builder()
                .userId(user.getId())
                .code(code)
                .expiryDate(VerificationCode.calculateExpiryDate())
                .build();

        codeRepository.save(verificationCode);
    }

    private String generateVerificationCode() {
        return String.valueOf((int) (Math.random() * 900000) + 100000);
    }
}
