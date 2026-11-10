package relatorio;

import contrato.DestinoRelatorio;
import contrato.FormatadorRelatorio;
import pedido.ControlePedidos;

// D: recebe as abstrações pelo construtor. Trocar o destino não edita esta classe.
public class GeradorDeRelatorio {
    private final FormatadorRelatorio formatador;
    private final DestinoRelatorio destino;

    public GeradorDeRelatorio(FormatadorRelatorio formatador, DestinoRelatorio destino) {
        this.formatador = formatador;
        this.destino = destino;
    }

    public void gerarFechamento(ControlePedidos controle) {
        String conteudo = formatador.formatar(
            controle.getPedidos(),
            controle.calcularFaturamento(),
            controle.buscarPedidoMaisCaro()
        );
        destino.enviar(conteudo);
    }
}
