package mickimaus.Data;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Actor {
    private Queue<Message> cua=new LinkedList<>();
    public void send(Message msg){
        cua.add(msg);
    }
    public void process(Message msg){

    }
}
