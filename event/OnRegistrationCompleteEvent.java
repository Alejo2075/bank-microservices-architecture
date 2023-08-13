package com.julieta.auth_service.event;

import com.julieta.auth_service.dto.RegistrationRequest;
import com.julieta.auth_service.model.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

import static com.julieta.auth_service.util.UserConverter.convertFromRegistrationRequestToUser;


@Data
@Builder
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private RegistrationRequest request;

    public OnRegistrationCompleteEvent(RegistrationRequest request){
        super(request);
        this.request = request;
    }

    public User getUser(){
        return UserConverter.convertFromRegistrationRequestToUser(request);
    }


}
