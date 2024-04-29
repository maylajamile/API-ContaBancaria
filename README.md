# Conta Bancária

Esse projeto, é responsável por criar uma API de Conta Bancária com as seguintes opereções:

- void deposita(float valor) - adiciona o valor ao saldo
- void retirada(float valor) - reduz o saldo, subtraindo o valor do saldo

## Endpoints

<table>
<caption><strong>Urls da aplicação:</strong></caption>
  <thead>
    <tr>
      <th>Método</th>
      <th>Endpoint</th>
      <th>Descrição</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>GET</td>
      <td>http://localhost:8080/contas</td>
      <td>Listar contas</td>
    </tr>
    <tr>
      <td>GET</td>
      <td>http://localhost:8080/contas/versionadas</td>
      <td>Listar contas versionadas</td>
    </tr>
    <tr>
      <td>PUT</td>
      <td>http://localhost:8080/contas/depositar/${ID}?valor=${valor}</td>
      <td>Depositar valor em conta por id</td>
    </tr>
    <tr>
      <td>PUT</td>
      <td>http://localhost:8080/contas/versionadas/depositar/${ID}?valor=${valor}</td>
      <td>Depositar valor em conta versionada por id</td>
    </tr>
    <tr>
      <td>PUT</td>
      <td>http://localhost:8080/contas/retirar/${ID}?valor=${valor}</td>
      <td>Retirar valor em conta por id</td>
    </tr>
    <tr>
      <td>PUT</td>
      <td>http://localhost:8080/contas/versionadas/retirar/${ID}?valor=${valor}</td>
      <td>Retirar valor em conta versionada por id</td>
    </tr>
  </tbody>
</table>


## Instruções

Nesse projeto, há 2 entidades: <strong>ContaBancaria</strong> e <strong>ContaBancariaVersionada</strong>, a última possui o campo ultimaAlteracao com uma data versionada.

Para executar os testes de carga, foi utilizado o <strong>Apache JMeter</strong>, você pode baixar essa ferramenta por meio da url:

- https://jmeter.apache.org/download_jmeter.cgi

No diretório desse projeto, você encontra o arquivo <em>"Testes,jmx"</em>, você pode abri-lo no JMeter para executar os testes.

Ao executar os testes, você pode checar os resultados das requisições e suas mensagens por meio das árvores de resultados.

Para alterar o caminho da url para testar a entidade com versionamento, você deve seguir o seguinte caminho e alterar o valor da variável caminho para <em>"/contas/versionadas"</em>:

<img src="https://github.com/maylajamile/github-images/blob/041414446d872b86a4a95f58696d7142d7b2046f/image.png" alt="Imagem Plano de Testes JMeter">

## Ferramentas

- JDK 17
- Spring Framework
- Banco de dados H2
- Eclipse IDE
- Maven
- Apache JMeter







