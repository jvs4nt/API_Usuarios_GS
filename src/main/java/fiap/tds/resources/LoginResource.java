package fiap.tds.resources;

import fiap.tds.models.Login;
import fiap.tds.models.LoginData;
import fiap.tds.models.Perfil;
import fiap.tds.repositories.LoginRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("login")
public class LoginResource {
    private LoginRepository loginRepo;

    public LoginResource() {
        this.loginRepo = new LoginRepository();
    }

    @GET
    @Path("get-login/{email}/{senha}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogin(@PathParam("email") String email, @PathParam("senha") String senha) {
        var login = loginRepo.getLogin(email, senha);
        if (login == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("{\"message\":\"Login not found\"}").build();
        }
        return Response.ok(login).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createLogin(LoginData loginData) {
        try {
            loginRepo.createLogin(loginData.getLogin(), loginData.getPerfil());
            return Response.ok("Login criado").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao criar login").build();
        }
    }
}
