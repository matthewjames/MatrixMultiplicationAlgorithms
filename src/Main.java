import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        int times = 25;
        long classicRuntime, dacRuntime, strassenRuntime, start, stop;
        int[][] matrix1, matrix2, result;
        Random r = new Random();

        for(int i = 0, n = 2; i < 10; i++, n *= 2){
            classicRuntime = 0;
            dacRuntime = 0;
            strassenRuntime = 0;


            matrix1 = createMatrix(n, r, 0, 99);
            matrix2 = createMatrix(n, r, 0, 99);
            result = new int[n][n];
            for(int j = 0; j < times; j++){

                // run classic Matrix Multiplication
                start = System.nanoTime();
                classicMatrixMultiply(matrix1, matrix2);
                stop = System.nanoTime();
                classicRuntime += (stop - start);

                // run divide and conquer Matrix Multiplication
                start = System.nanoTime();
                divideAndConquer(matrix1, matrix2, matrix1.length);
                stop = System.nanoTime();
                dacRuntime += (stop - start);

                // run strassen Matrix Multiplication
                start = System.nanoTime();
                strassen(matrix1, matrix2, result, matrix1.length);
                stop = System.nanoTime();
                strassenRuntime += (stop - start);
            }
            System.out.printf("---- %d x %d Matrix Multiplication Results ----\n\n", n, n);
            System.out.printf("%-20s %20d ns\n","Classic:", classicRuntime/times);
            System.out.printf("%-20s %20d ns\n","Divide and Conquer:", dacRuntime/times);
            System.out.printf("%-20s %20d ns\n\n", "Strassen's:", strassenRuntime/times);
        }
    }

    // generates a matrix of size n x n, populated by random integers in the range of min to max
    private static int[][] createMatrix(int n, Random random, int min, int max){
        int[][] result = new int[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                result[i][j] = random.nextInt(max - min + 1) + min;
            }
        }

        return result;
    }

    private static int[][] classicMatrixMultiply(int[][] matrix1, int[][] matrix2){
        int n = matrix1.length;
        int[][] result = new int[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < n; k++){
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        return result;
    }

    private static int[][] divideAndConquer(int[][] matrix1, int[][] matrix2, int n){
        int[][] c = new int[n][n];

        if(n == 1){
            c[0][0] = matrix1[0][0] * matrix2[0][0];
        } else {
            int[][] a11 = new int[n/2][n/2];
            int[][] a12 = new int[n/2][n/2];
            int[][] a21 = new int[n/2][n/2];
            int[][] a22 = new int[n/2][n/2];
            int[][] b11 = new int[n/2][n/2];
            int[][] b12 = new int[n/2][n/2];
            int[][] b21 = new int[n/2][n/2];
            int[][] b22 = new int[n/2][n/2];
            int[][] c11;
            int[][] c12;
            int[][] c21;
            int[][] c22;

            // partition the matricies into quadrants
            divideMatrix(matrix1, a11,1);
            divideMatrix(matrix1, a12,2);
            divideMatrix(matrix1, a21,3);
            divideMatrix(matrix1, a22,4);
            divideMatrix(matrix2, b11,1);
            divideMatrix(matrix2, b12,2);
            divideMatrix(matrix2, b21,3);
            divideMatrix(matrix2, b22,4);

            c11 = matrixAddition(divideAndConquer(a11, b11, a11.length), divideAndConquer(a12, b21, a12.length));
            c12 = matrixAddition(divideAndConquer(a11, b12, a11.length), divideAndConquer(a12, b22, a12.length));
            c21 = matrixAddition(divideAndConquer(a21, b11, a21.length), divideAndConquer(a22, b21, a22.length));
            c22 = matrixAddition(divideAndConquer(a21, b12, a21.length), divideAndConquer(a22, b22, a22.length));

            // put c matrix together from c quadrants
            for(int i = 0; i < c11.length; i++){
                for(int j = 0; j < c11.length; j++){
                    c[i][j] = c11[i][j];
                    c[i+n/2][j] = c12[i][j];
                    c[i][j+n/2] = c21[i][j];
                    c[i+n/2][j+n/2] = c22[i][j];
                }
            }
        }

        return c;
    }

    private static void strassen(int[][] matrix1, int[][] matrix2, int[][] result, int n){
        int[][] c11;
        int[][] c12;
        int[][] c21;
        int[][] c22;

        if(n == 2) {
            result[0][0] = (matrix1[0][0] * matrix2[0][0]) + (matrix1[1][0] * matrix2[0][1]);
            result[1][0] = (matrix1[0][0] * matrix2[1][0]) + (matrix1[1][0] * matrix2[1][1]);
            result[0][1] = (matrix1[0][1] * matrix2[0][0]) + (matrix1[1][1] * matrix2[0][1]);
            result[1][1] = (matrix1[0][1] * matrix2[1][0]) + (matrix1[1][1] * matrix2[1][1]);
        } else {
            n /= 2;
            int[][] a11 = new int[n][n];
            int[][] a12 = new int[n][n];
            int[][] a21 = new int[n][n];
            int[][] a22 = new int[n][n];
            int[][] b11 = new int[n][n];
            int[][] b12 = new int[n][n];
            int[][] b21 = new int[n][n];
            int[][] b22 = new int[n][n];
            int[][] p = new int[n][n];
            int[][] q = new int[n][n];
            int[][] r = new int[n][n];
            int[][] s = new int[n][n];
            int[][] t = new int[n][n];
            int[][] u = new int[n][n];
            int[][] v = new int[n][n];
            n *= 2;

            // partition the matricies into quadrants
            divideMatrix(matrix1, a11,1);
            divideMatrix(matrix1, a12,2);
            divideMatrix(matrix1, a21,3);
            divideMatrix(matrix1, a22,4);
            divideMatrix(matrix2, b11,1);
            divideMatrix(matrix2, b12,2);
            divideMatrix(matrix2, b21,3);
            divideMatrix(matrix2, b22,4);

            strassen(matrixAddition(a11,a22),matrixAddition(b11,b22), p, n/2);
            strassen(matrixAddition(a21,a22),b11, q, n/2);
            strassen(a11,matrixSubtraction(b12,b22), r, n/2);
            strassen(a22, matrixSubtraction(b21, b11), s, n/2);
            strassen(matrixAddition(a11,a12), b22, t, n/2);
            strassen(matrixSubtraction(a21,a11),matrixAddition(b11, b12), u, n/2);
            strassen(matrixSubtraction(a12,a22), matrixAddition(b21,b22), v, n/2);

            c11 = matrixAddition(matrixSubtraction(matrixAddition(p,s), t), v);
            c12 = matrixAddition(r,t);
            c21 = matrixAddition(q,s);
            c22 = matrixAddition(matrixSubtraction(matrixAddition(p,r), q), u);

            // put result matrix together from c quadrants
            for(int i = 0; i < c11.length; i++){
                for(int j = 0; j < c11.length; j++){
                    result[i][j] = c11[i][j];
                    result[i+n/2][j] = c12[i][j];
                    result[i][j+n/2] = c21[i][j];
                    result[i+n/2][j+n/2] = c22[i][j];
                }
            }
        }
    }

    private static void divideMatrix(int[][] matrix, int[][] outputMatrix, int quadrant){
        int n = matrix.length/2;

        switch(quadrant){
            case 1:
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        outputMatrix[j][i] = matrix[j][i];
                    }
                }
                break;
            case 2:
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        outputMatrix[j][i] = matrix[j + n][i];
                    }
                }
                break;
            case 3:
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        outputMatrix[j][i] = matrix[j][i + n];
                    }
                }
                break;
            case 4:
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        outputMatrix[j][i] = matrix[j + n][i + n];
                    }
                }
                break;
        }
    }

    private static int[][] matrixAddition(int[][] matrix1, int[][] matrix2){
        int n = matrix1.length;
        int[][] result = new int[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }

        return result;
    }

    private static int[][] matrixSubtraction(int[][] matrix1, int[][] matrix2){
        int n = matrix1.length;
        int[][] result = new int[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                result[i][j] = matrix1[i][j] - matrix2[i][j];
            }
        }

        return result;
    }

    private static String print2DArray(int[][] array){
        StringBuilder output = new StringBuilder();

        for(int i = 0; i < array.length; i++){
            output.append("|");

            for(int j = 0; j < array.length; j++){
                output.append(String.format("%10d", array[j][i]));
            }

            if(i == array.length - 1){
                output.append("|");
            } else {
                output.append("|\n");
            }
        }

        return output.toString();
    }
}
