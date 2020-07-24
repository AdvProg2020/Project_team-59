package Controller.NetWork;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Token {
    private String tokenString;
    private long start;
    private long endTime;
    private ArrayList<String>expireToken;
    public Token() {
        Random random=new Random();
        tokenString=String.valueOf(random.nextInt()%1000000);
        start=System.currentTimeMillis();
        endTime=start+(60*60*10);
        expireToken=new ArrayList<>();
    }

    public String getTokenString() {
        long now=System.currentTimeMillis();
        if (now>endTime){
            Random random=new Random();
            expireToken.add(tokenString);
            tokenString=String.valueOf(random.nextInt()%1000000);
            start=System.currentTimeMillis();
            endTime=start+(60*60*10);
        }
        return tokenString;
    }

    public boolean isExpire(Long now){
        if (now>endTime){
            return true;
        }
        return false;
    }
    public void setTokenString(String token) {
        this.tokenString = token;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
}
