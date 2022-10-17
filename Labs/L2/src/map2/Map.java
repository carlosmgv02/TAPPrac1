package map2;


import java.util.LinkedList;
import java.util.List;

public class Map {

    public static <R,T> List<R> map(List<T> list, Imap<T> function) {
        List<R> result = new LinkedList<R>();
        for (T elem : list)
            result.add(function.apply(elem));
        return result;
    }

}
