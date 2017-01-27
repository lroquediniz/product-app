# Product App

Este projeto provê um serviço de manutenção de cadastros de produtos.

A API é [REST](http://en.wikipedia.org/wiki/Representational_State_Transfer "RESTful") e usa o [Spark](http://sparkjava.com/ "Spark") e [Mongo DB](https://www.mongodb.com/ "Mongo DB") (Embadded) .
Atualmente, o formato de retorno de todos os endpoints é [JSON](http://json.org/ "JSON").

## Tecnologias

- [Angular JS](https://angularjs.org/)
- [Java 8](https://docs.oracle.com/javaee/7/tutorial/)
- [Spark](http://sparkjava.com/)
- [Mongo DB](https://www.mongodb.com/)
- [Docker](https://www.docker.com/)

## FAQ
### Como posso testar esta API?

Use o Docker para baixar a imagem e executar um container para testes.

- Baixe a imagem executando o docker pull:
```shell
docker pull lroquediniz/product-app
```
- Para iniciar um container da imagem recém baixada:
```shell
docker run --rm -p 80:4567 lroquediniz/product-app
```

### Quais formatos de retorno são suportados?
Atualmente os dados são retornados no formato [JSON](http://json.org/ "JSON").

### Qual o tipo de autenticação necessária?
Não requer nenhum tipo de autenticação.

