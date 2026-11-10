package cardapio;

import contrato.Precificavel;
import contrato.Preparavel;
import excecao.PrecoInvalidoException;

// Contrato da família (L): calcularPreco nunca lança exceção e nunca
// devolve valor negativo. Toda validação acontece no construtor.
public abstract class Prato implements Precificavel, Preparavel {
    private final String nome;
    private final double precoBase;
    private final int tempoPreparoMinutos;

    protected Prato(String nome, double precoBase, int tempoPreparoMinutos)
            throws PrecoInvalidoException {
        if (precoBase < 0) {
            throw new PrecoInvalidoException(
                "Preço base negativo para o prato " + nome + ": R$ " + precoBase);
        }
        this.nome = nome;
        this.precoBase = precoBase;
        this.tempoPreparoMinutos = tempoPreparoMinutos;
    }

    public String getNome() { return nome; }
    public double getPrecoBase() { return precoBase; }

    @Override
    public int getTempoPreparoMinutos() { return tempoPreparoMinutos; }

    // O: cada tipo de prato decide o seu preço
    @Override
    public abstract double calcularPreco();
}
