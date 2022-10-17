package map2;

public class Count implements Imap<String> {

    public Integer apply(String elem) {
        return elem.length();
    }

}
