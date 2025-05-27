package task1;

public class task1 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        StringBuilder path = new StringBuilder();
        int a = 1;
        boolean ftry = true;
        do {
            if (ftry) {
                path.append(a);  
                ftry = false;
            } else {
                path.append(a);
            }
            a = (a + m - 1) % n;
            if (a == 0) {
                a = n;
            }
        } while (a != 1);
        System.out.println(path.toString());
    }
}