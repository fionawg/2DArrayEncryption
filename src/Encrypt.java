import java.util.*;
public class Encrypt {
    private int rows;
    private int cols;
    private String toEncrypt;
    private ArrayList<String> alphabet;

    public Encrypt(String toEncrypt){
        rows = 3;
        cols = 3;
        this.toEncrypt = toEncrypt;
        alphabet = new ArrayList<String>(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"));
    }

    public String encryptString(){
        return null;
    }

    public void shift(){
        for (int i = 0; i < 10; i++){
            alphabet.add(0, "temp");
            alphabet.set(0 ,alphabet.remove(alphabet.size()-1));
        }
        System.out.println(alphabet);
    }
}
