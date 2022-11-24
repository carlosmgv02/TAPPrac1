package ActorModel.Data;

//TODO PODEMOS CAMBIARLO A UNA INTERFACE Y QUE LA IMPLEMENTE EL ENCRYPTIONDECORATOR

public class CifradoCesar implements ImplCifradoCesar{

    public static final String alfabeto = "abcdefghijklmnopqrstuvwxyz";
    @Override
    public String cifrar(String mensaje, int clave){
        //lo ponemos en minusculas
        mensaje = mensaje.toLowerCase();
        //creamos un stringbuilder para ir añadiendo los caracteres
        String mensajeCifrado = "";

        //recorremos el mensaje
        for (int i=0; i<mensaje.length(); i++){
            //obtenemos el caracter
            if (mensaje.charAt(i) == ' '){
                //si es un espacio lo añadimos
                mensajeCifrado += ' ';
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
            mensajeCifrado += reemplazarValor;
        }
        //devolvemos el mensaje cifrado
        return mensajeCifrado;
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
