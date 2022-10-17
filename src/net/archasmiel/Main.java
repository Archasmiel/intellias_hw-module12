package net.archasmiel;

import net.archasmiel.fizzbuzz.FizzBuzz;
import net.archasmiel.timer.Timer;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // new Timer().start();
        new FizzBuzz().soutNumbers(120000);
    }
}
