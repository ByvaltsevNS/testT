import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void task0() {
        Scanner scanner = new Scanner(System.in);
        int num1 = scanner.nextInt();
        int num2 = scanner.nextInt();
        System.out.println(num1 + num2);
    }

    public static void task1() {
        //FileReader fr = new FileReader("text.txt");
        Scanner scan = new Scanner(System.in);
        int rateCost = scan.nextInt();
        int rateSize = scan.nextInt();
        int mibCost = scan.nextInt();
        int mibSum = scan.nextInt();
        int totalCost = rateCost;
        if (mibSum > rateSize) {
            totalCost += (mibSum - rateSize) * mibCost;
        }
        System.out.println(totalCost);
    }

    public static void task2() {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int answer = (int) Math.ceil(Math.log(N) / Math.log(2));
        System.out.println(answer);
    }

    public static void task3() {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int t = scan.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scan.nextInt();
        }
        int num = scan.nextInt();

        int sum = 0;
        for (int i = 1; i < arr.length; i++) {
            if (i == 1 && t < arr[num-1] - arr[0]) {
                sum += arr[num-1] - arr[0];
            }
            sum += arr[i] - arr[i-1];
        }
        System.out.println(sum);
    }

    public static void task4() {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();

        long[] arr = new long[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scan.nextLong();
        }

        Arrays.sort(arr);
        long sum = Arrays.stream(arr).sum();

        int maxRank = 0;
        for (int i = 0; arr[arr.length - 1] / Math.round(Math.pow(10, i)) > 0; i++) {
            maxRank++;
        }
        int[][] ranks = new int[maxRank][n];
        for (int i = 0; i < ranks.length; i++) {
            for (int j = 0; j < ranks[i].length; j++) {
                ranks[i][j] = (int) (arr[j] / Math.round(Math.pow(10, i)));
            }
        }

        long new_sum = sum;
        for (int i = ranks.length - 1; 0 < k && i >= 0; i--) {
            for (int j = 0; 0 < k && j < ranks[i].length; j++) {
                if (ranks[i][j] > 0 && ranks[i][j] < 9) {
                    new_sum += (9 - ranks[i][j]) * Math.pow(10, i);
                    k--;
                }
            }
        }

        System.out.println(new_sum - sum);
    }

    public static void task5() {
        Scanner scan = new Scanner(System.in);
        long l = scan.nextLong();
        long r = scan.nextLong();

        int minRank = (int) Math.floor(Math.log10(l));
        int maxRank = (int) Math.floor(Math.log10(r));

        long minValue = 0;
        for (int i = minRank; i >= 0; i--) {
            minValue += Math.pow(10, i);
        }

        long maxValue = 0;
        for (int i = maxRank; i >= 0; i--) {
            maxValue += Math.pow(10, i);
        }

        long answer = (maxRank - minRank) * 9;
        for (int i = 1; i < 10; i++) {
            if (l > minValue * i)
                answer--;
            if (r >= maxValue * i)
                answer++;
        }

        System.out.println(answer);
    }

    public static void task6() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
        }

        int even = 0, odd = 0;
        int[] nums = new int[2];
        for (int i = 0; i < arr.length; i++) {
            if ((i + 1) % 2 == 0) {
                if (arr[i] % 2 == 0) {

                } else {
                    odd++;
                    nums[1] = (i + 1);
                }
            } else {
                if (arr[i] % 2 == 1) {

                } else {
                    even++;
                    nums[0] = (i + 1);
                }
            }
        }
        Arrays.sort(nums);

        if (odd == 1 && even == 1) {
            System.out.println(nums[0] + " " + nums[1]);
        } else {
            System.out.println("-1 -1");
        }
    }

    public static void task7() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n+1];
        arr[0] = 0;
        int[] arrNum = new int[n+1];
        arrNum[0] = 0;
        for (int i = 1; i <= n; i++) {
            arr[i] = scanner.nextInt();
            arrNum[i] = 0;
        }

        for (int i = 1; i <= n; i++) {
            arrNum[arr[i]] += 1;
        }
        // System.out.println(Arrays.toString(arrNum));

        int[] arrOrder = new int[n+1];
        arrOrder[0] = 1;
        arrOrder[1] = arr[1];
        for (int i = 2; i <= n; i++) {
            arrOrder[i] = arr[arrOrder[i-1]];
        }
        // System.out.println(Arrays.toString(arrOrder));

        int repetitive = 0;
        int missing = 0;
        for (int i = 1; i <= n; i++) {
            if (arrNum[i] == 0) {
                if (missing != 0) {
                    System.out.println("-1 -1");
                    return;
                }
                missing = i;
            } else if (arrNum[i] == 2) {
                if (repetitive != 0) {
                    System.out.println("-1 -1");
                    return;
                }
                repetitive = i;
            } else if (arrNum[i] == 1) {

            } else {
                System.out.println("-1 -1");
                return;
            }
        }
        // System.out.println(missing + " " + repetitive);

        if (missing == 0 || repetitive == 0) {
            System.out.println("-1 -1");
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (arr[i] == repetitive) {
               arr[i] = missing;
               repetitive = i;
               break;
            }
        }
        System.out.println(repetitive + " " + missing);
        // System.out.println(Arrays.toString(arr));
    }

    private static class Rectangle {
        private float width; // x
        private float height; // y

        private float[] coords;

        public Rectangle(float width, float height) {
            this.width = width;
            this.height = height;
            coords = new float[8];
            coords[0] = coords[1] = coords[3] = coords[6] = 0;
            coords[2] = coords[4] = width;
            coords[5] = coords[7] = height;
        }

        public Rectangle(float[] coords) {
            this.coords = coords;
            this.width = coords[2] - coords[0];
            this.height = coords[5] - coords[1];
        }

        public float getWidth() {
            return width;
        }

        public float getHeight() {
            return height;
        }

        public float[] getCoords() {
            return coords;
        }
    }

    public static void task8() {
        Scanner scanner = new Scanner(System.in);
        float x = scanner.nextFloat();
        float y = scanner.nextFloat();
        Rectangle floor = new Rectangle(x, y);
        float[] coords = floor.getCoords();

        float[] coordsPlan = new float[8];
        for (int i = 0; i < coordsPlan.length; i++) {
            coordsPlan[i] = scanner.nextFloat();
        }
        Rectangle plan = new Rectangle(coordsPlan);

        float scale = plan.getWidth() / floor.getWidth();
        float[] offset = new float[2];
        offset[0] = coordsPlan[2] - coords[2];
        offset[1] = coordsPlan[3] - coords[3];

        float[][] matrix = new float[2][3];
        matrix[0][0] = coords[2];
        matrix[0][1] = coords[3];
        matrix[0][2] = coordsPlan[2];
        matrix[1][0] = coords[2];
        matrix[1][1] = coords[2];
        matrix[1][2] = coords[2];
        Math.acos(1);
    }

    public static void task9() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        LinkedList<Integer> lunches = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            lunches.add(scanner.nextInt());
        }

        LinkedList<Integer> coupons = new LinkedList<>();
        int sum = 0;
        for (Integer i: lunches) {
            if (i > 100) {
                coupons.add(i);
                sum += i;
            }
        }
        for (Integer i: coupons) {
            lunches.remove(i);
        }
        for (Integer i: coupons) {
            Integer max = 0;
            for (Integer j: lunches) {
                if (j > max) {
                    max = j;
                }
            }
            lunches.remove(max);
        }
        for (Integer i: lunches) {
            sum += i;
        }
        System.out.println(sum);
    }

    public static void main(String[] args) {
        task9();
    }
}
