package classes;

public class Contato {

    private int id;
    private final String nome;
    private final String sobrenome;
    private final String telefone;
    private final String email;

    public Contato (String nome, String sobrenome, String telefone, String email){
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.email = email;
    }

    public Contato (int id, String nome, String sobrenome, String telefone, String email){
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }


}
