# Conceitos Avançados de XML e Maven

## 📚 Índice
1. [XML Avançado](#xml-avançado)
2. [Validação XML](#validação-xml)
3. [XPath e XQuery](#xpath-e-xquery)
4. [Maven Multi-Módulo](#maven-multi-módulo)
5. [Maven Archetype](#maven-archetype)
6. [Troubleshooting Maven](#troubleshooting-maven)
7. [Integração Contínua](#integração-contínua)
8. [Best Practices](#best-practices)
9. [Performance](#performance)
10. [Segurança](#segurança)

---

## XML Avançado

### Namespaces (Namespaces)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                             http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <!-- xsi = XML Schema Instance -->
</project>
```

**Usar namespaces previne conflitos de nomes:**

```xml
<!-- Sem namespace (conflito potencial) -->
<person>
    <name>João</name>
</person>

<!-- Com namespace -->
<h:person xmlns:h="http://example.com/hr">
    <h:name>João</h:name>
</h:person>

<f:person xmlns:f="http://example.com/finance">
    <f:name>Maria</f:name>
</f:person>
```

### DTD (Document Type Definition)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE pessoa [
  <!ELEMENT pessoa (nome, idade)>
  <!ELEMENT nome (#PCDATA)>
  <!ELEMENT idade (#PCDATA)>
  <!ATTLIST pessoa id ID #REQUIRED>
]>
<pessoa id="1">
    <nome>João</nome>
    <idade>30</idade>
</pessoa>
```

### XML Schema (XSD)

```xml
<!-- arquivo: pessoa.xsd -->
<?xml version="1.0" encoding="UTF-8"?>
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema">
    <xs:element name="pessoa">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="nome" type="xs:string"/>
                <xs:element name="idade" type="xs:integer"/>
                <xs:element name="email" type="xs:string" minOccurs="0"/>
            </xs:sequence>
            <xs:attribute name="id" type="xs:integer" use="required"/>
        </xs:complexType>
    </xs:element>
</xs:schema>

<!-- Usar o schema -->
<?xml version="1.0" encoding="UTF-8"?>
<pessoa xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:noNamespaceSchemaLocation="pessoa.xsd"
        id="1">
    <nome>João</nome>
    <idade>30</idade>
</pessoa>
```

### XSLT (Transformação XML)

```xml
<!-- entrada.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<pessoas>
    <pessoa id="1">
        <nome>João</nome>
        <idade>30</idade>
    </pessoa>
    <pessoa id="2">
        <nome>Maria</nome>
        <idade>25</idade>
    </pessoa>
</pessoas>

<!-- transformacao.xsl -->
<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    
    <xsl:template match="/">
        <html>
            <body>
                <table>
                    <xsl:for-each select="pessoas/pessoa">
                        <tr>
                            <td><xsl:value-of select="nome"/></td>
                            <td><xsl:value-of select="idade"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
```

---

## Validação XML

### Validar com SAX Parser

```java
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ValidadorXML extends DefaultHandler {
    public static void validar(String caminhoXML) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setValidating(true);
        
        SAXParser parser = factory.newSAXParser();
        parser.parse(caminhoXML, new ValidadorXML());
        
        System.out.println("XML válido!");
    }
    
    @Override
    public void warning(SAXException e) {
        System.out.println("Aviso: " + e.getMessage());
    }
    
    @Override
    public void error(SAXException e) {
        System.out.println("Erro: " + e.getMessage());
    }
    
    @Override
    public void fatalError(SAXException e) throws SAXException {
        System.out.println("Erro Fatal: " + e.getMessage());
        throw e;
    }
}
```

### Validar com DOM Parser

```java
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

public class ValidadorDOM {
    public static void validar(String caminhoXML, String caminhoXSD) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setValidating(true);
        
        factory.setAttribute(
            "http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation",
            caminhoXSD
        );
        
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(caminhoXML);
        
        System.out.println("XML válido contra schema!");
    }
}
```

---

## XPath e XQuery

### XPath Básico

```xml
<!-- Documento exemplo -->
<?xml version="1.0"?>
<biblioteca>
    <livro id="1">
        <titulo>Java Moderno</titulo>
        <autor>João Silva</autor>
        <preco>89.90</preco>
    </livro>
    <livro id="2">
        <titulo>Spring Boot</titulo>
        <autor>Maria Santos</autor>
        <preco>75.50</preco>
    </livro>
</biblioteca>

<!-- XPath Queries -->
/biblioteca/livro                          <!-- Todos os livros -->
/biblioteca/livro[1]                       <!-- Primeiro livro -->
/biblioteca/livro[@id='1']                 <!-- Livro com id=1 -->
/biblioteca/livro/titulo                   <!-- Todos os títulos -->
//autor                                    <!-- Todos os autores (qualquer nível) -->
/biblioteca/livro[preco > 80]              <!-- Livros com preço > 80 -->
/biblioteca/livro[position() > 1]          <!-- Livros após o primeiro -->
count(/biblioteca/livro)                   <!-- Contar livros -->
```

### XPath em Java

```java
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class ExemploXPath {
    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("biblioteca.xml");
        
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        
        // Query 1: Todos os títulos
        XPathExpression expr = xpath.compile("/biblioteca/livro/titulo");
        NodeList titulos = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        
        for (int i = 0; i < titulos.getLength(); i++) {
            System.out.println(titulos.item(i).getTextContent());
        }
        
        // Query 2: Livros caros (> 80)
        expr = xpath.compile("/biblioteca/livro[preco > 80]");
        NodeList livrosCaros = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        System.out.println("Livros caros: " + livrosCaros.getLength());
        
        // Query 3: Contar livros
        expr = xpath.compile("count(/biblioteca/livro)");
        Double count = (Double) expr.evaluate(doc, XPathConstants.NUMBER);
        System.out.println("Total de livros: " + count.intValue());
    }
}
```

---

## Maven Multi-Módulo

### Estrutura do Projeto

```
projeto-pai/
├── pom.xml (POM do agregador)
├── modulo-core/
│   ├── pom.xml
│   └── src/
├── modulo-web/
│   ├── pom.xml
│   └── src/
└── modulo-api/
    ├── pom.xml
    └── src/
```

### POM do Agregador

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project>
    <modelVersion>4.0.0</modelVersion>
    
    <groupId>com.example</groupId>
    <artifactId>projeto-pai</artifactId>
    <version>1.0.0</version>
    <packaging>pom</packaging>
    
    <name>Projeto Pai</name>
    
    <!-- Declarar módulos -->
    <modules>
        <module>modulo-core</module>
        <module>modulo-web</module>
        <module>modulo-api</module>
    </modules>
    
    <!-- Propriedades comuns -->
    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <spring.boot.version>3.1.0</spring.boot.version>
    </properties>
    
    <!-- Gerenciamento centralizado de dependências -->
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
                <version>${spring.boot.version}</version>
            </dependency>
        </dependencies>
    </dependencyManagement>
</project>
```

### POM dos Módulos

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project>
    <modelVersion>4.0.0</modelVersion>
    
    <!-- Referência ao POM pai -->
    <parent>
        <groupId>com.example</groupId>
        <artifactId>projeto-pai</artifactId>
        <version>1.0.0</version>
        <relativePath>../pom.xml</relativePath>
    </parent>
    
    <artifactId>modulo-core</artifactId>
    <name>Módulo Core</name>
    
    <dependencies>
        <!-- Sem versão (vem do dependencyManagement pai) -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>
</project>
```

### Compilar Multi-Módulo

```bash
# Compilar tudo do pai
cd projeto-pai
mvn clean install

# Compilar apenas um módulo
mvn clean install -pl modulo-core

# Compilar um módulo e dependentes
mvn clean install -pl modulo-core -am

# Compilar um módulo e módulos que o dependem
mvn clean install -pl modulo-core -amd
```

---

## Maven Archetype

### Criar Projeto com Archetype

```bash
# Interativo
mvn archetype:generate

# Não-interativo
mvn archetype:generate \
  -DgroupId=com.example \
  -DartifactId=meu-app \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DarchetypeVersion=1.4 \
  -DinteractiveMode=false
```

### Archetypes Populares

```bash
# Web App
mvn archetype:generate \
  -DarchetypeArtifactId=maven-archetype-webapp

# Spring Boot
# (usar Spring Initializr é mais fácil)

# JavaFX
mvn archetype:generate \
  -DarchetypeArtifactId=javafx-maven-plugin
```

### Criar Archetype Customizado

```bash
# Criar projeto com estrutura desejada
mkdir -p src/main/java
mkdir -p src/test/java
mkdir -p src/main/resources

# Transformar em archetype
cd projeto-template
mvn archetype:create-from-project

# Instalar
cd target/generated-sources/archetype
mvn install

# Usar
mvn archetype:generate \
  -DarchetypeGroupId=com.example \
  -DarchetypeArtifactId=meu-archetype
```

---

## Troubleshooting Maven

### Problemas Comuns

```bash
# 1. Limpar cache e reconstruir
mvn clean install -U

# 2. Atualizar snapshots
mvn clean install -U -Daether.updateCheckManager=always

# 3. Verificar árvore de dependências
mvn dependency:tree
mvn dependency:tree -Dincludes=org.springframework

# 4. Analisar conflitos
mvn dependency:tree -Doutput=myfile.txt

# 5. Diagnóstico detalhado
mvn -X clean install 2>&1 | tee maven.log

# 6. Modo offline
mvn install -o

# 7. Remover repositório local corrompido
rm -rf ~/.m2/repository
mvn clean install

# 8. Forçar download de arquivos
mvn dependency:sources
mvn dependency:resolve -Dclassifier=javadoc
```

### Configurar settings.xml

```xml
<!-- ~/.m2/settings.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                        http://maven.apache.org/xsd/settings-1.0.0.xsd">
    
    <localRepository>/custom/path/to/repository</localRepository>
    
    <servers>
        <server>
            <id>private-repo</id>
            <username>usuario</username>
            <password>senha</password>
        </server>
    </servers>
    
    <profiles>
        <profile>
            <id>default</id>
            <repositories>
                <repository>
                    <id>central</id>
                    <url>https://repo.maven.apache.org/maven2</url>
                </repository>
            </repositories>
        </profile>
    </profiles>
    
    <activeProfiles>
        <activeProfile>default</activeProfile>
    </activeProfiles>
</settings>
```

---

## Integração Contínua

### GitHub Actions com Maven

```yaml
# .github/workflows/maven.yml
name: Maven Build

on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
    - uses: actions/checkout@v2
    
    - name: Set up JDK 17
      uses: actions/setup-java@v2
      with:
        java-version: '17'
        distribution: 'adopt'
    
    - name: Build with Maven
      run: mvn clean install
    
    - name: Run Tests
      run: mvn test
    
    - name: Generate Coverage Report
      run: mvn jacoco:report
    
    - name: Upload to Codecov
      uses: codecov/codecov-action@v2
      with:
        files: ./target/site/jacoco/index.html
```

### Jenkins com Maven

```groovy
pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/user/repo.git'
            }
        }
        
        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }
        
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        
        stage('Deploy') {
            steps {
                sh 'mvn deploy'
            }
        }
    }
    
    post {
        always {
            junit 'target/surefire-reports/*.xml'
            archiveArtifacts artifacts: 'target/*.jar'
        }
    }
}
```

---

## Best Practices

### Organização do pom.xml

```
1. Declaração XML
2. Project
3. Model Version
4. Parent (se aplicável)
5. Basic Info (groupId, artifactId, version)
6. Properties
7. Dependencies
8. DependencyManagement
9. Build
10. Profiles
```

### Nomeação de Artefatos

- **Bom**: `spring-boot-starter-web`, `commons-lang3`
- **Ruim**: `lib`, `util`, `common`, `helper`

### Versionamento Semântico

```
MAJOR.MINOR.PATCH[-QUALIFIER]

1.0.0           Release
1.0.1           Bug fix
1.1.0           Novo feature
2.0.0           Breaking change
1.0.0-alpha     Alpha
1.0.0-beta      Beta
1.0.0-SNAPSHOT  Em desenvolvimento
```

---

## Performance

### Otimizações Maven

```bash
# Usar threads para compilação paralela
mvn -T 1C clean install

# Compilação offline
mvn install -o

# Pular testes
mvn package -DskipTests

# Cache de dependências
mvn install -q -Dmaven.javadoc.skip

# Usar mvnd (Maven Daemon - mais rápido)
mvnd clean install
```

### Configuração no pom.xml

```xml
<properties>
    <maven.compiler.fork>true</maven.compiler.fork>
    <maven.compiler.maxmem>1024m</maven.compiler.maxmem>
    <argLine>-Xmx1024m -XX:MaxPermSize=256m</argLine>
</properties>
```

---

## Segurança

### Dependências Vulneráveis

```bash
# Verificar vulnerabilidades
mvn org.owasp:dependency-check-maven:check

# Atualizar dependências
mvn versions:update-parent
mvn versions:update-properties

# Usar repositórios HTTPS
<url>https://repo.maven.apache.org/maven2</url>
```

### Configuração Segura

```xml
<!-- settings.xml -->
<servers>
    <server>
        <id>private-repo</id>
        <username>${env.MAVEN_USERNAME}</username>
        <password>${env.MAVEN_PASSWORD}</password>
    </server>
</servers>

<!-- Usar variáveis de ambiente -->
```

```bash
# Em CI/CD
export MAVEN_USERNAME=user
export MAVEN_PASSWORD=pass
mvn deploy
```

---

## Recursos Adicionais

- [Maven Advanced Configuration](https://maven.apache.org/guides/index.html)
- [XPath Tutorial](https://www.w3schools.com/xml/xpath_intro.asp)
- [XML Specification](https://www.w3.org/XML/)

---

*Última atualização: Maio 2026*
