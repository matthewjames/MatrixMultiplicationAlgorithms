public class Main {
    /*

    [[1 ,[4 ,[7 ]
      2   5   8
      3]  6]  9]

     */
    public static void main(String[] args) {
	    int[][] arr1 = {{1,2,3,4},{1,2,3,4},{1,2,3,4},{1,2,3,4}};
        int[][] arr2 = {{1,2,3,4},{1,2,3,4},{1,2,3,4},{1,2,3,4}};

//        System.out.println(print2DArray(matrixAddition(arr1,arr2)));

        System.out.println(print2DArray(strassen(arr1, arr2)));

//        System.out.println(print2DArray(arr1) + " * " + print2DArray(arr2) + " = " + print2DArray(classicMatrixMultiply(arr1, arr2)));

    }

    private static int[][] classicMatrixMultiply(int[][] matrix1, int[][] matrix2){
        int n = matrix1.length;
        int[][] result = new int[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                int sum = 0;
                for(int k = 0; k < n; k++){
                    sum += matrix1[i][k] * matrix2[k][j];
                }
                result[i][j] = sum;
            }
        }

        return result;
    }

    private static int[][] divideAndConquer(int[][] matrix1, int[][] matrix2){
        int n = matrix1.length/2;
        int[][] result = new int[n*2][n*2];

        if(n != 2) {
            int[][] a11 = new int[n][n];
            int[][] a12 = new int[n][n];
            int[][] a21 = new int[n][n];
            int[][] a22 = new int[n][n];
            int[][] b11 = new int[n][n];
            int[][] b12 = new int[n][n];
            int[][] b21 = new int[n][n];
            int[][] b22 = new int[n][n];
            int[][] c11;
            int[][] c12;
            int[][] c21;
            int[][] c22;

            // divide input matrices into four quadrants each
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    a11[j][i] = matrix1[j][i];
                    a12[j][i] = matrix1[j + n][i];
                    a21[j][i] = matrix1[j][i + n];
                    a22[j][i] = matrix1[j + n][i + n];
                    b11[j][i] = matrix2[j][i];
                    b12[j][i] = matrix2[j + n][i];
                    b21[j][i] = matrix2[j][i + n];
                    b22[j][i] = matrix2[j + n][i + n];
                }
            }

            // create quadrants of the resulting matrix by performing classic matrix multiplication using each quadrant as indivdual elements
            c11 = classicMatrixMultiply(divideAndConquer(a11, b11),divideAndConquer(a12,b21));
            c12 = classicMatrixMultiply(divideAndConquer(a11, b12),divideAndConquer(a12,b22));
            c21 = classicMatrixMultiply(divideAndConquer(a21, b11),divideAndConquer(a22,b21));
            c22 = classicMatrixMultiply(divideAndConquer(a21, b12),divideAndConquer(a22,b22));

            // put result matrix together from c quadrants
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    result[i][j] = c11[i][j];
                    result[i+n][j] = c12[i][j];
                    result[i][j+n] = c21[i][j];
                    result[i+n][j+n] = c22[i][j];
                }
            }

        } else {
            result = classicMatrixMultiply(matrix1, matrix2);
        }

        return result;
    }

    private static int[][] strassen(int[][] matrix1, int[][] matrix2){
        int n = matrix1.length/2;
        int[][] result = new int[n*2][n*2];

        if(n != 2) {
            int[][] a11 = new int[n][n];
            int[][] a12 = new int[n][n];
            int[][] a21 = new int[n][n];
            int[][] a22 = new int[n][n];
            int[][] b11 = new int[n][n];
            int[][] b12 = new int[n][n];
            int[][] b21 = new int[n][n];
            int[][] b22 = new int[n][n];
            int[][] p;
            int[][] q;
            int[][] r;
            int[][] s;
            int[][] t;
            int[][] u;
            int[][] v;
            int[][] c11;
            int[][] c12;
            int[][] c21;
            int[][] c22;

            // divide input matrices into four quadrants each
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    a11[j][i] = matrix1[j][i];
                    a12[j][i] = matrix1[j + n][i];
                    a21[j][i] = matrix1[j][i + n];
                    a22[j][i] = matrix1[j + n][i + n];
                    b11[j][i] = matrix2[j][i];
                    b12[j][i] = matrix2[j + n][i];
                    b21[j][i] = matrix2[j][i + n];
                    b22[j][i] = matrix2[j + n][i + n];
                }
            }

            p = strassen(matrixAddition(a11,a22),matrixAddition(b11,b22));
            q = strassen(matrixAddition(a21,a22),b11);
            r = strassen(a11,matrixSubtraction(b12,b22));
            s = strassen(a22, matrixSubtraction(b21, b11));
            t = strassen(matrixAddition(a11,a12), b22);
            u = strassen(matrixSubtraction(a21,a11),matrixAddition(b11, b12));
            v = strassen(matrixSubtraction(a12,a22), matrixAddition(b21,b22));
            c11 = matrixSubtraction(matrixAddition(p,s),matrixAddition(t,v));
            c12 = matrixAddition(r,t);
            c21 = matrixAddition(q,s);
            c22 = matrixSubtraction(matrixAddition(p,r), matrixAddition(q,u));

            // put result matrix together from c quadrants
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    result[i][j] = c11[i][j];
                    result[i+n][j] = c12[i][j];
                    result[i][j+n] = c21[i][j];
                    result[i+n][j+n] = c22[i][j];
                }
            }

        } else {
            result = classicMatrixMultiply(matrix1, matrix2);
        }

        return result;
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

    private static String print1DArray(int[] array){
        StringBuilder output = new StringBuilder("{");

        for(int i = 0; i < array.length; i++){
            output.append(array[i]);
            output.append(i != array.length - 1 ? "," : "}");
        }

        return output.toString();
    }

    private static String print2DArray(int[][] array){
        StringBuilder output = new StringBuilder("{");

        for(int i = 0; i < array.length; i++){
            output.append(print1DArray(array[i]));
            output.append(i != array.length - 1 ? "," : "}");
        }

        return output.toString();
    }
}
