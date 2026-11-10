package excecao;

// Exceção própria do restaurante: preço negativo não entra no cardápio
public class PrecoInvalidoException extends Exception {
    public PrecoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
