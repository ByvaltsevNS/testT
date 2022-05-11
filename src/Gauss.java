//Метод Гаусса
class Gauss {
    private static final float E = 0.0001f;
    private static boolean solution_exist = true;
    private static int N;
    private static float[][] A;
    private static float[] X;

    static void calc(final int n, final float[][] matrix) {
        N = n;
        A = matrix;
        X = new float[N];
        System.out.println("Исходная матрица");
        print_matrix();
        matrix_forward();
        System.out.println("Треугольная матрица");
        print_matrix();
        matrix_check();
        print_determinant();
        if (solution_exist) matrix_reverse();
    }

    private static void print_matrix() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N + 1; j++)
                System.out.printf("%10.3f\t", A[i][j]);
            System.out.println();
        }
        System.out.println();
    }

    //Проверка на наличие решения
    private static void matrix_check() {
        if (Math.abs(A[N - 1][N - 1]) < E) {
            solution_exist = false;
            if (Math.abs(A[N - 1][N]) < E) System.out.println("Система имеет бесконечное число решений!");
            else System.out.println("Система не имеет решений!");
        }
    }

    private static void print_determinant() {
        float Determinant = 1.0f;
        for (int i = 0; i < N; i++)
            Determinant *= A[i][i];
        System.out.printf("Детерминант = %.4f\n\n", Determinant);
    }

    //Прямой ход метода Гаусса
    private static void matrix_forward() {
        for (int k = 0; k < N - 1; k++) {
            if (A[k][k] != 0) {
                for (int j = k + 1; j < N; j++) {
                    float m = A[j][k] / A[k][k];//Коэффициент М
                    for (int i = k; i < N + 1; i++)
                        A[j][i] = A[j][i] - A[k][i] * m;
                    A[j][k] = 0;
                }
            }
            else {
                solution_exist = false;
                System.out.println("Алгоритм для решения не применим!");
            }
        }
    }

    //Обратный ход метода Гаусса
    private static
    void matrix_reverse() {
        for (int k = N - 1; k > 0; k--) {
            float s = A[k][N];
            for (int j = N - 1; j > k + 1; k--)
                s = s - A[k][j] * X[j];
            X[k] = s / A[k][k];
        }
        System.out.println("Корни системы уравнений:");
        for (int i = 0; i < N; i++)
            System.out.printf("X[%d] = %06.3f\n", i, X[i]);
    }

}
