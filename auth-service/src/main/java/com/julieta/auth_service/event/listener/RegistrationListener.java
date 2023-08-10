package com.julieta.auth_service.event.listener;

import com.julieta.auth_service.entity.VerificationCode;
import com.julieta.auth_service.event.OnRegistrationCompleteEvent;
import com.julieta.auth_service.model.User;
import com.julieta.auth_service.repository.VerificationCodeRepository;
import com.julieta.auth_service.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    private final VerificationService verificationService;
    private final VerificationCodeRepository codeRepository;

    @Autowired
    public RegistrationListener(VerificationService smsService, VerificationCodeRepository codeRepository) {
        this.verificationService = smsService;
        this.codeRepository = codeRepository;
    }

    @Override
    public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
        confirmRegistration(event);

    }

    private void confirmRegistration(final OnRegistrationCompleteEvent event) {
        final User user = event.getUser();
        final String code = generateVerificationCode();

        codeRepository.save(VerificationCode.builder()
                .userId(user.getId())
                .code(code)
                .expiryDate(VerificationCode.calculateExpiryDate())
                .build());
    }

    private String generateVerificationCode() {
        return String.valueOf((int) (Math.random() * 900000) + 100000);
    }
}
