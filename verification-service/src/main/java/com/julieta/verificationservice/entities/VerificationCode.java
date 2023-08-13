package com.julieta.verificationservice.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamoDBTable(tableName = "VerificationCode")
public class VerificationCode {


    @DynamoDBHashKey
    private String userId;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "email")
    private String email;

    @DynamoDBAttribute
    private String code;

    @DynamoDBAttribute
    private Date expiryDate;

    public static Date calculateExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 24);
        return new Date(cal.getTime().getTime());
    }

}
