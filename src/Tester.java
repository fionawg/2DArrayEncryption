public class Tester {
    public static void main(String[] args) {
        Encrypt y = new Encrypt("This is a normal sentence.");
        System.out.println(y.encryptString());

        Encrypt x = new Encrypt("sudAu.yJxyi qi e dqhcib jud");
        System.out.print(x.decryptString());
    }
}
