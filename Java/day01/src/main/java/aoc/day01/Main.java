package aoc.day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <input-file>");
            System.exit(1);
        }

        File inputFile = new File(args[0]);

        List<Integer> rotations;

        try {
            rotations = parseInputFile(inputFile);
        } catch (FileNotFoundException e) {
            System.err.println("Cannot read file: " + inputFile.getPath());
            System.exit(1);
            return;
        }

        int passcode = computePasscode(rotations);

        System.out.println("The passcode is " + passcode);
    }

    /**
     * Parses the input file and extracts the list of rotations. Right rotations are
     * positive; left rotations are negative.
     *
     * @param inputFile the file to parse
     * @return a list of rotations
     * @throws FileNotFoundException if the file cannot be opened
     */
    public static List<Integer> parseInputFile(File inputFile) throws FileNotFoundException {
        List<Integer> list = new ArrayList<>();

        try (Scanner sc = new Scanner(inputFile)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();

                if (line.isEmpty()) {
                    continue;
                }

                char direction = line.charAt(0);
                int amount = Integer.parseInt(line.substring(1));

                list.add(direction == 'R' ? amount : -amount);
            }

            return list;
        }
    }

    /**
     * Computes the passcode given a list of rotations. The dial starts at 50 and
     * wraps around modulo 100. The code increases each time the dial lands exactly
     * on 0.
     *
     * @param rotations list of integer rotations
     * @return the computed passcode
     */

    public static int computePasscode(List<Integer> rotations) {
        Dial dial = new Dial();

        for (int rotation : rotations) {
            dial.rotate(rotation);
        }

        return dial.getPasscode();
    }
}
