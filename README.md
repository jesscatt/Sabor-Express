# SaborExpress

trabalho de SOLID de POO (prof. Augusto)

basicamente é um sistema de delivery pro restaurante: cadastra cliente, faz os pedidos do dia e no final gera o fechamento com o faturamento e o pedido mais caro.

## pra rodar

```bash
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out app.Principal
```

(sem o encoding os acentos saem tudo zoado)

## pastas

dividi em app, modelo, cardapio, pedido, relatorio, contrato e excecao. as interfaces ficaram todas no contrato, igual fiz nos exercícios do FrotaLog e da PataViva.

## o SOLID

o S tá basicamente em tudo. tentei não deixar nenhuma classe fazendo duas coisas, o ControlePedidos só cuida dos pedidos do dia e o relatório ficou dividido em 3 (um gera, um formata, um envia).

o O é o Prato. ele é abstrato e cada tipo calcula seu preço (executivo é o preço normal, combo tem 15% de desconto e o especial soma o ingrediente premium). o pedido só faz um for somando, não tem if perguntando qual tipo é.

Liskov: o calcularPreco nunca dá exceção, a validação do preço fica no construtor. ou seja, se o prato existe, o preço dele é válido. fiz assim por causa daquele exemplo da bicicleta, que quebrava o despacho no meio da soma.

o I foi o que eu mais fiquei em dúvida. acabei fazendo duas interfaces pequenas, Precificavel e Preparavel. o Prato tem preço e tempo de preparo, então assina as duas. o Pedido só tem preço, então assina só a Precificavel. se fosse uma interface só, o pedido ia ter que implementar tempo de preparo sem precisar.

o D tá no relatório. o GeradorDeRelatorio recebe o destino pelo construtor e não sabe se é console ou arquivo. na Principal eu mando primeiro pro console e depois pra arquivo, trocando só uma linha. peguei a ideia do exemplo do DipComparado.

## erros

preço negativo cai na PrecoInvalidoException (que eu criei), e o try/catch segura sem parar o programa. pedido sem cliente dá IllegalArgumentException no construtor, então nem chega a criar.

## prato diet

a pergunta era quantos arquivos teria que editar. nenhum! é só criar o PratoDiet extends Prato e pronto
