package relatorio;

import contrato.DestinoRelatorio;

public class DestinoConsole implements DestinoRelatorio {

    @Override
    public void enviar(String conteudo) {
        System.out.println(conteudo);
    }
}
