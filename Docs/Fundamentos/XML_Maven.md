# Fundamentos de XML e Maven (pom.xml)

## 📚 Índice
1. [O que é XML](#o-que-é-xml)
2. [Sintaxe XML](#sintaxe-xml)
3. [O que é Maven](#o-que-é-maven)
4. [Estrutura do pom.xml](#estrutura-do-pomxml)
5. [Dependências](#dependências)
6. [Plugins](#plugins)
7. [Perfis (Profiles)](#perfis)
8. [Comandos Maven](#comandos-maven)
9. [Repositórios](#repositórios)
10. [Boas Práticas](#boas-práticas)

---

## O que é XML

**XML (eXtensible Markup Language)** é uma linguagem de marcação que define um conjunto de regras para codificar documentos em um formato legível tanto para humanos quanto para máquinas.

### Características Principais
- **Extensível**: Define seus próprios tags
- **Estruturado**: Baseado em hierarquia de elementos
- **Legível**: Fácil de ler e entender
- **Portável**: Funciona em qualquer plataforma
- **Separação**: Separa conteúdo de apresentação

---

## Sintaxe XML

### Declaração XML

```xml
<?xml version="1.0" encoding="UTF-8"?>
```

- `version`: Versão XML (obrigatório, geralmente 1.0)
- `encoding`: Codificação do documento (UTF-8 é padrão)
- `standalone`: Se depende de arquivo DTD (opcional)

### Elementos

```xml
<!-- Elemento simples -->
<nome>João</nome>

<!-- Elemento com conteúdo aninhado -->
<pessoa>
    <nome>João</nome>
    <idade>30</idade>
    <email>joao@example.com</email>
</pessoa>

<!-- Elemento com atributos -->
<pessoa id="1" ativo="true">
    <nome>Maria</nome>
</pessoa>

<!-- Elemento vazio (self-closing) -->
<imagem src="foto.jpg" />
```

### Comentários e CDATA

```xml
<!-- Comentário em XML -->

<!-- CDATA: Conteúdo que não deve ser parseado -->
<script><![CDATA[
    if (a < b && c > d) {
        result = a + b;
    }
]]></script>
```

### Regras XML

1. **Prolog obrigatório** (opcional mas recomendado)
2. **Um elemento raiz** (obrigatório)
3. **Nomes case-sensitive**
4. **Tags devem estar fechadas**
5. **Atributos em aspas**
6. **Caracteres especiais** devem ser escapados

```xml
<!-- Caracteres especiais -->
< &lt;
> &gt;
& &amp;
" &quot;
' &apos;
```

---

## O que é Maven

**Maven** é uma ferramenta de automação de compilação e gerenciamento de dependências para projetos Java. Baseia-se no conceito de **POM (Project Object Model)**.

### Vantagens do Maven
- **Padronização**: Estrutura de projeto consistente
- **Gerenciamento de Dependências**: Baixa automaticamente bibliotecas
- **Testes**: Executa testes automaticamente
- **Build**: Automatiza compilação e empacotamento
- **Documentação**: Gera documentação do projeto

### Ciclo de Vida Maven

1. **validate**: Valida a estrutura do projeto
2. **compile**: Compila o código-fonte
3. **test**: Executa testes
4. **package**: Empacota (JAR, WAR, etc.)
5. **verify**: Verifica integridade
6. **install**: Instala no repositório local
7. **deploy**: Faz upload para repositório remoto

---

## Estrutura do pom.xml

### Estrutura Básica

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                             http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <!-- Identificação do projeto -->
    <groupId>com.example</groupId>
    <artifactId>meu-projeto</artifactId>
    <version>1.0.0</version>
    
    <!-- Informações do projeto -->
    <name>Meu Projeto</name>
    <description>Descrição do projeto</description>
    <url>https://example.com</url>
    
    <!-- Propriedades -->
    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
    
    <!-- Dependências -->
    <dependencies>
        <!-- Aqui vão as dependências -->
    </dependencies>
    
    <!-- Build -->
    <build>
        <!-- Plugins e configurações de build -->
    </build>
</project>
```

### Coordenadas Maven (GAV)

```xml
<!-- groupId: Organização/grupo -->
<groupId>org.springframework.boot</groupId>

<!-- artifactId: Nome do projeto -->
<artifactId>spring-boot-starter-web</artifactId>

<!-- version: Versão do projeto -->
<version>3.1.0</version>
```

---

## Dependências

### Declaração Básica

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <version>3.1.0</version>
    </dependency>
</dependencies>
```

### Escopos (Scopes)

```xml
<!-- compile (padrão): Incluído em tempo de compilação e runtime -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <scope>compile</scope>
</dependency>

<!-- test: Apenas para testes -->
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <scope>test</scope>
</dependency>

<!-- provided: Disponível em tempo de compilação, mas não em runtime -->
<dependency>
    <groupId>org.apache.tomcat</groupId>
    <artifactId>tomcat-api</artifactId>
    <scope>provided</scope>
</dependency>

<!-- runtime: Não necessário em tempo de compilação, apenas em runtime -->
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>

<!-- system: Referencia local no sistema de arquivos -->
<dependency>
    <groupId>com.example</groupId>
    <artifactId>local-lib</artifactId>
    <version>1.0</version>
    <scope>system</scope>
    <systemPath>${basedir}/lib/local-lib.jar</systemPath>
</dependency>
```

### Exclusão de Dependências

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

### Versões

```xml
<!-- Versão específica -->
<version>3.1.0</version>

<!-- Range de versões -->
<version>[3.0.0,)</version>          <!-- >= 3.0.0 -->
<version>[3.0.0,4.0.0)</version>     <!-- >= 3.0.0 e < 4.0.0 -->

<!-- Versão dinâmica -->
<version>3.1-SNAPSHOT</version>      <!-- Snapshot (desenvolvimento) -->
<version>3.1-RELEASE</version>       <!-- Release (produção) -->
```

### BOM (Bill of Materials)

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>3.1.0</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <!-- Versão vem do BOM -->
    </dependency>
</dependencies>
```

---

## Plugins

### Configuração de Plugins

```xml
<build>
    <plugins>
        <!-- Plugin Maven Compiler -->
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.10.1</version>
            <configuration>
                <source>17</source>
                <target>17</target>
            </configuration>
        </plugin>
        
        <!-- Plugin Spring Boot Maven -->
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <version>3.1.0</version>
            <executions>
                <execution>
                    <goals>
                        <goal>repackage</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
        
        <!-- Plugin Surefire (Testes) -->
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.0.0</version>
            <configuration>
                <includes>
                    <include>**/*Test.java</include>
                    <include>**/*Tests.java</include>
                </includes>
            </configuration>
        </plugin>
        
        <!-- Plugin Failsafe (Testes de Integração) -->
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-failsafe-plugin</artifactId>
            <version>3.0.0</version>
        </plugin>
    </plugins>
</build>
```

---

## Perfis

Perfis permitem diferentes configurações para diferentes ambientes:

```xml
<profiles>
    <!-- Perfil de Desenvolvimento -->
    <profile>
        <id>dev</id>
        <activation>
            <activeByDefault>true</activeByDefault>
        </activation>
        <properties>
            <environment>development</environment>
            <maven.test.skip>false</maven.test.skip>
        </properties>
    </profile>
    
    <!-- Perfil de Produção -->
    <profile>
        <id>prod</id>
        <properties>
            <environment>production</environment>
            <maven.test.skip>true</maven.test.skip>
        </properties>
    </profile>
</profiles>
```

### Ativação de Perfis

```bash
# Ativar perfil específico
mvn clean install -Pprod

# Ativar múltiplos perfis
mvn clean install -Pprod,qa

# Desativar perfil
mvn clean install -P!dev
```

---

## Comandos Maven

### Comandos Básicos

```bash
# Validar projeto
mvn validate

# Compilar código-fonte
mvn compile

# Executar testes
mvn test

# Empacotar (gera JAR/WAR)
mvn package

# Verificar integridade
mvn verify

# Instalar no repositório local
mvn install

# Deploy para repositório remoto
mvn deploy

# Limpar (remove target/)
mvn clean

# Limpar e compilar
mvn clean compile

# Ciclo completo
mvn clean install
```

### Comandos Úteis

```bash
# Executar apenas um teste
mvn test -Dtest=NomeDaClasseTest

# Pular testes
mvn install -DskipTests
mvn install -Dmaven.test.skip=true

# Atualizar dependências (snapshots)
mvn clean install -U

# Modo offline (usa apenas repositório local)
mvn install -o

# Modo verbose (mais informações)
mvn install -X

# Modo quiet (menos informações)
mvn install -q

# Definir propriedade
mvn install -Dproperty=value

# Listar dependências
mvn dependency:tree

# Analizar dependências
mvn dependency:analyze

# Atualizar versões
mvn versions:update-parent
```

---

## Repositórios

### Repositório Local

```bash
# Localização padrão
~/.m2/repository   # Linux/Mac
C:\Users\<usuario>\.m2\repository  # Windows

# Configurar localização customizada em settings.xml
<localRepository>/path/to/custom/repo</localRepository>
```

### Repositórios Remotos

```xml
<repositories>
    <!-- Repositório Maven Central (padrão) -->
    <repository>
        <id>central</id>
        <name>Maven Central Repository</name>
        <url>https://repo.maven.apache.org/maven2</url>
    </repository>
    
    <!-- Repositório customizado -->
    <repository>
        <id>custom-repo</id>
        <name>Custom Repository</name>
        <url>https://custom.example.com/maven</url>
        <releases>
            <enabled>true</enabled>
        </releases>
        <snapshots>
            <enabled>true</enabled>
            <updatePolicy>always</updatePolicy>
        </snapshots>
    </repository>
</repositories>

<!-- Repositório para plugins -->
<pluginRepositories>
    <pluginRepository>
        <id>central</id>
        <name>Maven Central Repository</name>
        <url>https://repo.maven.apache.org/maven2</url>
    </pluginRepository>
</pluginRepositories>
```

### Repositório para Deploy

```xml
<distributionManagement>
    <repository>
        <id>releases</id>
        <name>Release Repository</name>
        <url>https://example.com/maven/releases</url>
    </repository>
    <snapshotRepository>
        <id>snapshots</id>
        <name>Snapshot Repository</name>
        <url>https://example.com/maven/snapshots</url>
    </snapshotRepository>
</distributionManagement>
```

---

## Boas Práticas

### Estrutura de Projeto

```
meu-projeto/
├── src/
│   ├── main/
│   │   ├── java/          # Código-fonte
│   │   ├── resources/     # Arquivos de configuração
│   │   └── webapp/        # Arquivos web (se WAR)
│   └── test/
│       ├── java/          # Código de testes
│       └── resources/     # Recursos de testes
├── pom.xml
├── README.md
└── .gitignore
```

### pom.xml Well-Formed

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project>
    <!-- Cabeçalho -->
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.example</groupId>
    <artifactId>meu-projeto</artifactId>
    <version>1.0.0</version>
    
    <!-- Informações -->
    <name>Meu Projeto</name>
    <description>Descrição</description>
    <url>https://example.com</url>
    
    <!-- Propriedades -->
    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
    </properties>
    
    <!-- Parent (se aplicável) -->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.1.0</version>
    </parent>
    
    <!-- Dependências -->
    <dependencies>
        <!-- Ordenar por: Compile, Runtime, Test -->
    </dependencies>
    
    <!-- Build -->
    <build>
        <plugins>
            <!-- Plugins bem organizados -->
        </plugins>
    </build>
</project>
```

### Checklist de Boas Práticas

- ✓ Sempre use `<properties>` para versões
- ✓ Agrupe dependências por funcionalidade
- ✓ Use `<dependencyManagement>` em projetos multi-módulo
- ✓ Exclua dependências transientes desnecessárias
- ✓ Defina explicitamente versões de plugins
- ✓ Use perfis para diferentes ambientes
- ✓ Mantenha pom.xml limpo e legível
- ✓ Documente dependências customizadas
- ✓ Revise regularmente `mvn dependency:tree`

---

## Recursos Adicionais

- [Maven Official Documentation](https://maven.apache.org/)
- [Maven POM Reference](https://maven.apache.org/pom.html)
- [XML Specification](https://www.w3.org/XML/)
- [Maven Central Repository](https://mvnrepository.com/)

---

*Última atualização: Maio 2026*
