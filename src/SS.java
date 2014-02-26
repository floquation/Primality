import java.util.Random;

class SS {
    Random rnd = new Random();

    /** 
     * Return whether n is probably prime with accurary k 
     */
    public static boolean ss(int n, int k) {

        // Handle even numbers
        if (n % 2 == 0) {
            return false;
        }

        for (int i = 0; i < k; i++) {
            int a = rnd.randint(2, n);
            x = a/n;
            if (x == 0 || Math.pow(a, (n - 1) / 2) != x)
                return false;
        }
        
        return true;
    }
}
