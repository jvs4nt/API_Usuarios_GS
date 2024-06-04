package fiap.tds.resources;

import fiap.tds.models.Perfil;
import fiap.tds.repositories.PerfilRepository;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

public class PerfilResource {
    private PerfilRepository perfilRepo;

    public PerfilResource() {
        this.perfilRepo = new PerfilRepository();
    }

    @GET
    @Path("get-perfil/{cpf}")
    public String getPerfil(@PathParam("cpf") String cpf){
        var perfil = perfilRepo.getPerfil(cpf);
        if (perfil == null) {
            return "Perfil not found";
        }
        return perfil.toString();
    }
}
