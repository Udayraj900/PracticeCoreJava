package HackerrankCodeTest;

public class YearChoas {

	public static void main(String[] args) {
	
		int[] q = {2,1,5,3,4};
		minimumBribes(q);
	}
	
	static void minimumBribes(int[] q) {
        int n = q.length;
        int bribe = 0;
        boolean chaotic = false;
        for (int i = 0; i < n; i++) {
            if (q[i] - (i + 1) > 2) {
                chaotic = true;
                break;
            }
            for (int j = Math.max(0, q[i] - 2); j < i; j++)
                if (q[j] > q[i])
                    bribe++;
        }
        if (chaotic)
            System.out.println("Too chaotic");
        else
            System.out.println(bribe);
    }


}
