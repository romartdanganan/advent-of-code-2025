import java.io.*;

public class Day2 {
    public static void main(String[] args) throws Exception {
        // read and join all lines into one string
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String input = br.readLine();
        br.close();

        String[] ranges = input.split(",");
        long totalSum = 0;

        for (String range : ranges) {
            String[] parts = range.split("-");
            long a = Long.parseLong(parts[0]);
            long b = Long.parseLong(parts[1]);

            // try each possible half-length k
            for (int k = 1; k <= 9; k++) {
                long pow10k = 1;
                for (int i = 0; i < k; i++) pow10k *= 10;

                long multiplier = pow10k + 1;           // e.g., 11, 101, 1001, ...
                long hLow  = (k == 1) ? 1 : pow10k / 10; // smallest k-digit number
                long hHigh = pow10k - 1;                   // largest k-digit number

                // we need: a <= h * multiplier <= b
                long hMin = (a + multiplier - 1) / multiplier;
                long hMax = b / multiplier;                    

                long lo = Math.max(hLow, hMin);
                long hi = Math.min(hHigh, hMax);

                if (lo > hi) continue;

                // sum of h values from low to high
                long count = hi - lo + 1;
                long sumH;
                if (count % 2 == 0) {
                    sumH = (count / 2) * (lo + hi);
                } else {
                    sumH = count * ((lo + hi) / 2);
                }

                totalSum += multiplier * sumH;
            }
        }

        System.out.println(totalSum);
    }
}