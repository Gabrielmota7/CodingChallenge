# CodingChallenge

## Descrição do projeto
Este documento visa contemplar detalhes sobre a Api desenvolvida para detalhar e explicar o funcionamento da Api.

### Features

- É necessário ter o Java 15 instalado para rodar a aplicação.

- Não foi implementada uma api externa para segurança, porém através do spring security foi implementado a utilização de token que dever é gerada após o logar que deve ser passada nos parâmetros para poder acessar a todas as outras rotas da aplicação.

- Dentro de ApplicationProperties é necessário colocar os dados do banco de dados e também está a informação sobre a APIKEY para fazer consultas da api externa, podendo também utilizar uma própria 

- Para rodar o projeto devemos inciar a MovieApiApplication dentro de src/java/coding.challenge.Api

- [x] Cadastro de usuário
- [x] Consumo API OMDb
- [ ] Api de segurança

## Detalhamento 
Ao rodar a Api na porta 8080 só temos acesso a duas rotas "/cadastrar" e "/logar", para cadastrar usuários é necessário passar campos obrigatórios como nome, usuário e senha utilizando o método post com a rota "/cadastrar" após efetuar o cadastro vá para rota de login faça o login e sera gerado uma BasicAuth com o token para ter acesso as outras rotas.

Os campos mínimos para efeturar cadastro do usuario estão como demostrado abaixo:

{
    "nome": "Gabriel",
    "usuario": "Bielzinho",
    "senha": "123456789"
}

- Podemos passar outros campos para já testar o esquema de Roles e pontos adicionando na hora de cadastro de usúario
