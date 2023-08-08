package com.julieta.auth_service.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements SmsService{

    @Value("${twilio.account_sid}")
    private String accountSid;

    @Value("${twilio.auth_token}")
    private String authToken;

    @Value("${twilio.phone_number}")
    private String twilioPhoneNumber;
    @Override
    public void sendVerificationCode(String destinationPhoneNumber, String code) {
        Twilio.init(accountSid, authToken);
        Message.creator(
                new PhoneNumber(destinationPhoneNumber),
                new PhoneNumber(twilioPhoneNumber),
                "Your verification code is: " + code
        ).create();
    }
}
