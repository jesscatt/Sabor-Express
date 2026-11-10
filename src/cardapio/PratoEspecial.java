package cardapio;

import excecao.PrecoInvalidoException;

public class PratoEspecial extends Prato {
    private final double custoIngredientePremium;

    public PratoEspecial(String nome, double precoBase, int tempoPreparoMinutos,
                         double custoIngredientePremium) throws PrecoInvalidoException {
        super(nome, precoBase, tempoPreparoMinutos);
        if (custoIngredientePremium < 0) {
            throw new PrecoInvalidoException(
                "Custo premium negativo para o prato " + nome + ": R$ " + custoIngredientePremium);
        }
        this.custoIngredientePremium = custoIngredientePremium;
    }

    public double getCustoIngredientePremium() { return custoIngredientePremium; }

    @Override
    public double calcularPreco() {
        return getPrecoBase() + custoIngredientePremium;
    }
}
