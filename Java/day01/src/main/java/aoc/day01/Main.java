package aoc.day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <input-file>");
            System.exit(1);
        }

        final File inputFile = new File(args[0]);

        List<Integer> rotations;

        try {
            rotations = parseInputFile(inputFile);
        } catch (final FileNotFoundException e) {
            System.err.println("Cannot read file: " + inputFile.getPath());
            System.exit(1);
            return;
        }

        final int passcodePartOne = computePasscode(rotations, new DialPartOne());
        final int passcodePartTwo = computePasscode(rotations, new DialPartTwo());

        System.out.println("The passcode for part one is " + passcodePartOne);
        System.out.println("The passcode for part two is " + passcodePartTwo);
    }

    /**
     * Parses the input file and extracts the list of rotations. Right rotations are
     * positive; left rotations are negative.
     *
     * @param inputFile the file to parse
     * @return a list of rotations
     * @throws FileNotFoundException if the file cannot be opened
     */
    public static List<Integer> parseInputFile(final File inputFile) throws FileNotFoundException {
        final List<Integer> list = new ArrayList<>();

        try (Scanner sc = new Scanner(inputFile)) {
            while (sc.hasNextLine()) {
                final String line = sc.nextLine().trim();

                if (line.isEmpty()) {
                    continue;
                }

                final char direction = line.charAt(0);
                final int amount = Integer.parseInt(line.substring(1));

                list.add(direction == 'R' ? amount : -amount);
            }

            return list;
        }
    }

    /**
     * Computes the passcode given a list of rotations and a dial.
     *
     * @param rotations list of integer rotations
     * @param dial      Concrete dial class to use
     * @return the computed passcode
     */
    public static int computePasscode(final List<Integer> rotations, final Dial dial) {
        for (final int rotation : rotations) {
            dial.rotate(rotation);
        }

        return dial.getPasscode();
    }
}
