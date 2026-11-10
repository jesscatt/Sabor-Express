package cardapio;

import excecao.PrecoInvalidoException;

public class PratoExecutivo extends Prato {

    public PratoExecutivo(String nome, double precoBase, int tempoPreparoMinutos)
            throws PrecoInvalidoException {
        super(nome, precoBase, tempoPreparoMinutos);
    }

    @Override
    public double calcularPreco() {
        return getPrecoBase();
    }
}
