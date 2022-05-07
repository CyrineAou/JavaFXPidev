/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.pidevuser.utils;

/**
 *
 * @author bilel
 */
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class sendSMS {

    public void sendSms(String message_, String number) throws UnsupportedEncodingException, MalformedURLException, IOException {

   String AUTH_TOKEN = "f441e1545a15a8d9f6f320ea41a68f15";
    String ACCOUNT_SID = "AC8ec8f91331504ff8d293c69b8940bc2b";


    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    Message message = Message.creator(new PhoneNumber("+216"+number),
        new PhoneNumber("+17692106361"), 
        message_).create();

    System.out.println(message.getSid());
}

    
}