import java.util.Scanner;

public class TaskFive {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int toCheck = scanner.nextInt();
		int sum = 0;
		int multi = 1;
	    while(toCheck != 0) {
	        int digit = toCheck % 10;
	        toCheck /= 10;
	        sum += digit;
	        multi *= digit;
	    }
	    if(sum%2 == 0 && multi%2 == 0) System.out.println("Число является дважды чётным");
	    else System.out.println("Число не является дважды чётным");
	}
}