package fiap.tds.resources;

import fiap.tds.models.Perfil;
import fiap.tds.repositories.PerfilRepository;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("perfil")
public class PerfilResource {
    private PerfilRepository perfilRepo;

    public PerfilResource() {
        this.perfilRepo = new PerfilRepository();
    }

    @GET
    @Path("get-perfil/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerfil(@PathParam("cpf") String cpf) {
        var perfil = perfilRepo.getPerfil(cpf);
        if (perfil == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("{\"message\":\"Perfil not found\"}").build();
        }
        return Response.ok(perfil).build();
    }
}
