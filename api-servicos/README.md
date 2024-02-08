# api-servicos

> [!IMPORTANT]
> Este projeto foi desenvolvido com o intuito de ser um exemplo de aplicação fullstack, utilizando Angular 9 e Spring Boot, com deploy no Heroku.



### Dicas de como foi feito o deploy no HEROKU, tanto do FRONT quanto do BACK

> Referências:   

  - https://devcenter.heroku.com/articles/java-support    

  - https://devcenter.heroku.com/articles/upgrading-to-the-latest-stack   
 
  - Caso receba erro de pom.xml não encontrado, ou incompatível, esse tutorial ajuda: (Deploy de subdiretorios)   

  - https://medium.com/@timanovsky/heroku-buildpack-to-support-deployment-from-subdirectory-e743c2c838dd

  - Banco de dados utilizado é o H2Database, executando em memória, não persistindo nada no FS(FileSystem).

  - Link de acesso ao Swagger da API, pode ser consumida via POSTMAN https://clientes-api-sfidencio.herokuapp.com/swagger-ui.html


### Realizando deploy da api no Heroku

> Para realizar deploy de sub-folders de um repositório GIT, deve seguir as dicas abaixo:

> Considere que a pasta .git esta na raiz do grupo de projetos, no caso ai temos o projeto clientes-api(Java/SpringBoot clientes-app(Angular).

### Assim, com CLI do heroku instalado e devidamente logado, e com o projeto de deploy já criado no dashboard do heroku façamos os seguinte, estando no root_folder do projeto:

> JAVA

```bash
heroku git:remote -a clientes-api-sfidencio` # apontar para o repositório criado no dashboard do heroku
```
```bash
git add . #adicionamos no stage
```
```bash
git commit -m "msg de commit" # msg de commit
```
```bash
git subtree push --prefix clientes-api/ heroku master # normal push
```

### Se ocorreu algum erro ou rejeição, podemos forçar o push, caso necessário:

```bash
git push heroku 'git subtree split --prefix clientes-api/ branch':master --force # force push
```

### Depois, faça o push normal para o repositório do github, pois o push do passo 4 e 5, envia para o repos da aplicação no heroku:

```bash 
git push origin [master ou main]
```

### Visualizar logs no CLI do heroku:

```bash
heroku logs -t --app clientes-api-sfidencio
```

### Basta efetuar requisição REST via postman ou pela aplicação angular, que irá aparecer no CLI do heroku.


> Angular

> Referências:
  - Para fazer deploy da aplicação angular, segui esse tutorial:

  - https://medium.com/geekculture/how-to-easily-deploy-your-first-angular-app-on-heroku-65dd546c8181


### Realizando deploy da aplicação angular no Heroku:

> Porém existe um pequeno detalhe, na hora de subir os fontes para o heroku, deve apontar para o repositório da seguinte forma:

```bash
heroku git:remote -a clientes-app-sfidencio  #observe que aqui apontamos pra qual repositório criamos no dashboard do heroku
```
```bash
git commit -m "Fazer Deploy"
```

```bash
git subtree push --prefix clientes-app/ heroku master
```

> [!TIP]
> Não esquecer de mudar o arquivo "enviroment.prod.ts", colocar a url_base da API hospedada no heroku.


### Visualizar logs no CLI do heroku:

```bash
heroku logs -t --app clientes-app-sfidencio
```
 
 
### Finalmente a URL de acesso da aplicação clientes-app-sfidencio, desenvolvida utilizando Angular:

> https://clientes-app-sfidencio.herokuapp.com/



### Referências Gerais:

  - https://www.udemy.com/course/full-stack-angular9-spring-boot

  - https://gist.github.com/dariye/1cdc25e7a168527f5d035c47f4f9aed3

  - https://www.treinaweb.com.br/blog/deploy-de-uma-aplicacao-spring-boot-no-heroku

  - https://devcenter.heroku.com/articles/heroku-cli

  - https://devcenter.heroku.com/articles/deploying-spring-boot-apps-to-heroku#creating-a-spring-boot-app

  - https://www.treinaweb.com.br/blog/documentando-uma-api-spring-boot-com-o-swagger

  - https://programmingtechie.com/2020/10/10/deploy-spring-boot-and-angular-application-to-heroku#Deploying_Spring_Boot_App_to_Heroku
