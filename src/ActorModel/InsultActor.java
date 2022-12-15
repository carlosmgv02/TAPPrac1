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

    protected List<String> insultList = new ArrayList<>(Arrays.asList("tonto", "feo", "inútil", "gilipollas"));

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
                    /*System.out.println("GETINSULT: " + insultList.get(0));
                    System.out.println("\tFrom: " + this);
                    System.out.println("\tTo: " + msg.getFrom());*/
//                    System.out.println("GETINSULT *"+insultList.get(0)+"* *"+ActorContext.lookupProxy(this).getProxyId()+"*");
                    AuxProxy auxProxy = new AuxProxy(msg.getFrom());
                    Message m = new Message(msg.getFrom(), insultList.get(0));
                    auxProxy.send(m);
                    //msg.getFrom().offer(new Message(this,insultList.get(0)));
                    //ActorContext.lookupProxy(this).offer(new Message(this,insultList.get(0))); TODO
                    //System.out.println(insultList.get(0));
                    //cua.offer(new Message(m1.getFrom(), insultList.get(0)));
                    return new Message(msg.getFrom(), insultList.get(0));
                }
            }
            case GetAllInsultsMessage m3 -> {
                //Collections.shuffle(insultList);
                //TODO: cambiarlo para que no se envie el mensaje dos veces a la cola
                /*System.out.println("GETALLINSULTS: " + Collections.singletonList(insultList));
                System.out.println("\tFrom: " + this);
                //System.out.println("\tTo: "+msg.getFrom());
                System.out.println("\tTo: " + msg.getFrom());*/

                insultList.forEach(e -> {
                    AuxProxy auxProxy = new AuxProxy(msg.getFrom());
                    Message temp = new Message(msg.getFrom(), e);
                    //System.out.println("GETALLINSULTS SENT FROM INSULT ACTOR, TO: *"+auxProxy+"*");
                    auxProxy.send(temp);
                    //msg.getFrom().offer(temp);
                    //ActorContext.lookupProxy(this).offer(temp);
                    //System.out.println(temp);
                    /*
                    if (msgIsValid(temp))
                        cua.offer(temp);
                    */
                });
                return new Message(msg.getFrom(), Collections.singletonList(insultList).toString());
            }
            case AddInsultMessage m4 -> {
                String insult = msg.getText();
                /*System.out.println("ADDINSULT: " + insult);
                System.out.println("\tFrom: " + this);
                System.out.println("\tTo: " + this + ".insultList");*/
                if (!insultList.contains(msg.getText()))
                    insultList.add(insult);
                //System.out.println(Arrays.asList(insultList));
                //insultList.add(InsultGenerator.getRandomInsult());
                return new Message(msg.getFrom(), insult);
            }
            case null -> {
                return null;
            }
            case QuitMessage m4 -> {
                throw new InterruptedException();

            }
            default -> {
                System.out.println(msg);
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

    /*public void addInsult(String insult) {
        cua.offer(new AddInsultMessage(insult));
    }

    public Message getInsult() {
        Collections.shuffle(insultList);
        Message m = cua.poll();
        return new Message(m.getFrom(), insultList.get(0));
    }

    public Message getAllInsults() {
        cua.offer(new GetAllInsultsMessage());
        return null; //TO BE CHANGED
    }*/
}
