package fiap.tds.resources;

import fiap.tds.models.Endereco;
import fiap.tds.services.ViaCEPService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("viacep")
public class ViaCEPResource {
    private ViaCEPService viaCEPService;

    public ViaCEPResource() {
        this.viaCEPService = new ViaCEPService();
    }

    @GET
    @Path("{cep}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEndereco(@PathParam("cep") String cep) {
        try {
            Endereco endereco = viaCEPService.getEndereco(cep);
            return Response.ok(endereco).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"Erro ao obter dados do CEP\"}").build();
        }
    }
}
