package ActorModel.Decorator;

/**
 * Interface used to define the methods that an EncryptionDecorator must implement
 */
public interface ImplCifradoCesar {
    /**
     * Predefined alphabet to be used in the encryption
     */
    String alfabeto = "0123456789abcdefghijklmnopqrstuvwxyz";

    /**
     * Method to encrypt the message
     *
     * @param mensaje the message to encrypt
     * @return the encrypted message
     */
    String encrypt(String mensaje);

    /**
     * Method to decrypt the message
     *
     * @param mensaje the message to decrypt
     * @return the decrypted message
     */
    String decrypt(String mensaje);
}
