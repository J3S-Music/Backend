package com.j3s.helper;


import org.apache.commons.net.ntp.TimeStamp;


public class SpotifyLinkMock {
    private String currentBearer;
    private TimeStamp bearerDead;
    private String clientAndSecret;

    private final User currentUser;

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
        this.clientAndSecret = null;
    }

    private void getReady(){
        if(bearerDead.compareTo(new TimeStamp(System.currentTimeMillis()))<=0){
            refresh();
        }
    }

    private void setBearerDead(long now){
        this.bearerDead = new TimeStamp(now+3559999l);
    }

    public String getCurrentBearer(){
        getReady();
        return this.currentBearer;
    }
}
