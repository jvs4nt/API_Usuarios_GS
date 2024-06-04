package fiap.tds.resources;

import fiap.tds.models.Login;
import fiap.tds.models.Perfil;
import fiap.tds.repositories.LoginRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("login")
public class LoginResource {
    private LoginRepository loginRepo;

    public LoginResource() {
        this.loginRepo = new LoginRepository();
    }

    @GET
    @Path("get-login/{email}/{senha}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getLogin(@PathParam("email") String email, @PathParam("senha") String senha){
        var login = loginRepo.getLogin(email, senha);
        if (login == null) {
            return "Login not found";
        }
        return login.toString();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createLogin(Login login, Perfil perfil){
        loginRepo.createLogin(login, perfil);
        return "Login created";
    }
}
