package aoc.day01;

public final class Dial {
    private int position = 50;
    private int passcode = 0;

    public void rotate(int amount) {
        position = ((position + amount) % 100 + 100) % 100;

        if (position == 0) {
            passcode++;
        }
    }

    public int getPasscode() {
        return passcode;
    }
}
