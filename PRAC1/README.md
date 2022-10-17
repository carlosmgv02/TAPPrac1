El patrón de actores sirve para evitar problemas entre concurrencias.

La entidad es un actor.
Un actor es un thread infinito.

Actor -> Mailbox = cola de mensajes
         Comportamiento = estado iterno del actor

         Para que un actor se comunique con otro, debe enviarle un mensaje.
         El actor recibe el mensaje y lo procesa.

         Todas las peticiones que se le encolan al actor las lee en orden.
         Por eso no hay problemas de concurrencias.

         Para cada actor necesitaremos un hilo y una cola.

         p1= PlayerActor() //crea un actor
           p1 ! "hola" //envia un mensaje al actor
              p1 ! "adios"

         ActorProxy hello = ActorContext.spawnActor("actor1", new RingActor());
         hello.send(new Message(null, "hola"));

         Tendremos un diccionario de actores para poder buscarlos por nombre.

         // hasta aquí tenemos lo básico para crear actores
         // a partir de ahora vamos a ver como crear un actor que responda a lo que queremos

         Que ocurra si queremos que el actor responda y me dé un resultado?
         Podemos hacer que el proxy tenga su propia cola.

         Actor insult = ActorContext.spawnActor("actor1", new InsultActor());
         insult.send(new GetInsultMessage());
         Message result = insult.receive();
         System.out.println(result.getText());

         // ya lo podemos hacer

         Actor Decorator: Actor que envuelve a otro actor y le añade funcionalidad.

         //hay que crear un patrón factoría para que nuestros actores puedan funcionar con Java Threads y con virtualThreads


