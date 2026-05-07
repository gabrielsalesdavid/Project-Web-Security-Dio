# Fundamentos de Java

## 📚 Índice
1. [Introdução](#introdução)
2. [Configuração do Ambiente](#configuração-do-ambiente)
3. [Conceitos Básicos](#conceitos-básicos)
4. [Tipos de Dados](#tipos-de-dados)
5. [Operadores](#operadores)
6. [Estruturas de Controle](#estruturas-de-controle)
7. [Arrays e Coleções](#arrays-e-coleções)
8. [Métodos e Funções](#métodos-e-funções)
9. [Programação Orientada a Objetos](#programação-orientada-a-objetos)
10. [Tratamento de Exceções](#tratamento-de-exceções)

---

## Introdução

Java é uma linguagem de programação orientada a objetos, multiplataforma e de propósito geral, criada pela Sun Microsystems (agora Oracle) em 1995. É conhecida pelo seu princípio **"Write Once, Run Anywhere" (WORA)**.

### Características Principais
- **Orientada a Objetos**: Tudo é um objeto
- **Multiplataforma**: Executável em qualquer máquina com JVM
- **Segura**: Sistema de tipos forte e gerenciamento automático de memória
- **Robusta**: Tratamento de exceções e verificação em tempo de compilação
- **Simples**: Sintaxe similar ao C++, mas mais limpa

---

## Configuração do Ambiente

### Instalação do JDK (Java Development Kit)

#### Windows
```bash
# Baixar do site: https://www.oracle.com/java/technologies/downloads/
# Executar o instalador e seguir as instruções

# Verificar instalação
java -version
javac -version
```

#### Linux/Mac
```bash
# Linux (Ubuntu/Debian)
sudo apt-get install openjdk-17-jdk

# Mac (Homebrew)
brew install openjdk@17

# Verificar
java -version
```

### Variáveis de Ambiente

**Windows:**
1. Abrir "Variáveis de Ambiente"
2. Criar `JAVA_HOME` apontando para a pasta de instalação do JDK
3. Adicionar `%JAVA_HOME%\bin` ao PATH

**Linux/Mac:**
```bash
export JAVA_HOME=/usr/libexec/java_home
export PATH=$JAVA_HOME/bin:$PATH
```

---

## Conceitos Básicos

### Estrutura de um Programa Java

```java
// Declaração do pacote
package dio.spring.web.security;

// Importações
import java.util.Scanner;

// Comentários
// Comentário de linha única
/* Comentário de múltiplas linhas */
/** Comentário de documentação (JavaDoc) */

// Definição da classe
public class HelloWorld {
    // Método principal (entry point)
    public static void main(String[] args) {
        System.out.println("Olá, Mundo!");
    }
}
```

### Compilação e Execução

```bash
# Compilar
javac HelloWorld.java

# Executar
java HelloWorld
```

### Pacotes (Packages)

Pacotes são organizações hierárquicas de classes:

```java
// Definir pacote (deve ser a primeira linha não-comentário)
package dio.spring.web.security;

// Importar pacotes
import java.util.*;
import java.util.List;
import static java.lang.Math.PI; // Importação estática
```

---

## Tipos de Dados

### Tipos Primitivos

| Tipo | Tamanho | Intervalo | Exemplo |
|------|---------|-----------|---------|
| `byte` | 1 byte | -128 a 127 | `byte b = 10;` |
| `short` | 2 bytes | -32.768 a 32.767 | `short s = 1000;` |
| `int` | 4 bytes | -2³¹ a 2³¹-1 | `int i = 42;` |
| `long` | 8 bytes | -2⁶³ a 2⁶³-1 | `long l = 1000L;` |
| `float` | 4 bytes | ±3.4 × 10³⁸ | `float f = 3.14f;` |
| `double` | 8 bytes | ±1.7 × 10³⁰⁸ | `double d = 3.14;` |
| `boolean` | 1 bit | true/false | `boolean b = true;` |
| `char` | 2 bytes | '\u0000' a '\uffff' | `char c = 'A';` |

### Tipos de Referência

```java
// String (imutável)
String nome = "João";
String mensagem = new String("Olá");

// Arrays
int[] numeros = new int[5];
int[] valores = {1, 2, 3, 4, 5};

// Objetos
User usuario = new User();
List<String> lista = new ArrayList<>();
```

### Conversão de Tipos

```java
// Widening (segura)
int numero = 10;
long numeroLongo = numero;

// Narrowing (requer casting)
double valor = 9.5;
int inteiro = (int) valor; // Resultado: 9

// Conversão de String
String texto = "42";
int numero = Integer.parseInt(texto);
```

---

## Operadores

### Operadores Aritméticos
```java
int a = 10, b = 3;
System.out.println(a + b);  // 13
System.out.println(a - b);  // 7
System.out.println(a * b);  // 30
System.out.println(a / b);  // 3
System.out.println(a % b);  // 1
```

### Operadores de Comparação
```java
int x = 5;
System.out.println(x == 5);   // true
System.out.println(x != 5);   // false
System.out.println(x > 3);    // true
System.out.println(x < 10);   // true
System.out.println(x >= 5);   // true
System.out.println(x <= 5);   // true
```

### Operadores Lógicos
```java
boolean a = true, b = false;
System.out.println(a && b);   // false (AND)
System.out.println(a || b);   // true  (OR)
System.out.println(!a);       // false (NOT)
```

### Operadores de Atribuição
```java
int x = 5;
x += 3;  // x = x + 3 = 8
x -= 2;  // x = x - 2 = 6
x *= 2;  // x = x * 2 = 12
x /= 3;  // x = x / 3 = 4
```

### Operadores Ternários
```java
int idade = 20;
String resultado = (idade >= 18) ? "Maior de idade" : "Menor de idade";
```

---

## Estruturas de Controle

### Condicional: if-else

```java
int nota = 7;

if (nota >= 8) {
    System.out.println("Aprovado");
} else if (nota >= 6) {
    System.out.println("Aprovado com ressalva");
} else {
    System.out.println("Reprovado");
}
```

### Switch-case

```java
int dia = 3;
String nomeDia;

switch(dia) {
    case 1:
        nomeDia = "Segunda";
        break;
    case 2:
        nomeDia = "Terça";
        break;
    case 3:
        nomeDia = "Quarta";
        break;
    default:
        nomeDia = "Dia inválido";
}
```

### Loops: for

```java
// For tradicional
for (int i = 0; i < 5; i++) {
    System.out.println(i);
}

// For-each (Enhanced for)
int[] numeros = {1, 2, 3, 4, 5};
for (int num : numeros) {
    System.out.println(num);
}
```

### Loops: while e do-while

```java
// while
int contador = 0;
while (contador < 5) {
    System.out.println(contador);
    contador++;
}

// do-while (executa pelo menos uma vez)
do {
    System.out.println(contador);
    contador++;
} while (contador < 5);
```

---

## Arrays e Coleções

### Arrays

```java
// Declaração e inicialização
int[] numeros = new int[5];
int[] valores = {10, 20, 30, 40, 50};

// Acessar elementos
System.out.println(valores[0]); // 10

// Modificar elementos
valores[1] = 25;

// Tamanho do array
System.out.println(valores.length); // 5

// Arrays multidimensionais
int[][] matriz = {{1, 2, 3}, {4, 5, 6}};
System.out.println(matriz[0][1]); // 2
```

### Coleções (Collections)

```java
// List (ordenada, permite duplicatas)
List<String> nomes = new ArrayList<>();
nomes.add("João");
nomes.add("Maria");
nomes.add("João");
System.out.println(nomes.size());  // 3
System.out.println(nomes.get(0));  // "João"

// Set (não ordenada, sem duplicatas)
Set<String> conjunto = new HashSet<>();
conjunto.add("A");
conjunto.add("B");
conjunto.add("A");
System.out.println(conjunto.size()); // 2

// Map (chave-valor)
Map<String, Integer> mapa = new HashMap<>();
mapa.put("João", 25);
mapa.put("Maria", 30);
System.out.println(mapa.get("João")); // 25

// Queue (fila, FIFO)
Queue<Integer> fila = new LinkedList<>();
fila.offer(1);
fila.offer(2);
System.out.println(fila.poll()); // 1
```

---

## Métodos e Funções

### Definição de Métodos

```java
// Sintaxe geral
[modificador] tipoRetorno nomeMetodo([parâmetros]) {
    // Corpo do método
    return valor;
}

// Exemplos
public void cumprimentar() {
    System.out.println("Olá!");
}

public int somar(int a, int b) {
    return a + b;
}

public static double calcularMedia(double[] notas) {
    double soma = 0;
    for (double nota : notas) {
        soma += nota;
    }
    return soma / notas.length;
}
```

### Sobrecarga de Métodos (Overloading)

```java
public class Calculadora {
    // Múltiplas versões do mesmo método
    public int somar(int a, int b) {
        return a + b;
    }
    
    public double somar(double a, double b) {
        return a + b;
    }
    
    public int somar(int a, int b, int c) {
        return a + b + c;
    }
}
```

### Parâmetros Variáveis

```java
public int somarTodos(int... numeros) {
    int soma = 0;
    for (int num : numeros) {
        soma += num;
    }
    return soma;
}

// Uso
System.out.println(somarTodos(1, 2, 3, 4, 5)); // 15
```

---

## Programação Orientada a Objetos

### Classes e Objetos

```java
public class User {
    // Atributos (variáveis)
    private String nome;
    private int idade;
    private String email;
    
    // Construtor
    public User() {
        this.nome = "Desconhecido";
        this.idade = 0;
    }
    
    public User(String nome, int idade, String email) {
        this.nome = nome;
        this.idade = idade;
        this.email = email;
    }
    
    // Getters e Setters
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    // Métodos
    public void exibirInfo() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Email: " + email);
    }
    
    @Override
    public String toString() {
        return "User{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", email='" + email + '\'' +
                '}';
    }
}
```

### Herança

```java
// Classe base (superclasse)
public class Pessoa {
    protected String nome;
    
    public Pessoa(String nome) {
        this.nome = nome;
    }
    
    public void apresentar() {
        System.out.println("Olá, meu nome é " + nome);
    }
}

// Classe derivada (subclasse)
public class Funcionario extends Pessoa {
    private double salario;
    
    public Funcionario(String nome, double salario) {
        super(nome); // Chama construtor da superclasse
        this.salario = salario;
    }
    
    @Override
    public void apresentar() {
        super.apresentar(); // Chama método da superclasse
        System.out.println("Meu salário é: " + salario);
    }
}
```

### Polimorfismo

```java
// Interface
public interface Animal {
    void fazer_som();
}

// Implementações
public class Cachorro implements Animal {
    @Override
    public void fazer_som() {
        System.out.println("Au au!");
    }
}

public class Gato implements Animal {
    @Override
    public void fazer_som() {
        System.out.println("Miau!");
    }
}

// Uso (Polimorfismo)
public class Main {
    public static void main(String[] args) {
        Animal animal1 = new Cachorro();
        Animal animal2 = new Gato();
        
        animal1.fazer_som(); // Au au!
        animal2.fazer_som(); // Miau!
    }
}
```

### Encapsulamento

```java
public class ContaBancaria {
    // Atributo privado
    private double saldo;
    
    // Construtor
    public ContaBancaria(double saldoInicial) {
        if (saldoInicial > 0) {
            this.saldo = saldoInicial;
        } else {
            this.saldo = 0;
        }
    }
    
    // Método público para depositar
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
        }
    }
    
    // Método público para sacar
    public boolean sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            return true;
        }
        return false;
    }
    
    // Getter para consultar saldo
    public double getSaldo() {
        return saldo;
    }
}
```

---

## Tratamento de Exceções

### Try-Catch-Finally

```java
try {
    // Código que pode gerar exceção
    String[] nomes = {"João", "Maria"};
    System.out.println(nomes[5]); // ArrayIndexOutOfBoundsException
} catch (ArrayIndexOutOfBoundsException e) {
    // Tratamento específico
    System.out.println("Índice fora dos limites!");
    e.printStackTrace();
} catch (Exception e) {
    // Tratamento genérico
    System.out.println("Erro geral: " + e.getMessage());
} finally {
    // Sempre executado (liberação de recursos)
    System.out.println("Bloco finally executado");
}
```

### Try-with-resources

```java
try (Scanner scanner = new Scanner(System.in)) {
    System.out.println("Digite seu nome: ");
    String nome = scanner.nextLine();
} catch (Exception e) {
    System.out.println("Erro: " + e.getMessage());
} // Resource é automaticamente fechado
```

### Exceções Customizadas

```java
// Definir exceção customizada
public class IdadeInvalidaException extends Exception {
    public IdadeInvalidaException(String mensagem) {
        super(mensagem);
    }
}

// Usar exceção customizada
public class Pessoa {
    private int idade;
    
    public void setIdade(int idade) throws IdadeInvalidaException {
        if (idade < 0 || idade > 150) {
            throw new IdadeInvalidaException("Idade deve estar entre 0 e 150!");
        }
        this.idade = idade;
    }
}
```

---

## Boas Práticas

1. **Nomenclatura**: Use camelCase para variáveis e métodos, PascalCase para classes
2. **Comentários**: Documente código complexo com comentários e JavaDoc
3. **Encapsulamento**: Sempre use modificadores de acesso apropriados
4. **DRY (Don't Repeat Yourself)**: Evite código duplicado
5. **SOLID**: Siga os princípios SOLID de design
6. **Tratamento de Exceções**: Sempre trate exceções apropriadamente
7. **Null Safety**: Verifique valores nulos antes de usar

---

## Recursos Adicionais

- [Documentação Oficial do Java](https://docs.oracle.com/en/java/)
- [Java Tutorial - Oracle](https://docs.oracle.com/javase/tutorial/)
- [JavaDoc Standard Tags](https://docs.oracle.com/javase/7/docs/technotes/tools/windows/javadoc.html)

---

*Última atualização: Maio 2026*
