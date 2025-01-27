import java.util.Scanner;

public class TaskThree {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Введите координату клада X");
		int xCord = scanner.nextInt();
	    System.out.println("Введите координату клада Y");
	    int yCord = scanner.nextInt();
	    
        int x = 0,y = 0;
        int iteration = 0;
        int result = 0;
	    while(true) {
	        System.out.println("Введите направление");
	        String direction = scanner.next();
	        if(direction.equals("стоп")) break;
	        System.out.println("Введите количество шагов");
	        int steps = scanner.nextInt();
	        switch(direction) {
	            case "север":
	                y += steps;
	                break;
	            case "юг":
	                y -= steps;
	                break;
	            case "запад":
	                x -= steps;
	                break;
	            case "восток":
	                x += steps;
	                break;
	            default:
	                System.out.println("Неизвестное направление");
	                continue;
	        }
	        iteration++;
	        if(x == xCord && y == yCord && iteration != 0) {
	            result = iteration;
	        }
	    }
	    System.out.println(result);
	}
}