package fiap.tds.models;

public class Login {
    private String email;
    private String senha;
    private int ativo;
    private Perfil perfil;

    public Login() {
    }

    public Login(String email, String senha, int ativo, Perfil perfil) {
        this.email = email;
        this.senha = senha;
        this.ativo = ativo;
        this.perfil = perfil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "Login{" +
                "email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", ativo=" + ativo +
                ", perfil=" + perfil +
                '}';
    }
}
