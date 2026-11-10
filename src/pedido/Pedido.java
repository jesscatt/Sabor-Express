package pedido;

import cardapio.Prato;
import contrato.Precificavel;
import modelo.Cliente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido implements Precificavel {
    private static int proximoNumero = 1;

    private final int numero;
    private final Cliente cliente;
    private final LocalDate data;
    private final List<Prato> pratos = new ArrayList<>();

    public Pedido(Cliente cliente, LocalDate data) {
        if (cliente == null) {
            throw new IllegalArgumentException("Pedido sem cliente não pode existir.");
        }
        this.numero = proximoNumero++;
        this.cliente = cliente;
        this.data = data;
    }

    // Mantém a ordem em que os pratos foram escolhidos
    public void adicionar(Prato prato) {
        pratos.add(prato);
    }

    public int getNumero() { return numero; }
    public Cliente getCliente() { return cliente; }
    public LocalDate getData() { return data; }

    public List<Prato> getPratos() {
        return List.copyOf(pratos);
    }

    // Percorre pela referência da mãe: não pergunta o tipo de ninguém
    @Override
    public double calcularPreco() {
        double total = 0;
        for (Prato p : pratos) {
            total += p.calcularPreco();
        }
        return total;
    }
}
