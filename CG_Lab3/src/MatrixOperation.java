public class MatrixOperation {

    static double[][] multiplyMatrix(double firstMatrix[][], double secondMatrix[][]) {
        int i, j, k;

        if (secondMatrix.length != firstMatrix[0].length) {
            System.out.println("\nMultiplication Not Possible");
            throw new RuntimeException();
        }

        double resultMatrix[][] = new double[firstMatrix.length][secondMatrix[0].length];


        for (i = 0; i < firstMatrix.length; i++) {
            for (j = 0; j < secondMatrix[0].length; j++) {
                for (k = 0; k < secondMatrix.length; k++)
                    resultMatrix[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
            }
        }
        return resultMatrix;
    }

    static double[][] createRotationMatrixAroundPoint(int angle, int x2, int y2){
        double[][] mRot = {
                {Math.cos(Math.toRadians(angle)), Math.sin(Math.toRadians(angle)), 0},
                {-Math.sin(Math.toRadians(angle)), Math.cos(Math.toRadians(angle)), 0},
                {0, 0, 1}};
        double[][] mMov1 = {
                {1, 0, 0},
                {0, 1, 0},
                {-x2, -y2, 1}};
        double[][] mMov2 = {
                {1, 0, 0},
                {0, 1, 0},
                {x2, y2, 1}};

        return MatrixOperation.multiplyMatrix(
                MatrixOperation.multiplyMatrix(mMov1, mRot), mMov2);
    }
}
