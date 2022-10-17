package mickimaus.Data;

import java.util.Queue;

public class RingActor implements Actor{

    @Override
    public void send(Message msg) {

    }

    @Override
    public Message process() {
        return null;
    }

    @Override
    public int getQueLength() {
        return 0;
    }

    @Override
    public Queue getQue() {
        return null;
    }
}
