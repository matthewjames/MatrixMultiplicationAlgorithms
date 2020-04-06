public class Main {
    public static void main(String[] args) {
//        int[][] arr1 = {{1,2},{3,4}};
//        int[][] arr2 = {{1,2},{3,4}};

//        int[][] arr1 = {{1,2,3,4},{1,2,3,4},{1,2,3,4},{1,2,3,4}};
//        int[][] arr2 = {{1,2,3,4},{1,2,3,4},{1,2,3,4},{1,2,3,4}};


        int[][] arr1 = {{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16},
                {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16},
                {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16},
                {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16},
                {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16},
                {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16},
                {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16},
                {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16},
                {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16},
                {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16},
                {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16},
                {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16},
                {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16},
                {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16},
                {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16},
                {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16}};
        int[][] arr2 = {{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16},
                {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16},
                {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16},
                {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16},
                {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16},
                {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16},
                {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16},
                {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16},
                {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16},
                {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16},
                {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16},
                {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16},
                {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16},
                {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16},
                {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16},
                {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16}};


//        int[][] arr1 = {{1,2},{1,2}};
//
//        System.out.println("Input matrix: " + print2DArray(arr1));
//        for(int i = 1; i < 5; i++){
//            System.out.println("Quadrant " + i + ": " + print2DArray(divideMatrix(arr1,i)));
//        }

        System.out.println(print2DArray(arr1) + "\n\n * \n\n" + print2DArray(arr2) + "\n\n = \n\n" + print2DArray(classicMatrixMultiply(arr1, arr2)) + "\n(Classic)");
        System.out.println(print2DArray(arr1) + "\n\n * \n\n" + print2DArray(arr2) + "\n\n = \n\n" + print2DArray(divideAndConquer(arr1, arr2, arr1.length))+ "\n(Divide and Conquer)");
//        System.out.println(print2DArray(arr1) + " * " + print2DArray(arr2) + " = " + print2DArray(strassen(arr1, arr2)) + "(Strassen's)");

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
            int[][] c11 = new int[n/2][n/2];
            int[][] c12 = new int[n/2][n/2];
            int[][] c21 = new int[n/2][n/2];
            int[][] c22 = new int[n/2][n/2];


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

            // put outputMatrix matrix together from c quadrants
            for(int i = 0; i < c11.length; i++){
                for(int j = 0; j < c11.length; j++){
                    c[i][j] += c11[i][j];
                    c[i+n/2][j] += c12[i][j];
                    c[i][j+n/2] += c21[i][j];
                    c[i+n/2][j+n/2] += c22[i][j];
                }
            }
        }

        return c;
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
