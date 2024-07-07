# Lab 1 - Notes

## **Maven**
O Apache Maven é uma ferramenta de gestão de projetos de software. Baseado no conceito de um modelo de objeto de projeto (POM), o Maven pode gerir a construção, relatórios e documentação de um projeto a partir de uma peça central de informação.

### **Maven Archetype**
Maven archetype é uma abstração de um projeto que pode ser instanciado para um projeto Maven personalizado, ou seja, é um template para um projeto do qual outros são criados.

### Commands
**Criar um novo projeto Maven**
```bash
mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```
**GroupId** and **DartifactId** can and should be changed to specify each project

</br>

**Compile and run the project**
```bash
mvn package # get dependencies, compiles the project and creates the jar
mvn exec:java -Dexec.mainClass="weather.WeatherStarter" # adapt to match the package structure and class name
```

## Git vs. Github

O Git é um sistema de controlo de versões distribuído e de código aberto, este foi criado com o objetivo de lidar com desde projetos de baix importância até projetos de alto nível com rapidez e eficiência.

Já o GitHub é uma plataforma de alojamento de código-fonte que permite que os programadores colaborem, controlem versões e partilhem projetos de software de forma eficaz.













