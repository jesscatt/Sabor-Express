package contrato;

// D: o gerador depende disto, não de console nem de arquivo
public interface DestinoRelatorio {
    void enviar(String conteudo);
}
