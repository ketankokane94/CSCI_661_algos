

public class Recursion{

    public static void main(String arg []){   
        System.out.println(stepCount(40 ,new double[41]));
    }

    private static double stepCount(int N,double[] cache){
        if (N < 1){
            return 0;
        }
        if (N == 1 ){
            return 1;
        }
        if (cache[N] == 0.0 ){
        cache[N] = 1 +  stepCount(N-1,cache) + stepCount(N-2,cache) + stepCount(N-3,cache);
        }
        return cache[N];
    }
}
