package aoc.day01;

public class DialPartTwo extends Dial {
    @Override
    public void rotate(final int amount) {
        final int newPos = position + amount;

        if (amount > 0) {
            passcode += newPos / 100;
        } else if (amount < 0) {

            if (newPos == 0) {
                passcode++;
            } else if (newPos < 0) {
                passcode += Math.abs(newPos / 100);
                if (position != 0) {
                    passcode++;
                }
            }

        }

        position = (newPos % 100 + 100) % 100;
    }
}
