package modelo;

// S: só guarda os dados do cliente
public class Cliente {
    private final String nome;
    private final String endereco;
    private final String telefone;

    public Cliente(String nome, String endereco, String telefone) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public String getTelefone() { return telefone; }
}
