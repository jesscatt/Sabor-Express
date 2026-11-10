package contrato;

import pedido.Pedido;

import java.util.List;

// D: o gerador depende disto, não de um formato concreto
public interface FormatadorRelatorio {
    String formatar(List<Pedido> pedidos, double faturamento, Pedido maisCaro);
}
