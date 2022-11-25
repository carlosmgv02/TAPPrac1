package ActorModel.Data;

import ActorModel.Data.Messages.Message;

import java.util.Queue;


public class EncryptionDecorator extends Actor implements ImplCifradoCesar {

    public EncryptionDecorator() {

    }
    @Override
    public void send(Message msg) {
        //msg.setText(cifrar(msg.getText(), 1));
        String txt=cifrar(msg.getText(), 1);
        //TODO ELIMINAR PRINT CUANDO HAYAMOS ACABADO DE HACER PRUEBAS, DESDE LAS CLASES NO SE IMPRIME
        System.out.println("El mensaje encriptado es: "+ txt);
        cua.offer(new Message(msg.getFrom(),txt));
    }

    @Override
    public Message process() {
        return null;
    }

    @Override
    public String cifrar(String mensaje, int clave){
        //lo ponemos en minusculas
        mensaje = mensaje.toLowerCase();
        //creamos un stringbuilder para ir añadiendo los caracteres
        StringBuilder mensajeCifrado = new StringBuilder();

        //recorremos el mensaje
        for (int i=0; i<mensaje.length(); i++){
            //obtenemos el caracter
            if (mensaje.charAt(i) == ' '){
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
    public String descifrar(String textoCifrado, int clave){
        //lo ponemos en minusculas
        textoCifrado = textoCifrado.toLowerCase();
        //creamos un stringbuilder para ir añadiendo los caracteres
        String mensajeOriginal = "";
        //recorremos el mensaje
        for (int i =0; i<textoCifrado.length(); i++){
            //obtenemos el caracter
            if(textoCifrado.charAt(i) == ' '){
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
            if(valorClave < 0){
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
