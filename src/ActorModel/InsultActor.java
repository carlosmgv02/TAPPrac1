package ActorModel;

import ActorModel.Messages.Insult.AddInsultMessage;
import ActorModel.Messages.Insult.GetAllInsultsMessage;
import ActorModel.Messages.Insult.GetInsultMessage;
import ActorModel.Messages.Message;
import ActorModel.Messages.QuitMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Class that represents an Insult Actor.
 * <p>
 * It has its own process method that processes the messages and inherits the rest of the methods from the Actor class.
 * </p>
 */
public class InsultActor extends Actor {

    protected final List<String> insultList = new ArrayList<>(Arrays.asList("tonto", "feo", "inútil", "gilipollas"));

    /**
     * Method that processes the message.
     * <p>
     * It processes the message via a pattern matching, depending on the type of message passed by parameter.
     * </p>
     * Available messages:
     * <ul>
     *     <li>Message</li>
     *     <li>GetInsultMessage</li>
     *     <li>GetAllInsultsMessage</li>
     *     <li>AddInsultMessage</li>
     * </ul>
     *
     * @return the processed message
     * @see Actor#process() Actor.process
     */
    @Override
    public synchronized Message process() throws InterruptedException {

        Message msg;
        synchronized (cua) {
            msg = cua.poll();
        }
        switch (msg) {
            case GetInsultMessage m1 -> {
                Collections.shuffle(insultList);
                if (msgIsValid(m1)) {
                    AuxProxy auxProxy = new AuxProxy(m1.getFrom());
                    Message m = new Message(m1.getFrom(), insultList.get(0));
                    auxProxy.send(m);
                    return new Message(m1.getFrom(), insultList.get(0));
                }
            }
            case GetAllInsultsMessage m3 -> {
                insultList.forEach(e -> {
                    AuxProxy auxProxy = new AuxProxy(m3.getFrom());
                    Message temp = new Message(m3.getFrom(), e);
                    auxProxy.send(temp);
                });
                return new Message(msg.getFrom(), Collections.singletonList(insultList).toString());
            }
            case AddInsultMessage m4 -> {
                String insult = m4.getText();
                if (!insultList.contains(m4.getText()))
                    insultList.add(insult);
                return new Message(m4.getFrom(), insult);
            }
            case null -> {
                return null;
            }
            case QuitMessage ignored -> throw new InterruptedException();
            default -> {
                return msg;
            }
        }
        return null;
    }

    /**
     * Method to verify if the message is already in the queue
     *
     * @param m the message to verify
     * @return true if the message is not in the queue, false otherwise
     */
    private boolean msgIsValid(Message m) {
        return !cua.contains(m);
    }

    /**
     * Method to get the insult list
     *
     * @return the insult list
     */
    public List<String> getInsultList() {
        return insultList;
    }
}
