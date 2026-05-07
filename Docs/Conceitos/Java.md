# Conceitos Avançados de Java

## 📚 Índice
1. [Programação Funcional](#programação-funcional)
2. [Streams API](#streams-api)
3. [Lambdas](#lambdas)
4. [Generics](#generics)
5. [Annotations](#annotations)
6. [Reflexão (Reflection)](#reflexão)
7. [Concorrência e Threads](#concorrência-e-threads)
8. [Padrões de Design](#padrões-de-design)
9. [SOLID Principles](#solid-principles)
10. [Java Moderno (Java 8+)](#java-moderno)

---

## Programação Funcional

Java introduziu suporte a programação funcional a partir do Java 8. Isso permite um estilo de programação mais declarativo.

### Conceito Fundamental

```java
// Imperativo (tradicional)
List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5);
List<Integer> pares = new ArrayList<>();
for (Integer num : numeros) {
    if (num % 2 == 0) {
        pares.add(num);
    }
}

// Funcional (declarativo)
List<Integer> pares = numeros.stream()
    .filter(num -> num % 2 == 0)
    .collect(Collectors.toList());
```

### Interfaces Funcionais

Uma interface funcional tem exatamente um método abstrato:

```java
@FunctionalInterface
public interface Operacao {
    int executar(int a, int b);
}

// Implementação tradicional
Operacao somar = new Operacao() {
    @Override
    public int executar(int a, int b) {
        return a + b;
    }
};

// Implementação com Lambda (mais concisa)
Operacao somar = (a, b) -> a + b;
Operacao subtrair = (a, b) -> a - b;

System.out.println(somar.executar(10, 5));     // 15
System.out.println(subtrair.executar(10, 5));  // 5
```

### Interfaces Funcionais Built-in

```java
// Predicate<T>: retorna boolean
Predicate<Integer> ehPar = n -> n % 2 == 0;
System.out.println(ehPar.test(4)); // true

// Function<T, R>: transforma T em R
Function<Integer, String> converterString = n -> "Número: " + n;
System.out.println(converterString.apply(42)); // "Número: 42"

// Consumer<T>: consome T (sem retorno)
Consumer<String> imprimirMaiuscula = s -> System.out.println(s.toUpperCase());
imprimirMaiuscula.accept("java"); // "JAVA"

// Supplier<T>: fornece T (sem parâmetros)
Supplier<Double> numeroAleatorio = () -> Math.random();
System.out.println(numeroAleatorio.get());
```

---

## Streams API

A Streams API fornece uma forma funcional e eficiente de processar coleções de dados.

### Estrutura Básica

```java
List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

// Stream pipeline: Fonte -> Operações Intermediárias -> Operação Terminal
int resultado = numeros.stream()              // Fonte
    .filter(n -> n > 3)                       // Intermediária
    .map(n -> n * 2)                          // Intermediária
    .reduce(0, Integer::sum);                 // Terminal
    
System.out.println(resultado); // (4+5+6+7+8+9+10)*2 = 98
```

### Operações Intermediárias

```java
List<String> palavras = Arrays.asList("Java", "Stream", "API", "Funcional");

// filter: filtra elementos
palavras.stream()
    .filter(p -> p.length() > 4)
    .forEach(System.out::println); // Stream, Funcional

// map: transforma elementos
palavras.stream()
    .map(String::toUpperCase)
    .forEach(System.out::println); // JAVA, STREAM, API, FUNCIONAL

// flatMap: mapeia e achata
List<List<Integer>> listas = Arrays.asList(
    Arrays.asList(1, 2, 3),
    Arrays.asList(4, 5, 6)
);
listas.stream()
    .flatMap(List::stream)
    .forEach(System.out::println); // 1, 2, 3, 4, 5, 6

// distinct: remove duplicatas
Arrays.asList(1, 2, 2, 3, 3, 3, 4)
    .stream()
    .distinct()
    .forEach(System.out::println); // 1, 2, 3, 4

// sorted: ordena elementos
Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6)
    .stream()
    .sorted()
    .forEach(System.out::println); // 1, 1, 2, 3, 4, 5, 6, 9

// limit e skip
Arrays.asList(1, 2, 3, 4, 5)
    .stream()
    .skip(2)
    .limit(2)
    .forEach(System.out::println); // 3, 4
```

### Operações Terminais

```java
List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5);

// forEach: executa ação para cada elemento
numeros.stream()
    .forEach(System.out::println);

// collect: coleta resultados
List<Integer> pares = numeros.stream()
    .filter(n -> n % 2 == 0)
    .collect(Collectors.toList()); // [2, 4]

// count: conta elementos
long quantidade = numeros.stream()
    .count(); // 5

// reduce: combina elementos
Optional<Integer> soma = numeros.stream()
    .reduce(Integer::sum); // Optional[15]

// min e max
Optional<Integer> minimo = numeros.stream()
    .min(Integer::compare); // Optional[1]

Optional<Integer> maximo = numeros.stream()
    .max(Integer::compare); // Optional[5]

// anyMatch, allMatch, noneMatch
boolean temPar = numeros.stream()
    .anyMatch(n -> n % 2 == 0); // true

boolean todosPositivos = numeros.stream()
    .allMatch(n -> n > 0); // true

boolean nenhumNegativo = numeros.stream()
    .noneMatch(n -> n < 0); // true

// findFirst, findAny
Optional<Integer> primeiro = numeros.stream()
    .findFirst(); // Optional[1]
```

### Collectors Úteis

```java
List<String> nomes = Arrays.asList("Alice", "Bob", "Charlie", "Alice");

// toList
List<String> lista = nomes.stream()
    .collect(Collectors.toList());

// toSet
Set<String> conjunto = nomes.stream()
    .collect(Collectors.toSet());

// toMap
Map<String, Integer> mapa = nomes.stream()
    .distinct()
    .collect(Collectors.toMap(
        Function.identity(),
        String::length
    )); // {Alice=5, Bob=3, Charlie=7}

// groupingBy
Map<Integer, List<String>> agrupado = nomes.stream()
    .collect(Collectors.groupingBy(String::length));
    // {3=[Bob], 5=[Alice, Alice], 7=[Charlie]}

// joining
String unido = nomes.stream()
    .collect(Collectors.joining(", "));
    // "Alice, Bob, Charlie, Alice"

// summarizingInt
IntSummaryStatistics stats = nomes.stream()
    .collect(Collectors.summarizingInt(String::length));
    // count=4, sum=20, min=3, max=7, average=5.0
```

---

## Lambdas

Expressões lambda são uma forma concisa de representar funções anônimas.

### Sintaxe

```java
// Sem parâmetros
() -> System.out.println("Olá");

// Um parâmetro (parênteses opcionais)
x -> x * 2
(x) -> x * 2

// Múltiplos parâmetros
(x, y) -> x + y
(x, y) -> {
    int resultado = x + y;
    return resultado;
}

// Com tipos explícitos
(int x, int y) -> x + y
(String s) -> s.toUpperCase()
```

### Exemplos Práticos

```java
// Comparador
List<String> palavras = Arrays.asList("Java", "Python", "C");
palavras.sort((a, b) -> a.length() - b.length());
// [C, Java, Python]

// Thread
new Thread(() -> System.out.println("Executando em thread")).start();

// Listener (Event Handler)
button.setOnClickListener(event -> handleButtonClick(event));

// Map com lambda
Map<String, Integer> mapa = new HashMap<>();
mapa.compute("chave", (k, v) -> v == null ? 1 : v + 1);
```

### Referências de Método

Uma forma ainda mais concisa de usar lambdas:

```java
// Lambda tradicional
list.forEach(x -> System.out.println(x));

// Referência de método (equivalente)
list.forEach(System::out::println);

// Outros exemplos
Function<Integer, String> converter = Object::toString;
Predicate<String> vazio = String::isEmpty;
Supplier<LocalDateTime> agora = LocalDateTime::now;

// Reference para construtores
Supplier<ArrayList> criarLista = ArrayList::new;
Function<Integer, int[]> criarArray = int[]::new;
```

---

## Generics

Generics permitem que classes, interfaces e métodos funcionem com diferentes tipos, mantendo segurança de tipos.

### Tipos Genéricos Básicos

```java
// Classe genérica
public class Caixa<T> {
    private T conteudo;
    
    public void colocar(T item) {
        this.conteudo = item;
    }
    
    public T pegar() {
        return conteudo;
    }
}

// Uso
Caixa<String> caixaString = new Caixa<>();
caixaString.colocar("Olá");
String valor = caixaString.pegar(); // Sem casting!

Caixa<Integer> caixaInt = new Caixa<>();
caixaInt.colocar(42);
Integer numero = caixaInt.pegar();
```

### Múltiplos Tipos Genéricos

```java
public class Par<K, V> {
    private K chave;
    private V valor;
    
    public Par(K chave, V valor) {
        this.chave = chave;
        this.valor = valor;
    }
    
    public K getChave() { return chave; }
    public V getValor() { return valor; }
}

// Uso
Par<String, Integer> registro = new Par<>("idade", 30);
String chave = registro.getChave();
Integer valor = registro.getValor();
```

### Bounds (Limites)

```java
// Upper Bounded - T deve ser Número ou sua subclasse
public class Caixa<T extends Number> {
    private T valor;
    
    public double getDouble() {
        return valor.doubleValue();
    }
}

// Lower Bounded (em métodos com wildcards)
public void processar(List<? super Integer> lista) {
    lista.add(10);
}

// Wildcard
public void imprimirCaixa(Caixa<?> caixa) {
    System.out.println(caixa.pegar());
}

// Bounded Wildcard
public void processar(List<? extends Number> lista) {
    for (Number num : lista) {
        System.out.println(num.doubleValue());
    }
}
```

### Métodos Genéricos

```java
// Método genérico
public static <T> void imprimirArray(T[] array) {
    for (T elemento : array) {
        System.out.println(elemento);
    }
}

// Uso
Integer[] inteiros = {1, 2, 3};
imprimirArray(inteiros);

String[] strings = {"a", "b", "c"};
imprimirArray(strings);

// Com múltiplos tipos
public static <K, V> void imprimirMapa(Map<K, V> mapa) {
    mapa.forEach((k, v) -> System.out.println(k + " -> " + v));
}
```

---

## Annotations

Annotations fornecem metadados sobre o código (não fazem parte da lógica).

### Annotations Built-in

```java
public class Exemplo {
    // Indica que o método sobrescreve um método da superclasse
    @Override
    public String toString() {
        return super.toString();
    }
    
    // Indica que o elemento está deprecated
    @Deprecated(since = "2.0", forRemoval = true)
    public void metodoAntigo() {
        // ...
    }
    
    // Suprime avisos do compilador
    @SuppressWarnings("unchecked")
    public void metodoComCasting() {
        List lista = new ArrayList(); // Raw type
    }
    
    // Indica que é uma interface funcional
    @FunctionalInterface
    interface Operacao {
        void executar();
    }
}
```

### Criando Annotations Customizadas

```java
// Definição
@Retention(RetentionPolicy.RUNTIME) // Disponível em runtime
@Target(ElementType.METHOD)         // Aplicável apenas em métodos
public @interface ValidarPerimissao {
    String valor();
    int nivel() default 1;
}

// Uso
public class Servico {
    @ValidarPerimissao(valor = "ADMIN", nivel = 3)
    public void operacaoSensivel() {
        // ...
    }
}

// Processamento em Runtime
Method metodo = Servico.class.getMethod("operacaoSensivel");
if (metodo.isAnnotationPresent(ValidarPerimissao.class)) {
    ValidarPerimissao anotacao = metodo.getAnnotation(ValidarPerimissao.class);
    System.out.println("Permissão: " + anotacao.valor());
}
```

---

## Reflexão

Reflexão permite inspecionar e modificar classes, métodos e campos em runtime.

### Obtendo Classe

```java
// Três formas de obter a classe
Class<?> classe1 = String.class;
Class<?> classe2 = "texto".getClass();
Class<?> classe3 = Class.forName("java.lang.String");
```

### Inspecionando Métodos

```java
Class<?> classe = Usuario.class;

// Obter todos os métodos públicos
Method[] metodos = classe.getMethods();

// Obter método específico
Method metodo = classe.getMethod("getNome");

// Inspecionar assinatura
System.out.println(metodo.getName());           // getNome
System.out.println(metodo.getReturnType());     // class java.lang.String
System.out.println(Arrays.toString(metodo.getParameterTypes())); // []
```

### Inspecionando Campos

```java
Field[] campos = Usuario.class.getDeclaredFields();

for (Field campo : campos) {
    System.out.println("Nome: " + campo.getName());
    System.out.println("Tipo: " + campo.getType());
    System.out.println("Modificadores: " + Modifier.toString(campo.getModifiers()));
}
```

### Criando Instâncias Dinamicamente

```java
// Criar instância
Constructor<?> construtor = Usuario.class.getConstructor(String.class, int.class);
Object instancia = construtor.newInstance("João", 30);

// Chamar método dinamicamente
Method metodo = Usuario.class.getMethod("getNome");
String nome = (String) metodo.invoke(instancia);

// Acessar campos dinamicamente
Field campo = Usuario.class.getDeclaredField("nome");
campo.setAccessible(true);
campo.set(instancia, "Maria");
String novoNome = (String) campo.get(instancia);
```

---

## Concorrência e Threads

### Criando Threads

```java
// Opção 1: Estender Thread
class MinhaThread extends Thread {
    @Override
    public void run() {
        System.out.println("Executando em thread: " + getName());
    }
}

MinhaThread thread = new MinhaThread();
thread.start();

// Opção 2: Implementar Runnable (mais flexível)
class MinhaRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Executando via Runnable");
    }
}

Thread thread = new Thread(new MinhaRunnable());
thread.start();

// Opção 3: Lambda (Java 8+)
new Thread(() -> System.out.println("Via lambda")).start();
```

### Sincronização

```java
public class Contador {
    private int valor = 0;
    
    // Bloco sincronizado
    public void incrementar() {
        synchronized (this) {
            valor++;
        }
    }
    
    // Método sincronizado (equivalente)
    public synchronized void decrementar() {
        valor--;
    }
    
    public synchronized int getValor() {
        return valor;
    }
}

// Lock explícito
public class ContadorComLock {
    private int valor = 0;
    private final Lock lock = new ReentrantLock();
    
    public void incrementar() {
        lock.lock();
        try {
            valor++;
        } finally {
            lock.unlock();
        }
    }
}
```

### ExecutorService

```java
// Criar pool de threads
ExecutorService executor = Executors.newFixedThreadPool(3);

// Enviar tarefas
for (int i = 0; i < 5; i++) {
    final int id = i;
    executor.execute(() -> System.out.println("Tarefa " + id));
}

// Aguardar conclusão
executor.shutdown();
executor.awaitTermination(10, TimeUnit.SECONDS);

// Obter resultado
ExecutorService executorComResultado = Executors.newSingleThreadExecutor();
Future<String> futuro = executorComResultado.submit(() -> {
    Thread.sleep(2000);
    return "Resultado após 2 segundos";
});

try {
    String resultado = futuro.get(5, TimeUnit.SECONDS);
    System.out.println(resultado);
} catch (TimeoutException e) {
    System.out.println("Timeout!");
}
```

---

## Padrões de Design

### Singleton

```java
public class DatabaseConnection {
    private static DatabaseConnection instancia;
    
    private DatabaseConnection() {}
    
    public static synchronized DatabaseConnection getInstance() {
        if (instancia == null) {
            instancia = new DatabaseConnection();
        }
        return instancia;
    }
}

// Versão thread-safe com eager initialization
public class DatabaseConnection {
    private static final DatabaseConnection INSTANCIA = new DatabaseConnection();
    
    private DatabaseConnection() {}
    
    public static DatabaseConnection getInstance() {
        return INSTANCIA;
    }
}
```

### Builder

```java
public class Usuario {
    private final String nome;
    private final String email;
    private final int idade;
    private final String telefone;
    
    private Usuario(Builder builder) {
        this.nome = builder.nome;
        this.email = builder.email;
        this.idade = builder.idade;
        this.telefone = builder.telefone;
    }
    
    public static class Builder {
        private String nome;
        private String email;
        private int idade;
        private String telefone;
        
        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }
        
        public Builder email(String email) {
            this.email = email;
            return this;
        }
        
        public Builder idade(int idade) {
            this.idade = idade;
            return this;
        }
        
        public Builder telefone(String telefone) {
            this.telefone = telefone;
            return this;
        }
        
        public Usuario build() {
            return new Usuario(this);
        }
    }
}

// Uso
Usuario usuario = new Usuario.Builder()
    .nome("João")
    .email("joao@example.com")
    .idade(30)
    .telefone("123456789")
    .build();
```

### Factory

```java
interface Veiculo {
    void dirigir();
}

class Carro implements Veiculo {
    @Override
    public void dirigir() {
        System.out.println("Dirigindo carro");
    }
}

class Moto implements Veiculo {
    @Override
    public void dirigir() {
        System.out.println("Dirigindo moto");
    }
}

class FabricaVeiculo {
    public static Veiculo criar(String tipo) {
        if ("carro".equalsIgnoreCase(tipo)) {
            return new Carro();
        } else if ("moto".equalsIgnoreCase(tipo)) {
            return new Moto();
        }
        throw new IllegalArgumentException("Tipo inválido");
    }
}

// Uso
Veiculo veiculo = FabricaVeiculo.criar("carro");
veiculo.dirigir();
```

### Observer

```java
interface Observer {
    void atualizar(String mensagem);
}

class Subject {
    private List<Observer> observers = new ArrayList<>();
    
    public void adicionar(Observer observer) {
        observers.add(observer);
    }
    
    public void remover(Observer observer) {
        observers.remove(observer);
    }
    
    public void notificar(String mensagem) {
        for (Observer observer : observers) {
            observer.atualizar(mensagem);
        }
    }
}

class Usuario implements Observer {
    private String nome;
    
    public Usuario(String nome) {
        this.nome = nome;
    }
    
    @Override
    public void atualizar(String mensagem) {
        System.out.println(nome + " recebeu: " + mensagem);
    }
}
```

---

## SOLID Principles

### S - Single Responsibility Principle

```java
// ❌ Ruim: Classe com múltiplas responsabilidades
public class Usuario {
    public void salvar() {
        // Lógica de persistência
    }
    
    public void enviarEmail() {
        // Lógica de email
    }
    
    public void gerarRelatorio() {
        // Lógica de relatório
    }
}

// ✅ Bom: Separar responsabilidades
public class Usuario {
    private String nome;
    private String email;
    // Apenas dados e lógica do usuário
}

public class RepositorioUsuario {
    public void salvar(Usuario usuario) {
        // Persistência
    }
}

public class EmailService {
    public void enviarEmail(String destinatario, String mensagem) {
        // Envio de email
    }
}

public class RelatorioService {
    public void gerar(List<Usuario> usuarios) {
        // Geração de relatório
    }
}
```

### O - Open/Closed Principle

```java
// ❌ Ruim: Violação do princípio Open/Closed
public class ProcessadorPagamento {
    public void processar(String tipo, double valor) {
        if ("cartao".equals(tipo)) {
            // Processar cartão
        } else if ("boleto".equals(tipo)) {
            // Processar boleto
        } else if ("pix".equals(tipo)) {
            // Processar Pix
        }
        // Adicionar novo tipo requer modificar a classe
    }
}

// ✅ Bom: Aberto para extensão, fechado para modificação
interface MetodoPagamento {
    void processar(double valor);
}

class PagamentoCartao implements MetodoPagamento {
    @Override
    public void processar(double valor) {
        // Lógica específica de cartão
    }
}

class PagamentoBoleto implements MetodoPagamento {
    @Override
    public void processar(double valor) {
        // Lógica específica de boleto
    }

class ProcessadorPagamento {
    private MetodoPagamento metodo;
    
    public ProcessadorPagamento(MetodoPagamento metodo) {
        this.metodo = metodo;
    }
    
    public void processar(double valor) {
        metodo.processar(valor);
    }
}
```

### L - Liskov Substitution Principle

```java
// ✅ Bom: Subclasses podem substituir a superclasse
abstract class Veiculo {
    abstract void acelerar();
    abstract void frear();
}

class Carro extends Veiculo {
    @Override
    void acelerar() {
        System.out.println("Carro acelerou");
    }
    
    @Override
    void frear() {
        System.out.println("Carro freou");
    }
}

public void dirigir(Veiculo v) {
    v.acelerar();
    v.frear();
    // Funciona para qualquer subclasse de Veiculo
}
```

### I - Interface Segregation Principle

```java
// ❌ Ruim: Interface gorda
interface Trabalhador {
    void trabalhar();
    void comer();
    void dormir();
}

// ✅ Bom: Interfaces especializadas
interface Trabalhavel {
    void trabalhar();
}

interface Comivel {
    void comer();
}

interface Dormivel {
    void dormir();
}

class Robo implements Trabalhavel {
    @Override
    public void trabalhar() {
        System.out.println("Robô trabalhando");
    }
}

class Pessoa implements Trabalhavel, Comivel, Dormivel {
    @Override
    public void trabalhar() {
        System.out.println("Pessoa trabalhando");
    }
    
    @Override
    public void comer() {
        System.out.println("Pessoa comendo");
    }
    
    @Override
    public void dormir() {
        System.out.println("Pessoa dormindo");
    }
}
```

### D - Dependency Inversion Principle

```java
// ❌ Ruim: Dependência direta de classes concretas
public class Servico {
    private RepositorioBancoDados repositorio;
    
    public Servico() {
        this.repositorio = new RepositorioBancoDados();
    }
}

// ✅ Bom: Depender de abstrações
public interface Repositorio {
    void salvar(Usuario usuario);
    Usuario buscar(String id);
}

public class RepositorioBancoDados implements Repositorio {
    @Override
    public void salvar(Usuario usuario) {
        // Implementação
    }
    
    @Override
    public Usuario buscar(String id) {
        // Implementação
    }
}

public class Servico {
    private Repositorio repositorio;
    
    public Servico(Repositorio repositorio) {
        this.repositorio = repositorio; // Injeção de dependência
    }
}

// Uso
Repositorio repositorio = new RepositorioBancoDados();
Servico servico = new Servico(repositorio);
```

---

## Java Moderno

### Optional

```java
// Evitar null pointer exceptions
Optional<String> valor = Optional.of("Java");

// Verificar presença
if (valor.isPresent()) {
    System.out.println(valor.get());
}

// Usar valor padrão
String resultado = valor.orElse("Padrão");

// Lançar exceção se vazio
String obrigatorio = valor.orElseThrow(() -> 
    new IllegalArgumentException("Valor obrigatório"));

// Mapear valores
Optional<Integer> tamanho = valor.map(String::length);

// Filtrar
Optional<String> comJ = valor.filter(s -> s.contains("J"));

// Flatmap para operações que retornam Optional
Optional<String> maiuscula = valor
    .flatMap(s -> Optional.of(s.toUpperCase()));
```

### LocalDateTime (Java 8+)

```java
import java.time.*;
import java.time.format.DateTimeFormatter;

// Criar instâncias
LocalDateTime agora = LocalDateTime.now();
LocalDate hoje = LocalDate.now();
LocalTime hora = LocalTime.now();

// Criar com valores específicos
LocalDateTime dataEspecifica = LocalDateTime.of(2026, 5, 7, 14, 30);

// Formatter
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
System.out.println(agora.format(formatter));

// Manipulação
LocalDateTime futuro = agora.plusDays(10);
LocalDateTime passado = agora.minusHours(2);

// Cálculos
Period periodo = Period.between(LocalDate.now(), LocalDate.of(2027, 1, 1));
System.out.println("Dias até 2027: " + periodo.getDays());
```

### Records (Java 14+)

```java
// Declaração concisa
public record Usuario(String nome, int idade, String email) {}

// Equivalente a (automaticamente gerado):
// - Campos privados final
// - Construtor completo
// - Getters
// - toString(), equals(), hashCode()

// Uso
Usuario usuario = new Usuario("João", 30, "joao@example.com");
System.out.println(usuario.nome());
System.out.println(usuario.idade());

// Records com métodos customizados
public record Ponto(int x, int y) {
    public double distancia() {
        return Math.sqrt(x * x + y * y);
    }
}
```

### Sealed Classes (Java 15+)

```java
// Controlar quem pode estender/implementar
sealed class Forma permits Circulo, Quadrado, Triangulo {
    abstract double area();
}

final class Circulo extends Forma {
    private double raio;
    
    @Override
    double area() {
        return Math.PI * raio * raio;
    }
}

final class Quadrado extends Forma {
    private double lado;
    
    @Override
    double area() {
        return lado * lado;
    }
}

sealed class Triangulo extends Forma permits TrianguloRetangulo {
    // ...
}

final class TrianguloRetangulo extends Triangulo {
    // ...
}
```

### Pattern Matching (Java 16+)

```java
// instanceof com pattern matching
Object objeto = "Java";

if (objeto instanceof String s) {
    System.out.println("Tamanho: " + s.length());
}

// Switch expressions com pattern matching
Object valor = 42;

String tipo = switch(valor) {
    case Integer i -> "Inteiro: " + i;
    case String s -> "String: " + s;
    case Double d -> "Double: " + d;
    default -> "Tipo desconhecido";
};
```

---

## Boas Práticas Avançadas

1. **Imutabilidade**: Prefer objetos imutáveis
2. **Streams**: Use Streams API para processamento funcional
3. **Null Safety**: Use Optional em vez de null
4. **Type Safety**: Aproveite Generics
5. **Concorrência**: Use ExecutorService em vez de criar threads manualmente
6. **Annotations**: Use para metaprogramação
7. **Reflection**: Use com cuidado (performance)
8. **SOLID**: Siga os princípios
9. **Design Patterns**: Aplique quando apropriado
10. **Testes**: Sempre escreva testes

---

## Recursos Adicionais

- [Java Language Features](https://docs.oracle.com/en/java/javase/17/docs/api/)
- [Effective Java](https://www.pearson.com/en-us/subject-catalog/p/effective-java/P200000000294)
- [Java Concurrency in Practice](https://jcip.net/)

---

*Última atualização: Maio 2026*
