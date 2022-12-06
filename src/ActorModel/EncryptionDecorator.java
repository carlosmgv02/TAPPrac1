package ActorModel;

import ActorModel.Messages.Message;

import java.util.Queue;

/**
 * Class used to encrypt messages sent to an actor.
 * <p>
 * It is a decorator that wraps an actor.
 * </p>
 */
public class EncryptionDecorator extends Actor implements ImplCifradoCesar {
    private final Actor act;


    public EncryptionDecorator(Actor actor) {
        this.act = actor;

    }

    /**
     * Method to add a message to the queue = encrypting the message
     *
     * @param msg the message to add = the message to encrypt
     */
    @Override
    public void offer(Message msg) {
        //ActorContext.lookupProxy(this).send());
        msg.setText(cifrar(msg.getText()));
        act.offer(msg);


    }

    /**
     * Method to process the message = decrypting the message
     *
     * @return the processed message = decrypted message
     */
    @Override
    public Message process() {
        Message toProcess = act.process();
        String dec = descifrar(toProcess.getText());
        System.out.println("Decrypted: " + dec);
        act.cua.poll();
        return new Message(toProcess.getFrom(), dec);
    }

    /**
     * Method Overritten to check the actor's instance queue
     */
    @Override
    public void run() {
        do {
            if (!act.getQueue().isEmpty())
                process();
        } while (true);
    }

    /**
     * Method used to encrypt the message
     *
     * @param mensaje the message to encrypt
     * @return the encrypted message
     */
    @Override
    public String cifrar(String mensaje) {
        int clave = 7;
        //lo ponemos en minusculas
        mensaje = mensaje.toLowerCase();
        //creamos un stringbuilder para ir añadiendo los caracteres
        StringBuilder mensajeCifrado = new StringBuilder();

        //recorremos el mensaje
        for (int i = 0; i < mensaje.length(); i++) {
            //obtenemos el caracter
            if (mensaje.charAt(i) == ' ') {
                //si es un espacio lo añadimos
                mensajeCifrado.append(' ');
                //si no es un espacio
                continue;
            }
            //obtenemos la posicion del caracter en el alfabeto
            int posicion = alfabeto.indexOf(mensaje.charAt(i));
            //obtenemos la posicion del caracter cifrado
            int posicionCifrada = (posicion + clave) % 26;
            //obtenemos el caracter cifrado
            char reemplazarValor = alfabeto.charAt(posicionCifrada);
            //añadimos el caracter cifrado al mensaje cifrado
            mensajeCifrado.append(reemplazarValor);
        }
        //devolvemos el mensaje cifrado
        return mensajeCifrado.toString();
    }

    /**
     * Method used to decrypt the message
     *
     * @param textoCifrado the message to decrypt
     * @return the decrypted message
     */
    @Override
    public String descifrar(String textoCifrado) {
        int clave = 7;

        //lo ponemos en minusculas
        textoCifrado = textoCifrado.toLowerCase();
        //creamos un stringbuilder para ir añadiendo los caracteres
        StringBuilder mensajeOriginal = new StringBuilder();
        //recorremos el mensaje
        for (int i = 0; i < textoCifrado.length(); i++) {
            //obtenemos el caracter
            if (textoCifrado.charAt(i) == ' ') {
                //si es un espacio lo añadimos
                mensajeOriginal.append(' ');
                //si no es un espacio
                continue;
            }
            //obtenemos la posicion del caracter en el alfabeto
            int posicionCaracter = alfabeto.indexOf(textoCifrado.charAt(i));
            //obtenemos la posicion del caracter cifrado
            int valorClave = (posicionCaracter - clave) % 26;
            //obtenemos el caracter cifrado
            if (valorClave < 0) {
                valorClave = alfabeto.length() + valorClave;
            }
            char sustituyeValor = alfabeto.charAt(valorClave);
            //añadimos el caracter cifrado al mensaje cifrado
            mensajeOriginal.append(sustituyeValor);
        }
        //devolvemos el mensaje original
        return mensajeOriginal.toString();
    }

    @Override
    public Queue<Message> getQueue() {
        return act.getQueue();
    }
}

