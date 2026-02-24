
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day1 {

    public static void main(String[] args) {

        // read all lines from input
        List<String> instructions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    instructions.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading input.txt: " + e.getMessage());
            System.err.println("Make sure input.txt exists in the same directory!");
            return;
        }

        System.out.println("Total instructions read: " + instructions.size());

        // state initial
        int position = 50;  
        int zeroCount = 0;  

        // process each one by one
        for (String instruction : instructions) {

            // parse direction and distance
            char direction = instruction.charAt(0);
            int distance = Integer.parseInt(instruction.substring(1));

            // apple rotation with modular arithmetic
            if (direction == 'R') {
                position = (position + distance) % 100;
            } else {
                position = ((position - distance) % 100 + 100) % 100;
            }

            // check if we landed on zero
            if (position == 0) {
                zeroCount++;
            }
        }

        System.out.println("The password is: " + zeroCount);
    }
}