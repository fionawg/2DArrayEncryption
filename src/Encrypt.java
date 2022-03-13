import java.lang.reflect.Array;
import java.util.*;
public class Encrypt {
    private int rows;
    private int cols;
    private String string;
    private String decrypted;
    private String encrypted;
    private ArrayList<String> alphabet; //normal alphabet
    private ArrayList<String> shiftedAlphabet; //shifts each letter by 16 so A becomes Q
    private String[][] letterBlock;


    public Encrypt(String string){
        if (string.length() % 3 == 0){
            rows = string.length() / 3;
        } else {
            rows = string.length() / 3 + 1;
        }
        cols = 3;
        this.string = string;
        decrypted = "";
        encrypted = "";
        alphabet = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", " ", ".", ","));
        shiftedAlphabet = new ArrayList<String>(Arrays.asList("Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", " ", ".", ","));
        letterBlock = new String[rows][cols];
    }

    public String encryptString(){
        shiftLetter();
        fillBlock(encrypted);
        shiftRow(); //shift every row to the right by one
        shiftColumn(); //shift every column down by two
        //puts the 2D array into a string
        encrypted = "";
        for (int i = 0; i < letterBlock.length; i++){
            for (int j = 0; j < letterBlock[0].length; j++){
                encrypted += letterBlock[i][j];
            }
        }
        return encrypted;
    }

    public String decryptString(){
        fillBlock(string);
        shiftColumnDecrypt(); //shift every column down by rows minus 2
        shiftRowDecrypt(); //shift every row to the right by 2
        string = "";
        //puts the 2D array into a string
        for (int i = 0; i < letterBlock.length; i++){
            for (int j = 0; j < letterBlock[0].length; j++){
                string += letterBlock[i][j];
            }
        }
        shiftLetterDecrypt();
        while (decrypted.substring(decrypted.length() - 1).equals("K")){
            decrypted = decrypted.substring(0, decrypted.length() - 1);
        }
        return decrypted;
    }

    private void shiftLetter(){
        for (int i = 0; i < string.length(); i++){
            int index = i;
            for (int j = 0; j < alphabet.size(); j++){
                if (string.substring(index, index + 1).equals(alphabet.get(j))){
                    encrypted += shiftedAlphabet.get(j);
                }
            }
        }
    }

    private void shiftLetterDecrypt(){
        for (int i = 0; i < string.length(); i++){
            int index = i;
            for (int j = 0; j < shiftedAlphabet.size(); j++){
                if (string.substring(index, index + 1).equals(shiftedAlphabet.get(j))){
                    decrypted += alphabet.get(j);
                }
            }
        }
    }

    private void fillBlock(String str){
        int count = 0;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if (count < str.length()){
                    letterBlock[i][j] = str.substring(count, count + 1);
                    count++;
                } else {
                    letterBlock[i][j] = "A";
                }
            }
        }
    }

    private void shiftRow(){
        for (int i = 0; i < letterBlock.length; i++){
            String temp = letterBlock[i][letterBlock[0].length-1];
            for (int j = letterBlock[0].length - 1; j > 0 ; j--){
                letterBlock[i][j] = letterBlock[i][j-1];
            }
            letterBlock[i][0] = temp;
        }
    }

    private void shiftRowDecrypt(){
        for (int shiftCount = 0; shiftCount < 2; shiftCount++){
            for (int i = 0; i < letterBlock.length; i++){
                String temp = letterBlock[i][letterBlock[0].length-1];
                for (int j = letterBlock[0].length - 1; j > 0 ; j--){
                    letterBlock[i][j] = letterBlock[i][j-1];
                }
                letterBlock[i][0] = temp;
            }
        }
    }

    private void shiftColumn(){
        for (int shiftCount = 0; shiftCount < 2; shiftCount++){
            for (int i = 0; i < letterBlock[0].length; i++){
                String temp = letterBlock[letterBlock.length-1][i];
                for (int j = letterBlock.length - 1; j > 0 ; j--){
                    letterBlock[j][i] = letterBlock[j-1][i];
                }
                letterBlock[0][i] = temp;
            }
        }
    }

    private void shiftColumnDecrypt(){
        for (int shiftCount = 0; shiftCount < rows - 2; shiftCount++){
            for (int i = 0; i < letterBlock[0].length; i++){
                String temp = letterBlock[letterBlock.length-1][i];
                for (int j = letterBlock.length - 1; j > 0 ; j--){
                    letterBlock[j][i] = letterBlock[j-1][i];
                }
                letterBlock[0][i] = temp;
            }
        }
    }
}
