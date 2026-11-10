package relatorio;

import contrato.DestinoRelatorio;

import java.io.FileWriter;
import java.io.IOException;

public class DestinoArquivo implements DestinoRelatorio {
    private final String nomeArquivo;

    public DestinoArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    @Override
    public void enviar(String conteudo) {
        try (FileWriter fw = new FileWriter(nomeArquivo)) {
            fw.write(conteudo);
            System.out.println("[ARQUIVO] " + nomeArquivo + " salvo com sucesso.");
        } catch (IOException e) {
            System.out.println("[ERRO] Falha ao salvar: " + e.getMessage());
        }
    }
}
