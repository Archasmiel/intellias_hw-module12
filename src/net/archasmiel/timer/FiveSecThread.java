package net.archasmiel.timer;

public class FiveSecThread extends Thread {

  private final CountUpDownLatch latch;

  public FiveSecThread(CountUpDownLatch latch) {
    this.latch = latch;
  }

  @Override
  public void run() {
    try {
      while (true) {
        latch.await();
        System.out.println("5 seconds has passed");
        latch.reset();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
