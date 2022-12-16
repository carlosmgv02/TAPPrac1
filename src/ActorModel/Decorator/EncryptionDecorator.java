package ActorModel.Decorator;

import ActorModel.Actor;
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
     * @param m the message to add = the message to encrypt
     */
    @Override
    public void offer(Message m) {

        m.setText(encrypt(m.getText()));
        act.offer(m);

    }

    /**
     * Method to process the message = decrypting the message
     *
     * @return the processed message = decrypted message
     */
    @Override
    public Message process() throws InterruptedException {
        Message toProcess = act.process();
        String dec = decrypt(toProcess.getText());
        System.out.println("Decrypted: " + dec);

        return new Message(toProcess.getFrom(), dec);
    }

    /**
     * Method used to encrypt the message
     *
     * @param mensaje the message to encrypt
     * @return the encrypted message
     */
    @Override
    public String encrypt(String mensaje) {
        int clave = 7;
        mensaje = mensaje.toLowerCase();
        StringBuilder mensajeCifrado = new StringBuilder();

        for (int i = 0; i < mensaje.length(); i++) {
            if (mensaje.charAt(i) == ' ') {
                mensajeCifrado.append(' ');
                continue;
            }
            int posicion = alfabeto.indexOf(mensaje.charAt(i));
            int posicionCifrada = (posicion + clave) % 36;
            char reemplazarValor = alfabeto.charAt(posicionCifrada);
            mensajeCifrado.append(reemplazarValor);
        }
        return mensajeCifrado.toString();
    }

    /**
     * Method used to decrypt the message
     *
     * @param encrypyedText the message to decrypt
     * @return the decrypted message
     */
    @Override
    public String decrypt(String encrypyedText) {
        int key = 7;

        encrypyedText = encrypyedText.toLowerCase();
        StringBuilder originalMessage = new StringBuilder();
        for (int i = 0; i < encrypyedText.length(); i++) {
            if (encrypyedText.charAt(i) == ' ') {
                originalMessage.append(' ');
                continue;
            }
            int charPosition = alfabeto.indexOf(encrypyedText.charAt(i));
            int keyValue = (charPosition - key) % 26;
            if (keyValue < 0) {
                keyValue = alfabeto.length() + keyValue;
            }
            char replaceValue = alfabeto.charAt(keyValue);
            originalMessage.append(replaceValue);
        }
        return originalMessage.toString();
    }

    @Override
    public Queue<Message> getQueue() {
        return act.getQueue();
    }
}

