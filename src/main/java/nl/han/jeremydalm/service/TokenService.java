package nl.han.jeremydalm.service;

import javax.ejb.Singleton;
import java.util.concurrent.ThreadLocalRandom;

@Singleton
public class TokenService {
    public String token;

    public TokenService() {
    }

    public String getToken() {
        return token;
    }

    public void generateToken() {
        int rand = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE/2, Integer.MAX_VALUE);
        this.token =  String.valueOf(rand);
    }

    public void verifyToken(String token) {
        if (this.token == null || !this.token.equals(token))
            throw new InvalidTokenException();
    }
}
