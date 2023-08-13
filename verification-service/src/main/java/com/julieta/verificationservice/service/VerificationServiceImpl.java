package com.julieta.verificationservice.service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.julieta.auth_service.model.Code;
import com.julieta.auth_service.repository.VerificationCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerificationServiceImpl implements VerificationService {


    private final AmazonSimpleEmailService amazonSimpleEmailService;
    private final VerificationCodeRepository verificationCodeRepository;

    @Override
    public void sendVerificationEmailCode(Code userCode) {
        String bodyMessage = "Dear User,\n\n"
                + "Thank you for registering with us. Below is your email verification code:\n\n"
                + userCode.getCode() + "\n\n"
                + "Please use this code to complete your email verification. Note that this code will expire in 24 hours.\n\n"
                + "If you have any questions or need further assistance, please contact our support team.\n\n"
                + "Best regards,\n"
                + "Your Company Name";

        SendEmailRequest emailRequest = new SendEmailRequest()
                .withDestination(new Destination().withToAddresses(userCode.getEmail()))
                .withMessage(new Message()
                        .withBody(new Body()
                                .withText(new Content()
                                        .withCharset("UTF-8")
                                        .withData(bodyMessage)))
                        .withSubject(new Content()
                                .withCharset("UTF-8")
                                .withData("Verification Code")))
                .withSource("alejsant75@gmail.com");

        amazonSimpleEmailService.sendEmail(emailRequest);
    }

}
