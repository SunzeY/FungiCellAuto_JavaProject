import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        int size;
        System.out.println("set n to initial frame(n*n)(50-300 recommended):");
        Scanner a = new Scanner(System.in);
        size = a.nextInt();
        new FungiModule(size);
    }
}
