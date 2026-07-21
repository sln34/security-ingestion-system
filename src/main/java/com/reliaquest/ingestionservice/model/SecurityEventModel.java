package com.reliaquest.ingestionservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

import java.time.Instant;

public class SecurityEventModel {

    //timeStamp must not be null
    //timeStamp @pastOrPresent must also be there so there is no timestamp claiming to
    //be in the future
    @NotNull @PastOrPresent
    private Instant timeStamp;

    //Not blank is used here and it has the same function as the username field
    //@Pattern regexp is used as a way to catch if the string comming in is a valid ip
    @NotBlank @Pattern(regexp = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$")
    private String sourceIp;

    //catches whether or not the username is empty, whitespace errors, and null
    @NotBlank
    private String username;

    private boolean success;

    public SecurityEventModel() {
        //no arguement constructor, needed to deserialize incoming JSON into object
    }

    public SecurityEventModel(Instant timeStamp, String sourceIp, String username, boolean success){
        this.sourceIp = sourceIp;
        this.timeStamp = timeStamp;
        this.username = username;
        this.success = success;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Instant timeStamp){
        this.timeStamp = timeStamp;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success){
        this.success = success;
    }

}
