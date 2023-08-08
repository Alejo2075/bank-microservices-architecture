package com.julieta.auth_service.events;

import com.julieta.auth_service.dtos.RegistrationRequest;
import com.julieta.auth_service.models.User;
import lombok.*;
import org.springframework.context.ApplicationEvent;


@Data
@Builder
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private RegistrationRequest request;

    public OnRegistrationCompleteEvent(RegistrationRequest request){
        super(request);
        this.request = request;
    }

    public User getUser(){
        return User.builder()
                .id(request.getId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .build();
    }


}
