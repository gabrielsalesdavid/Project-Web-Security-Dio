# 📚 Project Web Security DIO - Spring Boot Security

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.6-brightgreen?style=flat-square)
![Maven](https://img.shields.io/badge/Maven-3.8.1-blue?style=flat-square)
![License](https://img.shields.io/badge/License-MIT-yellow?style=flat-square)

Um projeto educacional do BootCamp Bradesco na plataforma DIO focado em implementação de segurança web com Spring Boot. Este repositório contém exemplos práticos, documentações completas sobre fundamentos e conceitos avançados.

---

## 📋 Índice

- [Visão Geral](#visão-geral)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Documentações](#documentações)
- [Requisitos](#requisitos)
- [Instalação](#instalação)
- [Como Executar](#como-executar)
- [Arquitetura](#arquitetura)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Contribuindo](#contribuindo)
- [Autor](#autor)
- [Licença](#licença)

---

## 🎯 Visão Geral

Este projeto implementa funcionalidades de segurança web utilizando **Spring Security** com foco em:

- ✅ Autenticação e Autorização
- ✅ Proteção de Recursos
- ✅ Gerenciamento de Usuários
- ✅ Integração com Banco de Dados
- ✅ Configuração de Segurança Web

### Objetivo Principal
Aprender e aplicar boas práticas de segurança em aplicações web desenvolvidas com Spring Boot.

---

## 📁 Estrutura do Projeto

```
Project-Web-Security-Dio/
├── 📄 README.md                                    # Este arquivo
├── 📄 pom.xml                                      # Configuração Maven
├── 📄 mvnw / mvnw.cmd                             # Maven Wrapper
│
├── 📂 src/
│   ├── main/
│   │   ├── java/dio/spring/web/security/
│   │   │   ├── Application.java                   # Classe principal (entry point)
│   │   │   ├── WebSecurityConfig.java            # Configuração de segurança
│   │   │   │
│   │   │   ├── controller/
│   │   │   │   └── WelcomeController.java        # Controller principal
│   │   │   │
│   │   │   ├── init/
│   │   │   │   └── StartApplication.java         # Inicialização da aplicação
│   │   │   │
│   │   │   ├── model/
│   │   │   │   └── User.java                     # Modelo de usuário
│   │   │   │
│   │   │   ├── repository/
│   │   │   │   └── IUserRepository.java          # Interface de repositório
│   │   │   │
│   │   │   └── service/
│   │   │       └── SecurityDatabaseService.java  # Serviço de segurança
│   │   │
│   │   └── resources/
│   │       ├── application.properties             # Configurações da aplicação
│   │       ├── static/                           # Arquivos estáticos (CSS, JS)
│   │       └── templates/                        # Templates HTML (Thymeleaf)
│   │
│   └── test/
│       └── java/dio/spring/web/security/
│           └── ApplicationTests.java              # Testes unitários
│
├── 📂 Docs/
│   │
│   ├── 📂 Fundamentos/
│   │   ├── Java.md                               # Fundamentos de Java
│   │   └── XML_Maven.md                          # Fundamentos de XML e Maven
│   │
│   └── 📂 Conceitos/
│       ├── Java.md                               # Conceitos avançados de Java
│       └── XML_Maven.md                          # Conceitos avançados de XML e Maven
│
├── 📂 .mvn/
│   └── wrapper/                                  # Maven Wrapper files
│
└── 📂 target/                                    # Build output (ignorado)
```

---

## 📚 Documentações

Este projeto inclui documentações completas sobre as linguagens e tecnologias utilizadas:

### 📖 Java

#### Fundamentos
**Arquivo**: `Docs/Fundamentos/Java.md`

Cobre os conceitos básicos e essenciais de Java:
- Configuração do ambiente
- Tipos de dados primitivos
- Operadores e expressões
- Estruturas de controle (if, switch, loops)
- Arrays e coleções
- Métodos e funções
- Programação orientada a objetos (POO)
- Herança, polimorfismo e encapsulamento
- Tratamento de exceções
- Boas práticas

**Estimado**: ~3-4 horas de leitura + prática

#### Conceitos Avançados
**Arquivo**: `Docs/Conceitos/Java.md`

Aprofunda-se em tópicos avançados:
- Programação funcional
- Streams API
- Expressões Lambda
- Generics
- Annotations
- Reflexão (Reflection)
- Concorrência e Threads
- Padrões de design (Singleton, Builder, Factory, Observer)
- SOLID Principles
- Java moderno (Java 8+, Records, Sealed Classes, Pattern Matching)

**Estimado**: ~5-6 horas de leitura + prática

### 📋 XML e Maven

#### Fundamentos
**Arquivo**: `Docs/Fundamentos/XML_Maven.md`

Introdução a XML e Maven:
- Sintaxe XML
- Declarações e elementos
- Caracteres especiais e CDATA
- O que é Maven
- Ciclo de vida do Maven
- Estrutura do pom.xml
- Dependências e escopos
- Plugins
- Perfis (Profiles)
- Comandos Maven úteis
- Repositórios

**Estimado**: ~2-3 horas de leitura + prática

#### Conceitos Avançados
**Arquivo**: `Docs/Conceitos/XML_Maven.md`

Tópicos avançados de XML e Maven:
- XML avançado (Namespaces, DTD, XSD)
- XSLT (transformações XML)
- Validação XML
- XPath e XQuery
- Maven multi-módulo
- Maven Archetype
- Troubleshooting Maven
- Integração contínua (GitHub Actions, Jenkins)
- Performance
- Segurança

**Estimado**: ~3-4 horas de leitura + prática

---

## 📦 Requisitos

- **Java**: JDK 17 ou superior
- **Maven**: 3.8.1 ou superior (incluído via Maven Wrapper)
- **Git**: Para clonar o repositório
- **IDE**: IntelliJ IDEA, VS Code ou Eclipse (recomendado)

### Verificar Instalação

```bash
# Verificar Java
java -version

# Verificar Maven
mvn -version

# Verificar Git
git --version
```

---

## ⚙️ Instalação

### 1. Clonar o Repositório

```bash
git clone https://github.com/gabrielsalesdavid/Project-Web-Security-Dio.git
cd Project-Web-Security-Dio
```

### 2. Instalar Dependências

```bash
# Usando Maven Wrapper (recomendado)
./mvnw clean install

# Ou usando Maven instalado globalmente
mvn clean install
```

### 3. Verificar Estrutura

```bash
# Listar arquivos
ls -la

# Verificar compilação
./mvnw compile
```

---

## 🚀 Como Executar

### Via IDE (IntelliJ IDEA)

1. Abrir o projeto
2. Clicar com botão direito em `Application.java`
3. Selecionar "Run Application.main()"
4. Acessar `http://localhost:8080`

### Via Terminal

```bash
# Opção 1: Maven Wrapper
./mvnw spring-boot:run

# Opção 2: Maven
mvn spring-boot:run

# Opção 3: Executar JAR (após compilação)
./mvnw clean package
java -jar target/spring.web.security-0.0.1-SNAPSHOT.jar
```

### Acessar a Aplicação

- **URL Principal**: http://localhost:8080
- **Porta Padrão**: 8080

---

## 🏗️ Arquitetura

### Padrão MVC com Spring Boot

```
Request HTTP
    ↓
[Controller] ← Recebe requisições
    ↓
[Service] ← Lógica de negócio
    ↓
[Repository] ← Acesso a dados
    ↓
[Database] ← Persistência
```

### Componentes Principais

#### 1. **Application.java**
Classe principal que inicia a aplicação Spring Boot.

```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

#### 2. **WebSecurityConfig.java**
Configuração centralizada de segurança usando Spring Security.

```java
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    // Configurações de segurança
}
```

#### 3. **User.java**
Modelo de dados para representação de usuários.

```java
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    // Getters e Setters
}
```

#### 4. **IUserRepository.java**
Interface para acesso a dados de usuários (JPA).

```java
public interface IUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
```

#### 5. **SecurityDatabaseService.java**
Serviço que implementa `UserDetailsService` para autenticação.

```java
@Service
public class SecurityDatabaseService implements UserDetailsService {
    // Implementação de UserDetails
}
```

#### 6. **WelcomeController.java**
Controller que mapeia rotas HTTP.

```java
@Controller
public class WelcomeController {
    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }
}
```

#### 7. **StartApplication.java**
Classe que inicializa dados (CommandLineRunner).

```java
@Component
public class StartApplication implements CommandLineRunner {
    // Inicialização de dados
}
```

---

## 🔧 Tecnologias Utilizadas

| Tecnologia | Versão | Descrição |
|-----------|--------|-----------|
| **Java** | 17 | Linguagem de programação principal |
| **Spring Boot** | 4.0.6 | Framework web |
| **Spring Security** | 6.0.x | Segurança web |
| **Spring Data JPA** | 3.1.x | Persistência de dados |
| **Maven** | 3.8.1+ | Gerenciador de build e dependências |
| **JUnit 5** | 5.9.x | Framework de testes |
| **Tomcat** | 10.1.x | Servidor web embarcado |

### Dependências Principais

```xml
<!-- Spring Boot Starters -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webmvc</artifactId>
</dependency>

<!-- Testes -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

---

## 📖 Guia de Estudos

### Ordem Recomendada

1. **Fundamentos de Java** (`Docs/Fundamentos/Java.md`)
   - Aprenda sintaxe básica
   - Entenda OOP
   - Pratique com exemplos

2. **Fundamentos de XML e Maven** (`Docs/Fundamentos/XML_Maven.md`)
   - Entenda o pom.xml
   - Aprenda Maven
   - Configure projeto

3. **Explore o Código Fonte**
   - Leia as classes do projeto
   - Entenda a arquitetura
   - Execute exemplos

4. **Conceitos Avançados de Java** (`Docs/Conceitos/Java.md`)
   - Programação funcional
   - Streams API
   - Padrões de design

5. **Conceitos Avançados XML/Maven** (`Docs/Conceitos/XML_Maven.md`)
   - Projetos multi-módulo
   - Integração contínua
   - Performance

6. **Projetos Práticos**
   - Modifique o código
   - Adicione novos recursos
   - Teste suas implementações

---

## 🔐 Segurança

Este projeto implementa as seguintes práticas de segurança:

- ✅ Autenticação baseada em banco de dados
- ✅ Proteção contra CSRF
- ✅ HTTPS ready
- ✅ Validação de entrada
- ✅ Senhas criptografadas (BCrypt)
- ✅ Autorização baseada em roles

### Configuração de Segurança

Veja `WebSecurityConfig.java` para detalhes da configuração.

---

## 📝 Configuração da Aplicação

### `application.properties`

```properties
# Servidor
server.port=8080

# Banco de Dados (padrão: H2 em memória)
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver

# JPA/Hibernate
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop

# Logging
logging.level.root=INFO
logging.level.dio.spring.web.security=DEBUG
```

---

## ✅ Testes

### Executar Testes

```bash
# Todos os testes
./mvnw test

# Teste específico
./mvnw test -Dtest=ApplicationTests

# Com cobertura
./mvnw clean test jacoco:report
```

### Cobertura de Testes

```bash
# Gerar relatório de cobertura
./mvnw clean test jacoco:report

# Visualizar em target/site/jacoco/index.html
```

---

## 🚨 Troubleshooting

### Problema: Maven não encontrado

```bash
# Usar Maven Wrapper
./mvnw clean install
```

### Problema: Porta 8080 em uso

```bash
# Usar outra porta
./mvnw spring-boot:run -Dspring-boot.run.arguments="--server.port=8081"
```

### Problema: Compilação falha

```bash
# Limpar e reconstruir
./mvnw clean install -U
```

### Problema: Dependências corrompidas

```bash
# Remover repositório local e baixar novamente
rm -rf ~/.m2/repository
./mvnw clean install
```

---

## 🤝 Contribuindo

### Como Contribuir

1. **Fork** o repositório
2. Crie uma branch (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

### Padrões de Código

- Siga as convenções Java
- Use nomes descritivos
- Adicione javadoc para métodos públicos
- Escreva testes para novas funcionalidades
- Mantenha cobertura de testes acima de 80%

---

## 📚 Recursos Adicionais

### Documentação Oficial

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Maven Documentation](https://maven.apache.org/)
- [Java Documentation](https://docs.oracle.com/en/java/)

### Tutoriais Recomendados

- [Spring Boot Guides](https://spring.io/guides)
- [Baeldung - Spring Security](https://www.baeldung.com/spring-security)
- [DIO - Plataforma de Aprendizado](https://www.dio.me)

### Livros Recomendados

- "Spring in Action" - Craig Walls
- "Effective Java" - Joshua Bloch
- "Clean Code" - Robert C. Martin

---

## 📊 Status do Projeto

- ✅ Estrutura base implementada
- ✅ Documentações criadas
- ✅ Exemplos de segurança
- 🔄 Em desenvolvimento
- ⏳ Futuras melhorias:
  - Autenticação OAuth2
  - API REST com JWT
  - Frontend com React
  - Docker support
  - CI/CD pipeline

---

## 👤 Autor

**Gabriel Sales David**

- GitHub: [@gabrielsalesdavid](https://github.com/gabrielsalesdavid)
- Email: gabriel.sales.david@example.com
- LinkedIn: [gabriel-sales-david](https://linkedin.com/in/gabriel-sales-david)

---

## 📄 Licença

Este projeto está licenciado sob a **MIT License** - veja o arquivo [LICENSE](LICENSE) para detalhes.

```
MIT License

Copyright (c) 2026 Gabriel Sales David

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
```

---

## 🙏 Agradecimentos

- **DIO (Digital Innovation One)** - Plataforma de aprendizado
- **Bradesco** - Patrocinador do BootCamp
- **Spring Team** - Framework excelente
- **Comunidade Java** - Suporte e inspiração

---

## 📞 Suporte

Encontrou um problema? Tenha uma pergunta?

- 📫 Abra uma [Issue](https://github.com/gabrielsalesdavid/Project-Web-Security-Dio/issues)
- 💬 Inicie uma [Discussion](https://github.com/gabrielsalesdavid/Project-Web-Security-Dio/discussions)
- 📧 Entre em contato via email

---

## 📈 Roadmap

- [ ] Autenticação OAuth2/OIDC
- [ ] API REST com Spring WebFlux
- [ ] Microserviços com Spring Cloud
- [ ] Frontend React/Vue
- [ ] Containerização com Docker
- [ ] Orquestração com Kubernetes
- [ ] Testes de integração com Testcontainers
- [ ] API Documentation com Swagger/OpenAPI
- [ ] Performance monitoring com Spring Boot Actuator
- [ ] Caching com Redis

---

**Desenvolvido com ❤️ durante o BootCamp Bradesco DIO**

**Última atualização**: 7 de maio de 2026

---

## Quick Start

```bash
# 1. Clonar repositório
git clone https://github.com/gabrielsalesdavid/Project-Web-Security-Dio.git
cd Project-Web-Security-Dio

# 2. Instalar dependências
./mvnw clean install

# 3. Executar aplicação
./mvnw spring-boot:run

# 4. Acessar
open http://localhost:8080
```

**Aproveite seus estudos! 🚀**
