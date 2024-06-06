package fiap.tds.repositories;

import fiap.tds.models.Perfil;
import fiap.tds.resources.ConexaoBD;

import java.util.ArrayList;
import java.util.List;

public class PerfilRepository {
    private ConexaoBD conexaoBD;

    public PerfilRepository() {
        this.conexaoBD = new ConexaoBD();
    }

    public List<Perfil> getPerfis() {
        var PerfilList = new ArrayList<Perfil>();
        var sql = "SELECT * FROM TB_CADASTRO";

        try (var conn = conexaoBD.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            var rs = stmt.executeQuery();
            while (rs.next()) {
                var perfil = new Perfil();
                perfil.setNome(rs.getString("NOME"));
                perfil.setIdade(rs.getString("IDADE"));
                perfil.setEndereco(rs.getString("ENDERECO"));
                perfil.setTelefone(rs.getString("TELEFONE"));
                perfil.setCpf(rs.getString("CPF"));
                PerfilList.add(perfil);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PerfilList;
    }

    public Perfil getPerfil(String cpf) {
        Perfil perfil = null;
        var sql = "SELECT * FROM TB_CADASTRO WHERE CPF = ?";

        try (var conn = conexaoBD.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                perfil = new Perfil();
                perfil.setNome(rs.getString("NOME"));
                perfil.setIdade(rs.getString("IDADE"));
                perfil.setEndereco(rs.getString("ENDERECO"));
                perfil.setTelefone(rs.getString("TELEFONE"));
                perfil.setCpf(rs.getString("CPF"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return perfil;
    }
}
