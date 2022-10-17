package net.archasmiel.timer;

public class OneSecThread extends Thread {

  private CountUpDownLatch latch;
  private int time;

  public OneSecThread(CountUpDownLatch latch) {
    this.latch = latch;
    time = 1;
  }

  @Override
  public void run() {
    try {
      while (true) {
        Thread.sleep(1000);
        System.out.print(time + " sec, ");
        if (time % 5 == 0) latch.countDown();
        time++;
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
