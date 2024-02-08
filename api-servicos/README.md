# api-servicos

> [!IMPORTANT]
> Este projeto foi desenvolvido com o intuito de ser um exemplo de aplicação fullstack, utilizando Angular 9 e Spring Boot, com deploy no Heroku.



### Dicas de como foi feito o deploy no HEROKU, tanto do FRONT quanto do BACK

> JAVA
> Versoes java heroku e upgrade de stack:   

  - https://devcenter.heroku.com/articles/java-support    

  - https://devcenter.heroku.com/articles/upgrading-to-the-latest-stack   
 
  - Caso receba erro de pom.xml não encontrado, ou incompatível, esse tutorial ajuda: (Deploy de subdiretorios)   

  - https://medium.com/@timanovsky/heroku-buildpack-to-support-deployment-from-subdirectory-e743c2c838dd

  - Banco de dados utilizado é o H2Database, executando em memória, não persistindo nada no FS(FileSystem).

  - Link de acesso ao Swagger da API, pode ser consumida via POSTMAN https://clientes-api-sfidencio.herokuapp.com/swagger-ui.html


### Realizando deploy da aplicação no Heroku
  - Para realizar deploy de sub-folders de um repositório GIT, deve seguir as dicas abaixo

1-Considere que a pasta .git esta na raiz do grupo de projetos, no caso ai temos o projeto clientes-api(Java/SpringBoot), clientes-app(Angular).

2-Assim, com CLI do heroku instalado e devidamente logado, e com o projeto de deploy já criado no dashboard do heroku, façamos os seguinte, estando no root_folder do projeto.

  &emsp;2.1-heroku git:remote -a clientes-api-sfidencio  (Observe que aqui apontamos pra qual repositório criamos no dashboard do heroku)

  &emsp;2.1.1-git add . #adicionamos no stage
    
  &emsp;2.1.2-git commit -m "msg de commit" # msg de commit

  &emsp;2.2-git subtree push --prefix clientes-api/ heroku master # normal push

  &emsp;2.3-Se ocorreu algum erro ou rejeição, podemos forçar o push, caso necessário:

  &emsp;2.4-git push heroku 'git subtree split --prefix clientes-api/ branch':master --force # force push

3-Depois, faça o push normal para o repositório do github, pois o push do passo 4 e 5, envia para o repos da aplicação no heroku.

4-#git push origin [master ou main]

5-Visualizar logs no CLI do heroku:

  &emsp;5.1-#heroku logs -t --app clientes-api-sfidencio

  &emsp;5.2-Basta efetuar requisição REST via postman ou pela aplicação angular, que irá aparecer no CLI do heroku.


# Angular

1-Para fazer deploy da aplicação angular, segui esse tutorial:

https://medium.com/geekculture/how-to-easily-deploy-your-first-angular-app-on-heroku-65dd546c8181

2-Porém existe um pequeno detalhe, na hora de subir os fontes para o heroku, deve apontar para o repositório da seguinte forma:

   &emsp;2.1-heroku git:remote -a clientes-app-sfidencio  (Observe que aqui apontamos pra qual repositório criamos no dashboard do heroku)
  
   &emsp;2.2-git commit -m "Fazer Deploy"
  
   &emsp;2.3-git subtree push --prefix clientes-app/ heroku master

2-Não esquecer de mudar o arquivo "enviroment.prod.ts", colocar a url_base da API hospedada no heroku.

3-Visualizar logs no CLI do heroku:

  &emsp;3.1-#heroku logs -t --app clientes-app-sfidencio
 
 
4-Finalmente a URL de acesso da aplicação clientes-app-sfidencio, desenvolvida utilizando Angular:

   &emsp;4.1-https://clientes-app-sfidencio.herokuapp.com/



# Referências:

https://www.udemy.com/course/full-stack-angular9-spring-boot

https://gist.github.com/dariye/1cdc25e7a168527f5d035c47f4f9aed3

https://www.treinaweb.com.br/blog/deploy-de-uma-aplicacao-spring-boot-no-heroku

https://devcenter.heroku.com/articles/heroku-cli

https://devcenter.heroku.com/articles/deploying-spring-boot-apps-to-heroku#creating-a-spring-boot-app

https://www.treinaweb.com.br/blog/documentando-uma-api-spring-boot-com-o-swagger

https://programmingtechie.com/2020/10/10/deploy-spring-boot-and-angular-application-to-heroku/#Deploying_Spring_Boot_App_to_Heroku
