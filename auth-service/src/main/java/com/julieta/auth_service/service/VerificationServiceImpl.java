package com.julieta.auth_service.service;

import com.julieta.auth_service.model.User;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class VerificationServiceImpl implements VerificationService {

    @Value("${twilio.account_sid}")
    private String accountSid;

    @Value("${twilio.auth_token}")
    private String authToken;

    @Value("${twilio.phone_number}")
    private String twilioPhoneNumber;

    @Override
    public void sendVerificationCode(User user, String code) {
        Twilio.init(accountSid, authToken);
        Message message = Message.creator(
                new PhoneNumber("whatsapp:+" + user.getPhoneNumber()),
                new PhoneNumber("whatsapp:+" + twilioPhoneNumber),
                "Your verification code is: " + code
        ).create();

    }
}
