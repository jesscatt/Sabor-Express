package app;

import cardapio.Combo;
import cardapio.PratoEspecial;
import cardapio.PratoExecutivo;
import excecao.PrecoInvalidoException;
import modelo.Cliente;
import pedido.ControlePedidos;
import pedido.Pedido;
import relatorio.DestinoArquivo;
import relatorio.DestinoConsole;
import relatorio.FormatadorTexto;
import relatorio.GeradorDeRelatorio;

import java.time.LocalDate;

public class Principal {
    public static void main(String[] args) {
        LocalDate hoje = LocalDate.now();
        ControlePedidos controle = new ControlePedidos();

        Cliente ana = new Cliente("Ana Souza", "Rua das Flores, 120", "(51) 99999-1111");
        Cliente bruno = new Cliente("Bruno Lima", "Av. Brasil, 455", "(51) 98888-2222");

        try {
            Pedido pedido1 = new Pedido(ana, hoje);
            pedido1.adicionar(new PratoExecutivo("Frango grelhado", 32.00, 20));
            pedido1.adicionar(new Combo("Combo Burger + Fritas", 45.00, 15));
            controle.registrar(pedido1);

            Pedido pedido2 = new Pedido(bruno, hoje);
            pedido2.adicionar(new PratoEspecial("Risoto de camarão", 58.00, 35, 22.50));
            pedido2.adicionar(new PratoExecutivo("Bife acebolado", 36.00, 25));
            pedido2.adicionar(new Combo("Combo Massa + Suco", 40.00, 20));
            controle.registrar(pedido2);

            Pedido pedido3 = new Pedido(ana, hoje);
            pedido3.adicionar(new PratoEspecial("Filé ao molho de trufas", 70.00, 40, 35.00));
            controle.registrar(pedido3);
        } catch (PrecoInvalidoException e) {
            System.out.println("[ERRO] " + e.getMessage());
        }

        // Erro tratado 1: preço base negativo (exceção própria)
        System.out.println(">>> Tentando cadastrar prato com preço negativo <<<");
        try {
            PratoExecutivo pratoErrado = new PratoExecutivo("Prato com erro", -10.00, 10);
            Pedido pedidoErrado = new Pedido(bruno, hoje);
            pedidoErrado.adicionar(pratoErrado);
            controle.registrar(pedidoErrado);
        } catch (PrecoInvalidoException e) {
            System.out.println("[RECUSADO] " + e.getMessage());
        }

        // Erro tratado 2: pedido sem cliente
        System.out.println(">>> Tentando criar pedido sem cliente <<<");
        try {
            new Pedido(null, hoje);
        } catch (IllegalArgumentException e) {
            System.out.println("[RECUSADO] " + e.getMessage());
        }
        System.out.println("O programa continua rodando.\n");

        // Fechamento do dia: hoje vai para o console
        GeradorDeRelatorio gerador = new GeradorDeRelatorio(new FormatadorTexto(), new DestinoConsole());
        gerador.gerarFechamento(controle);

        // Amanhã o dono quer em arquivo: muda só a montagem, o gerador não é editado
        GeradorDeRelatorio geradorArquivo = new GeradorDeRelatorio(
            new FormatadorTexto(), new DestinoArquivo("fechamento-do-dia.txt"));
        geradorArquivo.gerarFechamento(controle);
    }
}
