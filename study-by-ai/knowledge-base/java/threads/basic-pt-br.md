# 🚦🧵 Sumário: Concorrência e Paralelismo em Java 🧵🚦

## 📑 Índice

1. [🧠 Conceitos Fundamentais](#-conceitos-fundamentais)
   - [🔄 Concorrência vs. Paralelismo](#-concorrencia-vs-paralelismo)
   - [⚠️ Condições de corrida, deadlocks, starvation e livelocks](#-condicoes-de-corrida-deadlocks-starvation-e-livelocks)
   - [🛡️ Imutabilidade e segurança de thread](#-imutabilidade-e-seguranca-de-thread)
2. [🏗️ APIs Clássicas](#-apis-classicas)
   - [🧵 Thread, Runnable, synchronized](#-thread-runnable-synchronized)
   - [⏳ wait(), notify(), notifyAll()](#-wait-notify-notifyall)
   - [🛠️ Gerenciamento manual de threads](#-gerenciamento-manual-de-threads)
3. [🚀 APIs Modernas](#-apis-modernas)
   - [🗂️ ExecutorService, ScheduledExecutorService, ThreadPoolExecutor](#-executorservice-scheduledexecutorservice-threadpoolexecutor)
   - [📦 Future, Callable](#-future-callable)
   - [🗃️ Coleções concorrentes](#-colecoes-concorrentes)
   - [🔒 Locks](#-locks)
   - [🧮 Variáveis atômicas](#-variaveis-atomicas)
   - [⏱️ CountDownLatch, CyclicBarrier, Semaphore, Phaser](#-countdownlatch-cyclicbarrier-semaphore-phaser)
4. [🌊 Java 8+ e Programação Funcional](#-java-8-e-programacao-funcional)
   - [🏞️ Streams paralelos](#-streams-paralelos)
   - [🤝 CompletableFuture e programação assíncrona](#-completablefuture-e-programacao-assincrona)
5. [🛠️ Práticas e Ferramentas](#-praticas-e-ferramentas)
6. [✅ Boas Práticas](#-boas-praticas)
7. [✨ Extras](#-extras)
8. [🏋️ Exercícios estilo Hackerrank](#-exercicios-estilo-hackerrank)
9. [⬅️ Voltar](#-voltar)

---

## 1. 🧠 Conceitos Fundamentais

> **Teoria & Propósito:**
> Esta seção apresenta as ideias fundamentais por trás de concorrência e paralelismo em Java. Compreender esses conceitos é crucial para projetar aplicações eficientes, responsivas e escaláveis, capazes de lidar com múltiplas tarefas ao mesmo tempo ou aproveitar múltiplos núcleos para melhor desempenho.

### 🔄 Concorrência vs. Paralelismo

> **Teoria & Propósito:**
> Concorrência trata de lidar com várias coisas ao mesmo tempo (estruturando o código para múltiplas tarefas), enquanto paralelismo trata de fazer várias coisas ao mesmo tempo (executando tarefas simultaneamente). Ambos são essenciais para maximizar o uso de recursos e a responsividade da aplicação.

#### Exemplo 1: Thread Simples (Concorrência)
```java
public class Exemplo1 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> System.out.println("Thread 1 executando"));
        Thread t2 = new Thread(() -> System.out.println("Thread 2 executando"));
        t1.start();
        t2.start();
    }
}
// Saída: Thread 1 executando\nThread 2 executando (ordem pode variar)
```
**🧑‍🍳 Analogia:** Imagine dois chefs (threads) trabalhando na mesma cozinha. Eles se revezam usando o fogão (CPU), então às vezes um está cozinhando enquanto o outro espera. Isso é concorrência: ambos estão progredindo, mas nem sempre ao mesmo tempo.

#### Exemplo 2: Paralelismo com parallelStream
```java
import java.util.stream.IntStream;
public class Exemplo2 {
    public static void main(String[] args) {
        IntStream.range(1, 5).parallel().forEach(System.out::println);
    }
}
// Saída: 1\n2\n3\n4 (ordem pode variar)
```
**🏭 Analogia:** Pense em uma fábrica com quatro trabalhadores (núcleos de CPU), cada um montando um produto ao mesmo tempo. Todas as tarefas acontecem simultaneamente—isso é paralelismo.

#### Exemplo 3: Runnable com Múltiplas Threads
```java
public class Exemplo3 {
    public static void main(String[] args) {
        Runnable tarefa = () -> System.out.println(Thread.currentThread().getName() + " está executando");
        for (int i = 0; i < 3; i++) {
            new Thread(tarefa).start();
        }
    }
}
// Saída: Thread-0 está executando\nThread-1 está executando\nThread-2 está executando (ordem pode variar)
```
**🎤 Analogia:** Imagine três cantores (threads), cada um com seu próprio microfone. Eles podem cantar suas partes independentemente, possivelmente ao mesmo tempo ou em rápida sucessão.

---

### ⚠️ Condições de corrida, deadlocks, starvation e livelocks

> **Teoria & Propósito:**
> Esses são problemas comuns na programação concorrente. Compreendê-los ajuda os desenvolvedores a escrever código mais seguro e confiável, evitando bugs que são difíceis de detectar e reproduzir.

- **Condição de Corrida:** Quando duas ou mais threads acessam dados compartilhados e tentam alterá-los ao mesmo tempo.
- **Deadlock:** Duas ou mais threads estão bloqueadas para sempre, cada uma esperando pela outra.
- **Starvation:** Uma thread é perpetuamente negada o acesso aos recursos.
- **Livelock:** As threads não estão bloqueadas, mas continuam mudando de estado em resposta uma à outra sem fazer progresso.

#### Exemplo 1: Condição de Corrida
```java
class Contador {
    int count = 0;
    void increment() { count++; }
}
public class CondicaoCorrida {
    public static void main(String[] args) throws InterruptedException {
        Contador c = new Contador();
        Thread t1 = new Thread(() -> { for(int i=0;i<1000;i++) c.increment(); });
        Thread t2 = new Thread(() -> { for(int i=0;i<1000;i++) c.increment(); });
        t1.start(); t2.start(); t1.join(); t2.join();
        System.out.println(c.count); // Saída pode ser < 2000
    }
}
```
**🏦 Analogia:** Imagine dois caixas (threads) atualizando o saldo da mesma conta bancária ao mesmo tempo, sem coordenação. Às vezes, uma atualização sobrescreve a outra, então o saldo final está errado. Isso é uma condição de corrida.

#### Exemplo 2: Deadlock
```java
public class DemoDeadlock {
    static final Object Lock1 = new Object();
    static final Object Lock2 = new Object();
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (Lock1) {
                try { Thread.sleep(50); } catch (InterruptedException e) {}
                synchronized (Lock2) {
                    System.out.println("Thread 1 adquiriu ambos os locks");
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (Lock2) {
                try { Thread.sleep(50); } catch (InterruptedException e) {}
                synchronized (Lock1) {
                    System.out.println("Thread 2 adquiriu ambos os locks");
                }
            }
        });
        t1.start(); t2.start();
    }
}
// Saída: (Pode causar deadlock e não imprimir nada)
```
**🔒 Analogia:** Duas pessoas (threads) estão cada uma segurando uma chave para uma porta (recurso) que a outra precisa para passar. Elas não podem prosseguir até que ambas tenham a chave da outra, o que nunca acontecerá—isso é um deadlock.

#### Exemplo 3: Starvation
```java
public class DemoStarvation {
    public static void main(String[] args) {
        final Object lock = new Object();
        Thread lowPriority = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread de baixa prioridade executando");
            }
        });
        Thread highPriority = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    // Continua adquirindo o lock
                }
            }
        });
        highPriority.setPriority(Thread.MAX_PRIORITY);
        lowPriority.setPriority(Thread.MIN_PRIORITY);
        highPriority.start();
        lowPriority.start();
    }
}
// Saída: A thread de baixa prioridade pode nunca ser executada
```
**🚫 Analogia:** Imagine um banheiro (recurso) com duas cabines: uma sempre tem uma fila (thread de alta prioridade), e a outra está vazia (thread de baixa prioridade). A pessoa na cabina vazia pode nunca ter a chance de usar o banheiro porque a outra cabina está sempre ocupada.

---

### 🛡️ Imutabilidade e segurança de thread

> **Teoria & Propósito:**
> Imutabilidade e segurança de thread são estratégias chave para prevenir problemas de concorrência. Imutabilidade garante que objetos não possam ser alterados após a criação, enquanto segurança de thread garante o comportamento correto quando múltiplas threads acessam dados compartilhados.

- **Imutabilidade:** Objetos que não podem mudar de estado após a criação são naturalmente seguros para thread.
- **Segurança de thread:** Alcançada através de sincronização, coleções concorrentes ou variáveis atômicas.

#### Exemplo 1: Classe Imutável
```java
final class Pessoa {
    private final String nome;
    public Pessoa(String nome) { this.nome = nome; }
    public String getNome() { return nome; }
}
// Uso: Pessoa p = new Pessoa("Alice"); System.out.println(p.getNome());
// Saída: Alice
```
**🛡️ Analogia:** Um objeto imutável é como uma estátua: uma vez esculpida (criada), não pode ser mudada. Você pode olhar para ela ou tirar uma foto, mas não pode alterar sua forma.

#### Exemplo 2: Método Sincronizado
```java
class ContadorSeguro {
    private int count = 0;
    public synchronized void increment() { count++; }
    public int getCount() { return count; }
}
// Uso: ContadorSeguro c = new ContadorSeguro(); c.increment();
// Saída: 1
```
**🔒 Analogia:** Um método sincronizado é como um banheiro com uma fechadura: apenas uma pessoa (thread) pode usá-lo por vez. Se outra pessoa tentar entrar, terá que esperar do lado de fora até que a primeira pessoa termine.

#### Exemplo 3: Usando AtomicInteger
```java
import java.util.concurrent.atomic.AtomicInteger;
public class ExemploAtomic {
    public static void main(String[] args) {
        AtomicInteger ai = new AtomicInteger(0);
        ai.incrementAndGet();
        System.out.println(ai.get());
    }
}
// Saída: 1
```
**⚛️ Analogia:** Usar `AtomicInteger` é como ter um cofre de porquinho seguro para thread: você pode adicionar ou remover moedas (incrementar ou decrementar) sem se preocupar com alguém roubando uma moeda enquanto você não está olhando.

---

## 2. 🏗️ APIs Clássicas

> **Teoria & Propósito:**
> As APIs clássicas de concorrência em Java fornecem os blocos de construção básicos para programação multithread, como criar threads, sincronizar o acesso e coordenar entre threads.

### 🧵 Thread, Runnable, synchronized

#### Exemplo 1: Estendendo Thread
```java
class MinhaThread extends Thread {
    public void run() {
        System.out.println("MinhaThread executando");
    }
}
// Uso: new MinhaThread().start();
// Saída: MinhaThread executando
```
**🧵 Analogia:** Estender a classe Thread é como criar um interruptor de luz personalizado: você pode ligá-lo (iniciar a thread) e ele fará seu trabalho predefinido (executar o código no método `run()`).

#### Exemplo 2: Implementando Runnable
```java
class MinhaRunnable implements Runnable {
    public void run() {
        System.out.println("MinhaRunnable executando");
    }
}
// Uso: new Thread(new MinhaRunnable()).start();
// Saída: MinhaRunnable executando
```
**🔌 Analogia:** Implementar Runnable é como fornecer um dispositivo plug-in: você fornece a funcionalidade (no método `run()`), e pode ser usado em qualquer lugar (em qualquer thread) apenas sendo conectado.

#### Exemplo 3: Bloco Sincronizado
```java
class BlocoSync {
    private int value = 0;
    public void increment() {
        synchronized(this) {
            value++;
        }
    }
}
// Uso: BlocoSync sb = new BlocoSync(); sb.increment();
// Saída: 1
```
**🔒 Analogia:** Um bloco sincronizado é como um cofre seguro dentro de um banco: apenas uma pessoa (thread) pode acessar os valores (código dentro do bloco) por vez, garantindo segurança contra roubo (condições de corrida).

---

### ⏳ wait(), notify(), notifyAll()

#### Exemplo 1: wait/notify
```java
class DemoWaitNotify {
    private static final Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("Thread esperando");
                    lock.wait();
                    System.out.println("Thread retomada");
                } catch (InterruptedException e) {}
            }
        });
        t1.start();
        Thread.sleep(100);
        synchronized (lock) {
            lock.notify();
        }
    }
}
// Saída: Thread esperando\nThread retomada
```
**⏳ Analogia:** Usar wait/notify é como um aluno (thread) esperando o professor (notify) para continuar a lição. O aluno levanta a mão (espera) e o professor reconhece (notifica) quando é hora de continuar.

#### Exemplo 2: notifyAll
```java
class DemoNotifyAll {
    private static final Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            synchronized (lock) {
                try {
                    lock.wait();
                    System.out.println(Thread.currentThread().getName() + " retomada");
                } catch (InterruptedException e) {}
            }
        };
        new Thread(r).start();
        new Thread(r).start();
        Thread.sleep(100);
        synchronized (lock) {
            lock.notifyAll();
        }
    }
}
// Saída: Thread-0 retomada\nThread-1 retomada (ordem pode variar)
```
**📢 Analogia:** `notifyAll` é como um professor anunciando que a aula acabou. Todos os alunos (threads) são notificados de uma vez e podem sair da sala (retomar a execução).

#### Exemplo 3: Produtor-Consumidor
```java
import java.util.LinkedList;
class ProdutorConsumidor {
    public static void main(String[] args) {
        LinkedList<Integer> buffer = new LinkedList<>();
        Object lock = new Object();
        Thread produtor = new Thread(() -> {
            synchronized (lock) {
                buffer.add(1);
                lock.notify();
            }
        });
        Thread consumidor = new Thread(() -> {
            synchronized (lock) {
                while (buffer.isEmpty()) {
                    try { lock.wait(); } catch (InterruptedException e) {}
                }
                System.out.println("Consumido: " + buffer.remove());
            }
        });
        consumidor.start();
        produtor.start();
    }
}
// Saída: Consumido: 1
```
**🍽️ Analogia:** O problema produtor-consumidor é como uma cozinha de restaurante (produtor) e área de jantar (consumidor). A cozinha prepara a comida e notifica a área de jantar quando está pronta. A área de jantar espera pela notificação para pegar a comida.

---

### 🛠️ Gerenciamento manual de threads

#### Exemplo 1: Criando e juntando threads
```java
public class ExemploJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> System.out.println("Thread executando"));
        t.start();
        t.join();
        System.out.println("Principal finalizado");
    }
}
// Saída: Thread executando\nPrincipal finalizado
```
**🔗 Analogia:** Juntar uma thread é como esperar um amigo terminar sua ligação antes de você começar sua conversa. Você não quer interromper, então espera pacientemente (junta) até que ele termine.

#### Exemplo 2: Interrompendo uma thread
```java
public class ExemploInterromper {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                // executando
            }
            System.out.println("Thread interrompida");
        });
        t.start();
        Thread.sleep(100);
        t.interrupt();
    }
}
// Saída: Thread interrompida
```
**🚫 Analogia:** Interromper uma thread é como tocar alguém no ombro para chamar sua atenção. A pessoa tocada (thread) vai parar o que está fazendo e responder ao toque (interrupção).

#### Exemplo 3: Thread daemon
```java
public class ExemploDaemon {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            while (true) {}
        });
        t.setDaemon(true);
        t.start();
        System.out.println("Principal finalizado");
    }
}
// Saída: Principal finalizado
```
**👻 Analogia:** Uma thread daemon é como um fantasma que só aparece quando há alguém vivo (thread de usuário) por perto. Assim que a última pessoa sai, o fantasma desaparece.

---

## 3. 🚀 APIs Modernas

> **Teoria & Propósito:**
> As APIs modernas de concorrência em Java oferecem abstrações de nível mais alto para gerenciar threads, tarefas e sincronização, tornando a programação concorrente mais segura e produtiva.

### 🗂️ ExecutorService, ScheduledExecutorService, ThreadPoolExecutor

#### Exemplo 1: ExecutorService
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class DemoExecutor {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> System.out.println("Tarefa 1"));
        executor.submit(() -> System.out.println("Tarefa 2"));
        executor.shutdown();
    }
}
// Saída: Tarefa 1\nTarefa 2 (ordem pode variar)
```
**🛠️ Analogia:** Usar ExecutorService é como contratar um serviço de táxi. Você solicita uma corrida (submete uma tarefa), e a empresa de táxi (ExecutorService) envia um carro (thread) para te buscar. Você não precisa saber quantos carros eles têm ou como eles os gerenciam.

#### Exemplo 2: ScheduledExecutorService
```java
import java.util.concurrent.*;
public class DemoAgendador {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> System.out.println("Tarefa agendada"), 1, TimeUnit.SECONDS);
        scheduler.shutdown();
    }
}
// Saída: Tarefa agendada (após 1 segundo)
```
**⏰ Analogia:** ScheduledExecutorService é como ajustar um despertador. Você define a hora (agendamento), e quando chega a hora, o alarme toca (tarefa é executada).

#### Exemplo 3: ThreadPoolExecutor
```java
import java.util.concurrent.*;
public class DemoPoolThread {
    public static void main(String[] args) {
        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        pool.execute(() -> System.out.println("Tarefa ThreadPool"));
        pool.shutdown();
    }
}
// Saída: Tarefa ThreadPool
```
**🏭 Analogia:** ThreadPoolExecutor é como uma linha de montagem de fábrica. Você tem um número fixo de trabalhadores (threads) e envia tarefas (produtos) pela linha. Cada trabalhador pega uma tarefa, processa e depois pega outra.

---

### 📦 Future, Callable

#### Exemplo 1: Callable com Future
```java
import java.util.concurrent.*;
public class DemoCallable {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(() -> 42);
        System.out.println(future.get());
        executor.shutdown();
    }
}
// Saída: 42
```
**🔮 Analogia:** Usar Callable com Future é como fazer uma aposta: você entrega seu dinheiro (tarefa) para o bookmaker (executor), e eles prometem te pagar com os ganhos (resultado) depois.

#### Exemplo 2: Future isDone
```java
import java.util.concurrent.*;
public class DemoFutureIsDone {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(() -> "Feito");
        while (!future.isDone()) {}
        System.out.println(future.get());
        executor.shutdown();
    }
}
// Saída: Feito
```
**⏳ Analogia:** Verificar se um Future está concluído é como esperar uma panela de água ferver. Você não fica olhando dentro o tempo todo; apenas espera até ouvir o apito (notificação de conclusão).

#### Exemplo 3: Cancelando um Future
```java
import java.util.concurrent.*;
public class DemoCancelarFuture {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> future = executor.submit(() -> {
            while (true) {}
        });
        Thread.sleep(100);
        future.cancel(true);
        System.out.println(future.isCancelled());
        executor.shutdown();
    }
}
// Saída: true
```
**🚫 Analogia:** Cancelar um Future é como cancelar um pedido de pizza. Você liga para o restaurante e pede para pararem de fazer sua pizza (tarefa). Se eles ainda não começaram, nada acontece. Se já começaram, eles jogam a pizza (tarefa) fora e param de trabalhar nela.

---

### 🗃️ Coleções concorrentes

#### Exemplo 1: ConcurrentHashMap
```java
import java.util.concurrent.*;
public class DemoMapaConcorrente {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("A", 1);
        System.out.println(map.get("A"));
    }
}
// Saída: 1
```
**🗺️ Analogia:** Um ConcurrentHashMap é como um estacionamento de vários andares. Carros (dados) podem entrar e sair simultaneamente em diferentes andares (segmentos) sem interferir uns com os outros.

#### Exemplo 2: CopyOnWriteArrayList
```java
import java.util.concurrent.CopyOnWriteArrayList;
public class DemoCopyOnWrite {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("Olá");
        System.out.println(list.get(0));
    }
}
// Saída: Olá
```
**📚 Analogia:** Uma CopyOnWriteArrayList é como um livro de biblioteca: quando você o pega emprestado (lê), a biblioteca faz uma cópia para você. Você pode lê-lo o quanto quiser, e o livro original permanece inalterado.

#### Exemplo 3: ConcurrentLinkedQueue
```java
import java.util.concurrent.ConcurrentLinkedQueue;
public class DemoFilaConcorrente {
    public static void main(String[] args) {
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();
        queue.add(10);
        System.out.println(queue.poll());
    }
}
// Saída: 10
```
**Fila Analogia:** Uma ConcurrentLinkedQueue é como uma fila de ingressos em um show. As pessoas (dados) entram na fila (fila) e saem pela frente quando chega a vez delas, tudo sem confusão ou engano.

---

### 🔒 Locks

#### Exemplo 1: ReentrantLock
```java
import java.util.concurrent.locks.ReentrantLock;
public class DemoLock {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println("Seção bloqueada");
        } finally {
            lock.unlock();
        }
    }
}
// Saída: Seção bloqueada
```
**🔑 Analogia:** Um ReentrantLock é como uma chave de quarto de hotel: você pode trancar (proteger) o quarto (seção de código) quando estiver dentro, e destrancar (sair) quando terminar. Se você sair e voltar, pode usar a mesma chave para entrar novamente.

#### Exemplo 2: ReadWriteLock
```java
import java.util.concurrent.locks.*;
public class DemoReadWriteLock {
    public static void main(String[] args) {
        ReadWriteLock rwLock = new ReentrantReadWriteLock();
        rwLock.readLock().lock();
        System.out.println("Lock de leitura adquirido");
        rwLock.readLock().unlock();
    }
}
// Saída: Lock de leitura adquirido
```
**📖 Analogia:** Um ReadWriteLock é como uma biblioteca pública: muitas pessoas podem ler livros (dados) ao mesmo tempo, mas quando alguém quer escrever ou atualizar um livro, ela recebe acesso exclusivo até terminar.

#### Exemplo 3: Condition
```java
import java.util.concurrent.locks.*;
public class DemoCondition {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        lock.lock();
        try {
            condition.signalAll();
            System.out.println("Condição sinalizada");
        } finally {
            lock.unlock();
        }
    }
}
// Saída: Condição sinalizada
```
**📢 Analogia:** Usar uma Condition é como um professor sinalizando todos os alunos para começarem a trabalhar em sua tarefa. Todos os alunos (threads) respondem ao sinal e começam sua tarefa.

---

### 🧮 Variáveis atômicas

#### Exemplo 1: AtomicInteger
```java
import java.util.concurrent.atomic.AtomicInteger;
public class DemoIntAtomico {
    public static void main(String[] args) {
        AtomicInteger ai = new AtomicInteger(0);
        ai.incrementAndGet();
        System.out.println(ai.get());
    }
}
// Saída: 1
```
**⚛️ Analogia:** Um AtomicInteger é como um contador seguro em uma bilheteira. Não importa quantas pessoas estão comprando ingressos ao mesmo tempo, o contador sempre mostra o número correto de ingressos vendidos.

#### Exemplo 2: AtomicReference
```java
import java.util.concurrent.atomic.AtomicReference;
public class DemoRefAtomica {
    public static void main(String[] args) {
        AtomicReference<String> ref = new AtomicReference<>("A");
        ref.set("B");
        System.out.println(ref.get());
    }
}
// Saída: B
```
**🔗 Analogia:** Uma AtomicReference é como um interruptor de luz que pode ser ligado ou desligado (referenciado) por várias pessoas ao mesmo tempo. Todos podem ver o estado atual do interruptor, e mudar o estado é instantâneo e seguro para thread.

#### Exemplo 3: AtomicBoolean
```java
import java.util.concurrent.atomic.AtomicBoolean;
public class DemoBoolAtomico {
    public static void main(String[] args) {
        AtomicBoolean ab = new AtomicBoolean(false);
        ab.set(true);
        System.out.println(ab.get());
    }
}
// Saída: true
```
**✅ Analogia:** Um AtomicBoolean é como um item de lista de verificação compartilhado que várias pessoas podem marcar ou desmarcar. Não importa quantas pessoas estão acessando a lista ao mesmo tempo, cada ação de marcar ou desmarcar é registrada imediatamente e com segurança.

---

### ⏱️ CountDownLatch, CyclicBarrier, Semaphore, Phaser

#### Exemplo 1: CountDownLatch
```java
import java.util.concurrent.CountDownLatch;
public class DemoLatch {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            System.out.println("Thread executando");
            latch.countDown();
        }).start();
        latch.await();
        System.out.println("Principal continua");
    }
}
// Saída: Thread executando\nPrincipal continua
```
**🔔 Analogia:** Um CountDownLatch é como uma pistola de largada em uma corrida. Os corredores (threads) esperam pelo sinal (contagem regressiva do latch) para começar a correr. Assim que o sinal é dado, todos os corredores começam simultaneamente.

#### Exemplo 2: CyclicBarrier
```java
import java.util.concurrent.CyclicBarrier;
public class DemoBarrier {
    public static void main(String[] args) throws Exception {
        CyclicBarrier barrier = new CyclicBarrier(2, () -> System.out.println("Barreira atingida!"));
        new Thread(() -> {
            try { barrier.await(); } catch (Exception e) {}
        }).start();
        barrier.await();
    }
}
// Saída: Barreira atingida!
```
**🚧 Analogia:** Uma CyclicBarrier é como uma equipe de construção que não pode prosseguir até que todos os membros estejam presentes. Assim que todos chegam, eles recebem a autorização para começar a trabalhar.

#### Exemplo 3: Semaphore
```java
import java.util.concurrent.Semaphore;
public class DemoSemaphore {
    public static void main(String[] args) throws InterruptedException {
        Semaphore sem = new Semaphore(1);
        sem.acquire();
        System.out.println("Semáforo adquirido");
        sem.release();
    }
}
// Saída: Semáforo adquirido
```
**🚦 Analogia:** Um Semaphore é como um semáforo para threads. Ele controla o acesso (luz verde) a um determinado recurso, permitindo que um certo número de threads o use simultaneamente.

---

## 4. 🌊 Java 8+ e Programação Funcional

> **Teoria & Propósito:**
> Java 8 introduziu recursos de programação funcional e novas ferramentas de concorrência, permitindo um código mais expressivo, conciso e paralelizado para aplicações modernas.

### 🏞️ Streams paralelos

#### Exemplo 1: Processamento paralelo
```java
import java.util.*;
public class DemoStreamParalelo {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4);
        list.parallelStream().forEach(System.out::println);
    }
}
// Saída: 1\n2\n3\n4 (ordem pode variar)
```
**🏭 Analogia:** Usar streams paralelos é como ter várias linhas de montagem em uma fábrica. Cada linha trabalha em uma parte do produto simultaneamente, acelerando todo o processo de produção.

#### Exemplo 2: Coletando resultados
```java
import java.util.*;
import java.util.stream.Collectors;
public class DemoColetarParalelo {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4);
        List<Integer> result = list.parallelStream().map(x -> x * 2).collect(Collectors.toList());
        System.out.println(result);
    }
}
// Saída: [2, 4, 6, 8]
```
**📦 Analogia:** Coletar resultados de um stream paralelo é como reunir todos os produtos acabados de diferentes linhas de montagem em um único armazém. Cada produto está completo e pronto para entrega.

#### Exemplo 3: Soma paralela
```java
import java.util.*;
public class DemoSomaParalela {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4);
        int sum = list.parallelStream().mapToInt(Integer::intValue).sum();
        System.out.println(sum);
    }
}
// Saída: 10
```
**➕ Analogia:** Calcular uma soma usando streams paralelos é como ter vários caixas em um checkout de supermercado. Cada caixa processa alguns clientes (pedaços de dados) simultaneamente, tornando o processo de checkout mais rápido.

---

### 🤝 CompletableFuture e async programming

#### Exemplo 1: CompletableFuture Simples
```java
import java.util.concurrent.*;
public class DemoCompletableFuture {
    public static void main(String[] args) throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Olá");
        System.out.println(future.get());
    }
}
// Saída: Olá
```
**🔮 Analogia:** Um CompletableFuture é como uma caixa mágica: você coloca um pedido dentro (supplyAsync), e ela promete entregar o resultado depois. Você pode continuar com outras tarefas e verificar a caixa (get) quando estiver pronto para o resultado.

#### Exemplo 2: Encadeando CompletableFutures
```java
import java.util.concurrent.*;
public class DemoEncadearFutures {
    public static void main(String[] args) throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 2)
            .thenApply(x -> x * 3);
        System.out.println(future.get());
    }
}
// Saída: 6
```
**🔗 Analogia:** Encadear CompletableFutures é como uma corrida de revezamento: um corredor (tarefa) passa o bastão (resultado) para o próximo corredor na fila. Cada corredor sabe exatamente o que fazer com o bastão.

#### Exemplo 3: Combinando CompletableFutures
```java
import java.util.concurrent.*;
public class DemoCombinarFutures {
    public static void main(String[] args) throws Exception {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> 2);
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> 3);
        CompletableFuture<Integer> combined = f1.thenCombine(f2, Integer::sum);
        System.out.println(combined.get());
    }
}
// Saída: 5
```
**➕ Analogia:** Combinar CompletableFutures é como unir dois rios: cada rio (future) flui independentemente, mas quando se encontram, criam um fluxo mais forte e combinado (resultado).

---

## 5. 🛠️ Práticas e Ferramentas

> **Teoria & Propósito:**
> Esta seção cobre técnicas e ferramentas práticas para identificar, analisar e testar problemas de concorrência em aplicações Java do mundo real.

### 🕵️ Identificando e resolvendo problemas de concorrência
- Use dumps de thread, logging e ferramentas de análise para identificar deadlocks, condições de corrida e gargalos de desempenho.

### 🧰 Ferramentas de análise (VisualVM, JConsole, Dumps de thread)
- VisualVM e JConsole são ferramentas poderosas para monitorar e analisar aplicações Java em tempo real.

### 🧪 Testes de concorrência
- Use frameworks como JUnit e ferramentas como jcstress para testes de concorrência.

---

## 6. ✅ Boas Práticas

> **Teoria & Propósito:**
> Boas práticas ajudam os desenvolvedores a evitar armadilhas comuns e escrever código concorrente robusto e mantenível.

---

## 7. ✨ Extras

> **Teoria & Propósito:**
> Tópicos e frameworks avançados que estendem as capacidades de concorrência do Java, incluindo detalhes do modelo de memória e integração com frameworks populares.

---

## 🏋️ Exercícios estilo Hackerrank

1. Implemente um contador seguro para threads em Java.
2. Crie um exemplo de deadlock e explique como evitá-lo.
3. Escreva um programa que demonstre starvation.
4. Implemente uma classe de ponto 2D imutável.
5. Demonstre o uso de parallelStream para processar uma lista.
6. Crie um exemplo de condição de corrida e conserte-o.
7. Implemente um livelock simples.
8. Explique a diferença entre concorrência e paralelismo com código.
9. Use `synchronized` para proteger um recurso compartilhado.
10. Mostre como usar `AtomicInteger` para segurança de thread.

---

**Novos Exercícios:**

11. Simule um restaurante onde vários garçons (threads) atendem clientes, mas apenas um pode acessar a cozinha por vez.
12. Escreva um programa onde duas threads imprimem números em ordem (uma imprime pares, a outra ímpares).
13. Implemente um cenário produtor-consumidor usando wait/notify.
14. Crie um pool de threads que processa uma lista de tarefas e imprime o resultado de cada uma.
15. Use um `CountDownLatch` para fazer a thread principal esperar que três threads trabalhadoras terminem antes de imprimir "Tudo pronto!".

<details>
<summary>Respostas (clique para expandir)</summary>

<!--
1. Use AtomicInteger ou synchronized.
2. Two crossed locks, avoid by ordering lock acquisition.
3. Low-priority thread never runs.
4. Class with final fields and no setters.
5. list.parallelStream().forEach(...)
6. Shared variable without synchronization, fix with synchronized.
7. Two threads yielding resources to each other repeatedly.
8. Concurrency: interleaving, Parallelism: simultaneous execution.
9. synchronized(method or block).
10. AtomicInteger.incrementAndGet().
11. Use synchronized block for kitchen access.
12. Use wait/notify and a shared counter.
13. Shared buffer, producer adds, consumer removes, both use wait/notify.
14. Use ExecutorService and submit tasks.
15. Create CountDownLatch(3), each worker calls countDown(), main calls await().
-->

</details>

---

## ⬅️ [Voltar](../README.md)

---



