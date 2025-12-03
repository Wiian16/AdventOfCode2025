package aoc.day01;

public class DialPartOne extends Dial {
    @Override
    public void rotate(final int amount) {
        position = ((position + amount) % 100 + 100) % 100;

        if (position == 0) {
            passcode++;
        }
    }
}
