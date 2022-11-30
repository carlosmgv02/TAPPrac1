package ActorModel.Data;

import ActorModel.Data.Messages.Message;

import java.util.Queue;


public class EncryptionDecorator extends ActorDecorator implements ImplCifradoCesar {
    private Actor act;

    public EncryptionDecorator(Actor actor) {
        this.act = actor;
    }

    //A.K.A encrypt
    @Override
    public void offer(Message msg) {
        //ActorContext.lookupProxy(this).send());
        act.offer(new Message(msg.getFrom(), cifrar(msg.getText())));

//        ActorContext.lookupProxy(this).getActor().
//                offer(new Message(msg.getFrom(), cifrar(msg.getText())));
    }

    //A.K.A decrypt
    @Override
    public Message process() {
        Message toProcess = act.cua.element();
        String dec=descifrar(toProcess.getText());
        System.out.println("Decrypted: " + descifrar(toProcess.getText()));
        act.cua.poll();
        return new Message(toProcess.getFrom(), dec);
    }
    //Sobreescribimos el metodo run para que procesemos si la instancia de actor tiene algun mensaje en cola
    @Override
    public void run(){
        do{
            if(!act.cua.isEmpty())
                process();
        }while(!act.isInterrupted());
    }


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

    @Override
    public String descifrar(String textoCifrado) {
        int clave = 7;

        //lo ponemos en minusculas
        textoCifrado = textoCifrado.toLowerCase();
        //creamos un stringbuilder para ir añadiendo los caracteres
        String mensajeOriginal = "";
        //recorremos el mensaje
        for (int i = 0; i < textoCifrado.length(); i++) {
            //obtenemos el caracter
            if (textoCifrado.charAt(i) == ' ') {
                //si es un espacio lo añadimos
                mensajeOriginal += ' ';
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
            mensajeOriginal += sustituyeValor;
        }
        //devolvemos el mensaje original
        return mensajeOriginal;
    }

}

