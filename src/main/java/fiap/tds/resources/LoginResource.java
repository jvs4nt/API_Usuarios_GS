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

    @PUT
    @Path("update-login/{email}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateLogin(@PathParam("email") String email, LoginData loginData) {
        try {
//            loginData.getLogin().setEmail(email); // Atualiza o email do login com o email da URL
            int rowsAffected = loginRepo.updateLogin(loginData.getLogin(), loginData.getPerfil());
            if (rowsAffected > 0) {
                return Response.ok("Login atualizado").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Login não encontrado").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar login").build();
        }
    }

    @DELETE
    @Path("delete-login/{email}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteLogin(@PathParam("email") String email) {
        try {
            int rowsAffected = loginRepo.deleteLogin(email);
            if (rowsAffected > 0) {
                return Response.ok("Login excluído").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Login não encontrado").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao excluir login").build();
        }
    }
}
