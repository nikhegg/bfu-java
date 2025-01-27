import java.util.Scanner;

public class TaskFour {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int totalRoads = scanner.nextInt();
		int suggestedRoad = 0;
		int maxHeight = 0;
		for(int i = 0; i < totalRoads; i++) {
		    int totalTunnels = scanner.nextInt();
		    int minRoadHeight = -1;
		    for(int j = 0; j < totalTunnels; j++) {
		        int height = scanner.nextInt();
		        if(height < minRoadHeight || minRoadHeight == -1) {
		            minRoadHeight = height;
		        }
		    }
		    if(minRoadHeight > maxHeight) {
		        suggestedRoad = i+1;
		        maxHeight = minRoadHeight;
		    }
		}
		System.out.println(suggestedRoad);
		System.out.println(maxHeight);
	}
}