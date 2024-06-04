package fiap.tds.repositories;

import fiap.tds.models.Login;
import fiap.tds.models.Perfil;
import fiap.tds.resources.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRepository {
    private ConexaoBD conexaoBD;
    public LoginRepository() {
        this.conexaoBD = new ConexaoBD();
    }

    public Login getLogin(String email, String senha) {
        Login login = null;
        String sql = "SELECT * FROM TB_CADASTRO WHERE EMAIL = ? AND SENHA = ?";

        try (Connection conn = conexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                login = new Login();
                login.setEmail(rs.getString("EMAIL"));
                login.setSenha(rs.getString("SENHA"));
                login.setAtivo(rs.getInt("ATIVO"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return login;
    }

    public int createLogin(Login login, Perfil perfil) {
        String sql = "INSERT INTO TB_CADASTRO (EMAIL, SENHA, ATIVO, NOME, IDADE, ENDERECO, TELEFONE, CPF) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = conexaoBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login.getEmail());
            stmt.setString(2, login.getSenha());
            stmt.setInt(3, 1);
            stmt.setString(4, perfil.getNome());
            stmt.setString(5, perfil.getIdade());
            stmt.setString(6, perfil.getEndereco());
            stmt.setString(7, perfil.getTelefone());
            stmt.setString(8, perfil.getCpf());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
