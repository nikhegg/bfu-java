package Lab2;

public class TaskFour {
    public static int[][] rotate2DArray(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows];

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                result[i][j] = matrix[cols - j - 1][i];
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        int[][] matrix = {
            {1,3,5},
            {6,2,4},
            {9,8,3}
        };
        int[][] result = rotate2DArray(matrix);
        for(int i = 0; i < result.length; i++) {
            for(int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}
