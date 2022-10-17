package net.archasmiel.timer;

public class Timer {

  private final Thread timer1Sec;
  private final Thread timerOnly5Sec;

  public Timer() throws InterruptedException {
    CountUpDownLatch latch = new CountUpDownLatch(1);
    timer1Sec = new OneSecThread(latch);
    timerOnly5Sec = new FiveSecThread(latch);
  }

  public void start() {
    timer1Sec.start();
    timerOnly5Sec.start();
  }

}
