package nl.han.jeremydalm.resource;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/")
public class SpotitubeApp extends Application {

    @GET
    public String healthy() {
        return "hij doet t";
    }
}
