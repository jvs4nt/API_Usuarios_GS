package fiap.tds.services;

import fiap.tds.models.Endereco;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class ViaCEPService {
    private static final String BASE_URL = "https://viacep.com.br/ws";

    public Endereco getEndereco(String cep) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(BASE_URL).path(cep).path("json");

        Response response = target.request(MediaType.APPLICATION_JSON).get();

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }

        return response.readEntity(Endereco.class);
    }
}
