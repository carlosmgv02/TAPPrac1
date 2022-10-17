package File;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
public class FileUtil {

    public static void main(String[] args) {
        System.out.println( countWords("src/topgs.txt"));
    }

    //method to count all words in a text file
  public static Map<String,Integer> countWords(String filename) {
      int count = 0;
      Map<String,Integer> mapa=new HashMap<>();

      try{
      Scanner in = new Scanner(new File(filename));
          while (in.hasNext()) {
              String txt=in.next();
              System.out.println(txt);
              if(mapa.containsKey(txt)){
                  mapa.put(txt, mapa.get(txt)+1);
              }
              else{
                  mapa.put(txt, 1);
              }
          }
      }catch(FileNotFoundException e){
          System.out.println("File not found");
      }

      return mapa;
  }

}
