package com.julieta.auth_service.event.listener;

import com.julieta.auth_service.entity.VerificationCode;
import com.julieta.auth_service.event.OnRegistrationCompleteEvent;
import com.julieta.auth_service.exception.NotSendVerificationCodeException;
import com.julieta.auth_service.model.Code;
import com.julieta.auth_service.model.User;
import com.julieta.auth_service.repository.VerificationCodeRepository;
import com.julieta.auth_service.service.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final VerificationService verificationService;
    private final VerificationCodeRepository codeRepository;

    @Override
    public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
        confirmRegistration(event);

    }

    private void confirmRegistration(final OnRegistrationCompleteEvent event){
        final User user = event.getUser();
        final String code = generateVerificationCode();

        codeRepository.save(VerificationCode.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .code(code)
                .expiryDate(VerificationCode.calculateExpiryDate())
                .build());

        Code userCode = new Code(user.getEmail(), code);
        verificationService.sendVerificationEmailCode(userCode);
    }

    private String generateVerificationCode() {
        return String.valueOf((int) (Math.random() * 900000) + 100000);
    }
}
