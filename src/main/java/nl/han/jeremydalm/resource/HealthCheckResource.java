package nl.han.jeremydalm.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/")
public class HealthCheckResource {

    @GET
    public String healthy() {
        return "Up & Running";
    }
}
