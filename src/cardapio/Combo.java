package cardapio;

import excecao.PrecoInvalidoException;

public class Combo extends Prato {
    private static final double DESCONTO = 0.15;

    public Combo(String nome, double precoBase, int tempoPreparoMinutos)
            throws PrecoInvalidoException {
        super(nome, precoBase, tempoPreparoMinutos);
    }

    @Override
    public double calcularPreco() {
        return getPrecoBase() * (1 - DESCONTO);
    }
}
