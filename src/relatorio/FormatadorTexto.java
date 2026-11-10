package relatorio;

import cardapio.Prato;
import contrato.FormatadorRelatorio;
import pedido.Pedido;

import java.util.List;

public class FormatadorTexto implements FormatadorRelatorio {

    @Override
    public String formatar(List<Pedido> pedidos, double faturamento, Pedido maisCaro) {
        StringBuilder sb = new StringBuilder();
        sb.append("========== FECHAMENTO DO DIA - SABOREXPRESS ==========\n");
        for (Pedido p : pedidos) {
            sb.append(String.format("Pedido #%d - %s - %s (%s)%n",
                p.getNumero(), p.getData(), p.getCliente().getNome(),
                p.getCliente().getEndereco()));
            for (Prato prato : p.getPratos()) {
                sb.append(String.format("   %-28s %3d min   R$ %8.2f%n",
                    prato.getNome(), prato.getTempoPreparoMinutos(), prato.calcularPreco()));
            }
            sb.append(String.format("   %-28s           R$ %8.2f%n", "Total do pedido", p.calcularPreco()));
        }
        sb.append("======================================================\n");
        sb.append(String.format("FATURAMENTO TOTAL: R$ %.2f%n", faturamento));
        sb.append(String.format("PEDIDO MAIS CARO: #%d de %s - R$ %.2f%n",
            maisCaro.getNumero(), maisCaro.getCliente().getNome(), maisCaro.calcularPreco()));
        return sb.toString();
    }
}
