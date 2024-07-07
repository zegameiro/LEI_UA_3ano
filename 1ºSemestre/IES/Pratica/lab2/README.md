# Lab 2 Notas

## Exercício 2.1 - Programação do estilo server-side com servlets

### Java Servlet ou Jakarta
Tecnologia usada para o design e desenvolvimento de páginas web usando a linguagem de programação Java. Utiliza a arquitetura client-server e do lado do servidor encontra se o **Servlet**.

O servlet é uma classe do Java que corre num servidor e lida com os pedidos que os clientes fazem, este deve de ser corrido com um container.


### Servlet Containers e Docker Containers

- Servlet Containers fornecem um tempo para a execução de código Java relacionado à web do lado do servidor (sem relação com virtualização).

- Docker Containers são usados para fazer deploy de virtualizações para qualquer tipo de serviço.


## Exercício 2.2 - Programação do estilo server-side e servidores para aplicações

### Apache Tomcat
O Tomcat é um software opensource que implementa o jakarta Servlet, jakarta Server Pages, entre outras.
Este é usado mais para aplicações com uma higher performance, logo é preferível usar este software em vez de embedded servers.

### Docker Compose   
O Compose é uma ferramenta para definir e executar aplicações Docker de vários contentores. Este utiliza um ficheiro ,yaml para configurar os serviços da sua aplicação. Em seguida, com um único comando, cria e inicia todos os serviços a partir do seu ficheiro de configuração.

### Ficheiro docker-compose.yaml
- Como primeiro passo especificamos a versão do docker compose que está a ser utilizada:

```docker
version: '3.8'
```
- Depois definimos os serviços que vão ser utilizado, que neste caso é designado de:
```docker
services:
  tomcat-10-0-11-jdk11:
```

- A linha segguinte especifica a imagem Docker que irá ser utilizada para criar o container:
```docker
image: tomcat:10.0-jdk11
``` 

- De seguida defini-se as portas que irão ser utilizadas para mapear o container e o localhost:
```docker
ports:
    - "8888:8080" # expor a porta do container 8080 para o tomcat e 8888 para o host
    - "5005:5005" # expor a porta 5005 do container para o java debbugging e a mesma também para o host
```

- É especificado também para executar um script em bash quando o container for iniciado que neste caso é o catalinha.sh, que serve para iniciar o Tomcat:
```docker
command: "catalina.sh run"
```

- De seguida encontra-se definido os volumes, estes são caminhos no sistema host que, quando o container for iniciado, irão ser criados dentro do container:
```docker
volumes:
    - "./target:/usr/local/tomcat/webapps"
```

- Por último são definidas algumas variáveis de ambiente que iram ser configuradas dentro do contentor:
```docker
environment:
    JAVA_OPTS: "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"
```

Para correr a aplicação num container usar o comando ```docker compose up``` e para parar a sua execução executar o comando ```docker compose down```.
</br>

Verificar se algum container está em execução:
```bash
sudo docker ps
sudo docker ps -la
```

Remover uma imagem:
```bash
sudo docker rmi <ID da imagem>
```
## Exercício 2.3 - Introdução ao desenvolvimento web com uma full-featured framework (Spring Boot)

### Spring Boot
O Spring Boot é uma framework de desenvolvimento de aplicações na linguagem de porgramação Java, esta facilita a criação de aplicações web e serviços de forma rápida e simples.
</br>

O Spring Boot simplifica muitas tarefas em comum no desenvolvimento, como por exemplo, a configuração e gestão de dependências, permitindo aos programadores focarem-se na lógica e no funcionamento da aplicação.menta o jakarta Servlet, jakarta Server Pages, entre outras.
<br>

Este é usado mais para aplicações com uma higher performance, logo é preferível usar este software em vez de embedded servers.

### Criar projetos com Spring Boot
Para se criar um projeto com o Spring boot podemos o usar o website [Spring Initializer](https://start.spring.io/).
</br>

O [Spring Initializer](https://start.spring.io/) contém um conjunto de templates com todas as dependências transitivas relevantes para uma funcionalidade em específico e que irão simplificar 

Executar projetos que contém o Spring Boot:
```bash
mvn install -DskipTests && java -jar target\webapp1-0.0.1-SNAPSHOT.jar
# or
./mvnw spring-boot:run
```

### Maven Wraper (mvnw)
O Maven Wrapper permite que um projeto tenha uma versão específica do Maven ou para utilizadores que não queiram instalar o Maven.

Criar um mvnw:
```bash
mvn -N wrapper:wrapper
# Ou se quisermos especificar a versão do Maven a utilizar
mvn -N wrapper:wrapper -Dmaven=<MAVEN_VERSION>
```
- A opção -N significa -non-recursive (não recursiva), ou seja o wrapper irá apenas ser aplicado para o projeto principal no diretório atual.

Depois de se executar um destes comandos iram ser gerados os seguintes ficheiros e diretórios:
-  __mvnw__:  é um Unix shell script executável que substitui a necessidade de ter instalado o Maven;
- __mvnw.cmd__: é uma versão em Batch do ficheiro __mvnw__;
- /.mvn: um diretório escondido que a biblioteca Java do Maven Wrappe e os ficheiros com as respetivas propriedades.


## Exercício 2.4 - Wrapping-up e conceitos de integração

Neste exercício é pedido para criar uma API com os seguintes endpoints:

| __Método__ | __Path__ | __Descrição__ |
|  :-------: | :------: | :-------------: | 
| GET | api/quote | Devolve uma quote de um filme aleatório, ou seja, o filme não é especificado |
| GET | api/shows | Listar todos os filmes disponíveis, para o qual uma quote existe, os filmes devem de ter um identificador (id) |
| GET | api/quotes?show=<show_id> | Retorna as quotes existentes do filme especificado |






