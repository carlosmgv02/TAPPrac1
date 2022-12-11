# PRACTICA TAP
Practica 1 Técnicas Avanzadas de Programación. Consiste en implementar un sistema de actores en `Java`.<br>
## NOTES
### Monitor service:
Ha de tenir una llista d'actors als que està subscrit.
  * Interface observer (unic mètode update)
    * Implementar observer desde actorListener -> estat()
    * Implementar observer desde monitorService -> traffic
  * Interface observable: attach | dettach | notifyAll
    * Implementar observable desde Actor (attach | dettach | notifyAll)

### Dynamic proxy
Hem de fer una adaptació del proxy que utilitzi reflection.
Si li passem un objecte de tipus *getMessage*, haurem de cridar a aquest mètode per a facilitar l'execució del codi.
D'aquesta forma, no ho hem de mirar al process, si no que quan rebem un objecte, fem un binding amb el mètode.
* Com relacionar el invoke amb el intercept
* Com difefrenciar el reflectiveActor amb el dynProxy

### Consells Pedro
* Actor proxy no ha de ser un thread pero necessita tenir una cua per poder rebre missatges un cop ja n'ha enviat.
* Proxy ha de ser una interfície.
* Actor ha de ser una classe, no una interfície, y les classes han d'heretar d'ella.
* Nos bloqueamos cuando hacemos un receive para esperar la respuesta.
## Contenido
* [TODO](#todo)
* [Introducción](#introducción)
* [Enunciado](#enunciado)
    * [Description](#description)
    * [ActorProxy](#actorproxy)
    * [ActorDecorator](#actordecorator)
    * [Reflection and Dynamic Proxy](#reflection-and-dynamic-proxy)
    * [MonitorService and Observer pattern](#monitorservice-and-observer-pattern)
    * [Validation](#validation)
    * [Optional](#optional-parts)
* [Autores](#autores)
* [Apuntes](#apuntes)
## TODO
Tareas pendientes que faltan por hacer:
* [ ] Implementar el sistema de actores
  * [x] Patrón de singleton (Actor Context)
    * [x] Insult Actor
      * [x] GetInsultMessage
      * [x] GetAllInsultMessage
      * [x] AddInsultMessage
    * [x] HelloWorld Actor
  * [ ] Patrón de decorador
    * [ ] ActorDecorator
    * [x] Firewall decorator
    * [x] Encryption decorator
  * [ ] Patrón de proxy
    * [x] Actor Proxy
    * [ ] Dynamic Proxy
  * [ ] Patrón de observador
* [ ] Validar funcionamiento
  * [ ] Test unitarios
  * [ ] Crear JavaDoc
* [ ] Parte opcional threads & ring example
## Introducción
Implementar dicho sistema de actores utilizando patrones de diseño y threads que manejen colas de mensajes.<br>
Algunos de los patrones de diseños utilizados son:
* Singleton
* Decorator
* Proxy
* Reflection
* Observer
* Monitor
## Enunciado
### Description
<div style="text-align: justify">
The goal of this task is to implement a Java Actor System. An Actor is basically a software entity
that receives and send messages using Message Queues, and a single thread that reads and process
those messages sequentially. The sequential process of messages is extremely useful for
concurrency reasons, avoiding race conditions in the access to the state of the actor.
To create an actor we will use an ActorContext entity based on Singleton pattern. The API of the
Actor Context is: ActorContext.spawnActor(“name”,new ActorInstance()),
ActorContext.lookup(name), and ActorContext.getNames().<br>
Example:


```java
ActorProxy hello = ActorContext.spawnActor(“name”,new RingActor());
hello.send(new Message(null,”Hello World”);
```
ActorProxy only defines a method send(Message) that submits this message to the queue of the
Actor.
ActorContext is a registry of Actors indexed using a unique id. Spawning an actor will create the
required thread that will listen to messages for this actor.
An Actor has methods to send messages to its queue, and a process method that reacts to messages
in that queue. A QuitMessage forces the Actor to stop running. A Message includes a from field
which is an Actor reference (used to reply to that actor) and a text message (String).
To demonstrate the Actor system, create a HelloWorldActor that receives a message and writes it in
System.out. Now use several Actors that send messages to this Actor, to demonstrate that it can
process concurrent messages.
### ActorProxy
In order to communicate with an Actor in a OOP way, we will extend ActorProxy to also receive
messages from an Actor. The ActorProxy will have its own queue so that Actors can send back a
response to the requester Proxy.
Create an InsultActor accepting three messages: GetInsultMessage, AddInsultMessage,
GetAllInsultsMessage. The Actor has a list of insults, that can be extended using the
AddInsultMessage. GetInsultMessage will return a random insult in a Message to the requesting
Actor or Proxy entity.
Demonstrate that you can communicate with Actors using Proxies.<br>
Example:

```java
Actor insult = ActorContext.spawnActor(“name”,new InsultActor());
insult.send(new GetInsultMessage());
Message result = insult.receive();
System.out.println(result.getText());
```
### ActorDecorator
Using the Decorator pattern, implement an ActorDecorator enabling to modify message processing
to an actor. Create a FirewallDecorator and an EncryptionDecorator to demonstrate the pattern.
The FirewallDecorator will only let process messages whose sender is a valid Actor registered in
the ActorContext. It will stop all messages coming from a Proxy.
Create a LambdaFirewallDecorator that accepts closures to filter the messages than can be received
using an AddClosureMessage.
The EncryptionDecorator will encrypt (send) and decrypt message text (process) between
communicating Actors.
Demonstrate a pipeline of Decorators connecting EncryptionDecorator with Firewall Decorator.<br>
Example:

```java
ActorProxy hello = ActorContext.spawnActor(“name”,new FireWallDecorator(new
RingActor()));
```
### Reflection and Dynamic Proxy
Replace the ActorProxy using a DynamicProxy over an intercepted Java class. For example, an
InsultService class with methods addInsult, getAllInsults, getInsult.<br>
Example:
    
```java
Actor insult = ActorContext.spawnActor(“name”,new InsultActor());
InsultService insulter = DynamicProxy.intercept(insult);
insulter.addInsult(“stupid”);
System.out.println(insulter.getInsult());
```
OPTIONAL: Create a ReflectiveActor that given a Java Class, automatically maps the processing
of messages (process) to method invocation (i.e getInsultMessage to getInsult). This way, the
developer does not need to implement the process method, just provide a valid instance of a Java
class.<br>
Example:
    
```java
Actor insult = ActorContext.spawnActor(“name”,new ReflectiveActor(new InsultService()));
```
### MonitorService and Observer Pattern
The idea is to create a MonitorService that can obtain runtime information about the ActorSystem.
Using the Observer pattern, create an ActorListener enabling to receive information about four
Actor Events (creation, finalization, incorrect finalization, and received message). Incorrect
finalization means that the Actor stopped without receiving a QuitMessage (it halted abruptly due to
an error).<br><br>
The MonitorService can subscribe to some specific Actors (monitorActor(name)) or to all Actors in
the system (monitorAllActors()). The service will offer information about Actor message traffic
(LOW<5 messages, MEDIUM>5 <15, HIGH >15). A method (getTraffic) can be used to obtain a
Map where the key is the traffic (LOW, MEDIUM, HIG) and the value is the list of actor names.<br><br>
The Monitor Service also offers methods to getNumberofMessages for a givenActor, to log all
messages from one or more Actors, and to log all events.
The MonitorService also offers a getSentMessages method working over the message log, which
returns a Map where the key is the Actor, and the value is the messages sent by that actor. You can
also implement the analogous getReceivedMessages returning a Map where the key is the Actor,
and the value is the list of Messages sent by that Actor. You can use Java streams and Lambdas in
this task.<br><br>
The MonitorService also offers a getEvents method working over the event log, which returns a
Map where the key is the enum (CREATED, STOPPED, ERROR), and the value contains the
aforementioned events.





### Validation
To demonstrate that the Actor system is running correctly we propose two simple examples: Ring
and PingPong. Ring consists of creating a number of actors connected in a ring: every actor is
connected to the next one in the ring. When we send one message to an actor, this will resend the
message to the next actor in the ring. PingPong consists of two actors sending message to each
other. Create a mechanism to avoid infinite message processing.
Calculate the performance in number of messages that your Actor system can process.
Using System.currentTimeMillis, you can also calculate how much time it took to process 100
entire rounds in a ring of 100 nodes.
Use `JavaDocs` and `Unit tests` to validate the system.

### Optional parts
#### RemoteActor context and RemoteActorProxy
Use java.util.RMI to enable the remote communication with Actors. A RemoteActorContext will
create a RMI Server waiting for messages. A RemoteActorProxy will create a RMI client that
connects to the remote entity (RemoteActorContext) to send or receive messages.
#### Virtual Actors and performance
Using a Factory Design pattern, enable to use normal Java Threads or Virtual Threads (Java 19) in
the Actor system. Compare the performance of both systems using the Ring example.

## Autores
* Carlos Martínez - [carlosmgv02](https://github.com/carlosmgv02)
* Nil Monfort - [nilm9](https://github.com/nilm9)
* Genís Martínez - [genismartinez](https://github.com/genismartinez)
## Apuntes

El patrón de `actores` sirve para evitar problemas entre concurrencias.

La entidad es un actor.
Un actor es un thread infinito.

Actor -> `Mailbox` = cola de mensajes
         Comportamiento = estado iterno del actor

 Para que un actor se comunique con otro, debe enviarle un mensaje.
 El actor recibe el mensaje y lo procesa.

 Todas las peticiones que se le encolan al actor las lee en orden.
 Por eso no hay problemas de concurrencias.

 Para cada actor necesitaremos un `hilo/thread` y una `cola`.
```java
 p1= PlayerActor() //crea un actor
   p1 ! "hola" //envia un mensaje al actor
      p1 ! "adios"
```
```java
 ActorProxy hello = ActorContext.spawnActor("actor1", new RingActor());
 hello.send(new Message(null, "hola"));
```

 Tendremos un `diccionario/HashMap` de actores para poder buscarlos por nombre.

### Como crear un actor que responda a lo que queremos

 Que ocurra si queremos que el actor responda y me dé un resultado?<br>
 Podemos hacer que el proxy tenga su propia cola.
```java
 Actor insult = ActorContext.spawnActor("actor1", new InsultActor());
 insult.send(new GetInsultMessage());
 Message result = insult.receive();
 System.out.println(result.getText());
```

 

 [Actor Decorator](https://www.geeksforgeeks.org/decorator-design-pattern-in-java-with-example/): 
 Actor que envuelve a otro actor y le añade funcionalidad.

    //hay que crear un patrón factoría para que nuestros actores puedan funcionar con Java Threads y con virtualThreads

</div>


//pot ser que el proxy nomes envia i no te llista


//interficie
Actor (metodes declarats)

//classes implementen actor
-Cadascuna té llista de missatges i caracteristiues de processar
ringActor
helloWorldActor
...
//ActorProxy->
No te llista i nomes serveix per conectar els actors entre si i comunicarlos i donarels hi un nom







