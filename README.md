# trabalho-solid SaborExpress

Trabalho desenvolvido para a disciplina de Programação Orientada a Objetos: SaborExpress, o delivery completo.

## Como executar

```bash
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out app.Principal
```

## Pacotes

| Pacote | Conteúdo |
|---|---|
| `app` | `Principal` (demonstração) |
| `modelo` | `Cliente` |
| `cardapio` | `Prato` (abstrata), `PratoExecutivo`, `Combo`, `PratoEspecial` |
| `pedido` | `Pedido`, `ControlePedidos` |
| `relatorio` | `GeradorDeRelatorio`, `FormatadorTexto`, `DestinoConsole`, `DestinoArquivo` |
| `contrato` | `Precificavel`, `Preparavel`, `FormatadorRelatorio`, `DestinoRelatorio` |
| `excecao` | `PrecoInvalidoException` |

## Onde está cada princípio SOLID

**S - Responsabilidade Única**
Cada classe tem um único motivo para mudar: `Cliente` guarda dados, `Pedido` junta pratos de um cliente, `ControlePedidos` controla os pedidos do dia (faturamento e mais caro), `FormatadorTexto` só monta o texto, `DestinoConsole`/`DestinoArquivo` só enviam e `GeradorDeRelatorio` só coordena o fechamento.

**O - Aberto/Fechado**
`Prato` é abstrata e cada tipo implementa `calcularPreco()`. `Pedido` e `ControlePedidos` percorrem a lista pela referência da mãe, sem `if` perguntando o tipo. Um novo destino de relatório também entra só criando classe.

**L - Substituição de Liskov**
O contrato de `Prato` promete: `calcularPreco()` nunca lança exceção e nunca devolve valor negativo. A validação de preço fica no construtor, então qualquer filha substitui a mãe dentro do `Pedido` sem surpresas.

**I - Segregação de Interfaces**
Contratos magros: `Precificavel` (só preço) e `Preparavel` (só tempo de preparo). `Prato` assina os dois; `Pedido` assina só `Precificavel`, porque não tem tempo de preparo próprio. O relatório também foi dividido em `FormatadorRelatorio` e `DestinoRelatorio`, em vez de uma interface única.

**D - Inversão de Dependência**
`GeradorDeRelatorio` depende das interfaces `FormatadorRelatorio` e `DestinoRelatorio`, recebidas pelo construtor. Trocar console por arquivo (ou outro meio no futuro) muda só a montagem na `Principal`; o gerador não é editado.

## Regras de negócio

- Preço base negativo: `PrecoInvalidoException` (exceção própria, checked), tratada com `try/catch` sem derrubar o programa.
- Pedido sem cliente: o construtor de `Pedido` recusa com `IllegalArgumentException`.

## Dica de teste: e o Prato Diet?

Nenhum arquivo existente precisa ser editado na lógica. Basta criar `cardapio/PratoDiet.java` estendendo `Prato`. A `Principal` só muda se quisermos usar o novo prato na demonstração.
