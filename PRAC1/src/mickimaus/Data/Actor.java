package mickimaus.Data;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public interface Actor {

    //Method 2 send a message to the actor
    void send(Message msg);

    //Method that processes the message and deletes it from the queue
     Message process();
     int getQueLength();

     Queue getQue();

}
