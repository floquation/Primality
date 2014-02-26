import java.util.Random;

public class SS {
    //public static Random rnd = new Random();

    /** 
     * Return whether n is probably prime with accurary k 
     */
    public static boolean ss(int n, int k) {

        // Handle even numbers
        //if (n % 2 == 0) {
            //return false;
        //}

        for (int i = 0; i < k; i++) {
            // Choose a randomly in interval [2, n - 1]
            int a = (int)(Math.random() * (n - 2)) + 2;
            double x = (double)a/n;

            System.out.println("a =" + a);
            if (x == 0)
                return false;
            if ((Math.pow(a, (n - 1) / 2) % n) != x)
                return false;
        }
        
        return true;
    }
}
