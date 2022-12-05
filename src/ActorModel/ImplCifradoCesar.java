package ActorModel;

/**
 * Interface used to define the methods that an EncryptionDecorator must implement
 */
public interface ImplCifradoCesar {
    /**
     * Predefined alphabet to be used in the encryption
     */
    String alfabeto = "abcdefghijklmnopqrstuvwxyz";

    /**
     * Method to encrypt the message
     * @param mensaje the message to encrypt
     * @return the encrypted message
     */
    String cifrar(String mensaje);

    /**
     * Method to decrypt the message
     * @param mensaje the message to decrypt
     * @return the decrypted message
     */
    String descifrar(String mensaje);
}
