package com.j3s.helper;


import org.apache.commons.net.ntp.TimeStamp;

/**
 * A mock simulating a working connection to spotify using the bearer token flow
 */
public class SpotifyLinkMock {
    /**
     * The current bearer toke
     */
    private String currentBearer;
    /**
     * The time the bearer token will expire
     */
    private TimeStamp bearerDead;
    /**
     * client and secret string used for bearer token retrieval
     */
    private String clientAndSecret;
    /**
     * useless?
     */
    private final User currentUser;

    /**
     * Create a new spotify mock
     * @param clientAndSecret used client and secret string
     */
    public SpotifyLinkMock(String clientAndSecret){
        this.currentUser = new User();
        this.clientAndSecret = clientAndSecret;
        refresh();
    }

    /**
     * get a new bearer token and set a new expire timestamp
     */
    private void refresh(){
        this.currentBearer = APIHelper.getBearerToken(this.clientAndSecret);
        setBearerDead(System.currentTimeMillis());
    }

    /**
     * kill the mock without removing the object [do not use normally]
     */
    public void kill(){
        this.currentBearer = null;
        this.clientAndSecret = null;
    }

    /**
     * refresh the token if necessary using {@link #refresh()}
     */
    private void getReady(){
        if(bearerDead.compareTo(new TimeStamp(System.currentTimeMillis()))<=0){
            refresh();
        }
    }

    /**
     * set the current bearer token expire timestamp
     * @param now current system time in milliseconds
     */
    private void setBearerDead(long now){
        this.bearerDead = new TimeStamp(now+3559999l);
    }

    /**
     * get the current bearer token and refresh if necessary
     * @return current working bearer token string
     */
    public String getCurrentBearer(){
        getReady();
        return this.currentBearer;
    }
}
