import java.util.Scanner;

public class TaskOne {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
        int i = 0;
        while(n != 1) {
            i++;
            if(n%2 == 0) n = n/2;
            else n = 3*n + 1;
        }
        System.out.println(i);
	}
}