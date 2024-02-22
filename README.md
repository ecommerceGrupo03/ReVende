# Projeto ReVende

<p align="center">
  <img src="./reVende/src/assets/logoRevende.svg" width="50%" />
</p>

## Visão Geral
Este projeto é um E-commerce de produtos recicláveis, baseado no Objetivo de Desenvolvimento Sustentável (ODS) da ONU número 12 - Consumo e Produção Sustentáveis. <br></br>
Nosso objetivo é criar uma plataforma que facilite a conexão entre pessoas que desejam descartar ou vender materiais recicláveis e pessoas interessadas em comprá-los.
<p>
  <img src="https://brasil.un.org/profiles/undg_country/themes/custom/undg/images/SDGs/pt-br/SDG-12.svg" width="200" />
</p>

## Funcionalidades

- Cadastro e login de usuários, com autenticação.
- Possibilidade de ser vendedor e consumidor.
- Listagem de produtos e categorias.
- Compra e venda de produtos recicláveis.
- Busca por produtos e categorias.
- Sistema de avaliação de produtos e vendedores.

## Tecnologias Utilizadas

- HTML
- CSS
- JavaScript
- React
- Tailwind CSS
- Java
- Spring Boot
- MySQL

## Desenvolvedores
- Eloisa Marin: [LinkedIn](https://www.linkedin.com/in/eloisamarin/)
- Fernando Lopes: [LinkedIn](https://www.linkedin.com/in/fernando-barbosa-ferreira-lopes/)
- Jaqueline Paula: [LinkedIn](https://www.linkedin.com/in/jaquelinepaula/)
- Leonardo Duarte: [LinkedIn](https://www.linkedin.com/in/leonardo-rodrigues-de-sousa-duarte-714bba260/)
- Marina Pereira: [LinkedIn](https://www.linkedin.com/in/marina-alexandre-pereira/)
- Marília Tostes: [LinkedIn](https://www.linkedin.com/in/marilia-ribeiro-tostes/)
- Rafael Carmo: [LinkedIn](https://www.linkedin.com/)

<br></br>
***

# Sistema de Controle de Acesso e Segurança

Este é um projeto de sistema de controle de acesso e segurança desenvolvido com Spring Security e autenticação JWT. O sistema oferece funcionalidades para autenticação de usuários, gerenciamento de categorias e produtos.

## Configuração e Instalação

Para configurar e executar o projeto localmente, siga estas etapas:

1. Clone o repositório para o seu ambiente de desenvolvimento:

```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
```
1. Certifique-se de ter o JDK (Java Development Kit) e o Apache Maven instalados na sua máquina.
2. Importe o projeto na sua IDE favorita como um projeto Maven existente.
3. Certifique-se de configurar as dependências do projeto, como o Spring Boot e outras bibliotecas mencionadas no arquivo pom.xml.
4. Configure as informações do banco de dados no arquivo application.properties ou application.yml.
5. Compile e execute o projeto a partir da sua IDE ou utilizando o Maven:

```bash
mvn spring-boot:run
```
## Uso
Após a execução do projeto, você pode acessar os endpoints HTTP para interagir com as funcionalidades oferecidas pelo sistema.

## Autenticação de Usuários
Para autenticar um usuário e receber um token JWT válido, envie uma solicitação POST para o endpoint /login com as credenciais de usuário. O token JWT será retornado como resposta e pode ser usado para autorização em outros endpoints.

Exemplo de solicitação:
```http
POST /login
Content-Type: application/json

{
  "email": "usuario@example.com",
  "senha": "senha123"
}
```
## Gerenciamento de Categorias
O sistema oferece endpoints para criar, listar, atualizar e excluir categorias de produtos. As solicitações devem incluir um token JWT válido no cabeçalho Authorization para autorização.

Exemplo de criação de categoria:

```http
POST /categorias
Authorization: Bearer {seu-token-jwt}
Content-Type: application/json

{
  "nome": "Categoria de Exemplo",
  "descricao": "Esta é uma categoria de exemplo"
}
```
## Gerenciamento de Produtos
Da mesma forma que as categorias, o sistema oferece endpoints para criar, listar, atualizar e excluir produtos. As solicitações também devem incluir um token JWT válido para autorização.

Exemplo de criação de produto:
```http
POST /produtos
Authorization: Bearer {seu-token-jwt}
Content-Type: application/json

{
  "nome": "Produto de Exemplo",
  "descricao": "Este é um produto de exemplo",
  "preco": 19.99,
  "quantidade": 10,
  "categoriaId": 1
}
```
## Testes
O projeto inclui testes automatizados para garantir a qualidade e a integridade do código. Você pode executar os testes unitários utilizando o Maven:

```bash
mvn test
```
## Contribuição
Contribuições são bem-vindas! Se você tiver sugestões de melhorias, correções de bugs ou novas funcionalidades, sinta-se à vontade para abrir uma issue ou enviar um pull request.
