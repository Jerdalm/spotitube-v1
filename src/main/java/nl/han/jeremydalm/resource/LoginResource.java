package nl.han.jeremydalm.resource;

import nl.han.jeremydalm.datasource.LoginDAO;
import nl.han.jeremydalm.service.TokenService;
import nl.han.jeremydalm.service.dto.LoginRequestDTO;
import nl.han.jeremydalm.service.dto.LoginResponseDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginResource {
    private LoginDAO loginDAO;
    private TokenService tokenService;

    @Inject
    public void setLoginDAO(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    @Inject
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequestDTO request) {
        if (request == null || request.getUser() == null || request.getPassword() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        loginDAO.verifyLogin(request.getUser(), request.getPassword());
        tokenService.generateToken();

        return Response.status(Response.Status.OK)
                .entity(new LoginResponseDTO(tokenService.getToken(), request.getUser()))
                .build();
    }
}
