package aoc.day01;

public abstract class Dial {
    int position = 50;
    int passcode = 0;

    public abstract void rotate(int amount);

    public int getPasscode() {
        return passcode;
    }
}
