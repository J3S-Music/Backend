package com.j3s.helper;

import io.restassured.specification.RequestSpecification;
import org.apache.commons.net.ntp.TimeStamp;

import java.sql.Timestamp;

public class SpotifyLinkMock {
    private String currentBearer;
    private TimeStamp bearerDead;
    private String clientAndSecret;

    private User currentUser;

    public SpotifyLinkMock(String clientAndSecret){
        this.currentUser = new User();
        this.clientAndSecret = clientAndSecret;
        refresh();
    }

    private void refresh(){
        this.currentBearer = APIHelper.getBearerToken(currentUser, this.clientAndSecret);
        setBearerDead(System.currentTimeMillis());
    }

    public void kill(){
        this.currentBearer = null;
    }

    private void getReady(){
        if(bearerDead.compareTo(new TimeStamp(System.currentTimeMillis()))<=0){
            refresh();
        }
    }

    private void setBearerDead(long now){
        this.bearerDead = new TimeStamp(now+3500000l);
    }

    public String getCurrentBearer(){
        getReady();
        return this.currentBearer;
    }
}
