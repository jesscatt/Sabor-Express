package pedido;

import java.util.ArrayList;
import java.util.List;

// S: controle único dos pedidos do dia
public class ControlePedidos {
    private final List<Pedido> pedidos = new ArrayList<>();

    public void registrar(Pedido pedido) {
        pedidos.add(pedido);
    }

    public List<Pedido> getPedidos() {
        return List.copyOf(pedidos);
    }

    public double calcularFaturamento() {
        double total = 0;
        for (Pedido p : pedidos) {
            total += p.calcularPreco();
        }
        return total;
    }

    public Pedido buscarPedidoMaisCaro() {
        if (pedidos.isEmpty()) {
            throw new IllegalStateException("Nenhum pedido registrado no dia.");
        }
        Pedido maisCaro = pedidos.get(0);
        for (Pedido p : pedidos) {
            if (p.calcularPreco() > maisCaro.calcularPreco()) {
                maisCaro = p;
            }
        }
        return maisCaro;
    }
}
