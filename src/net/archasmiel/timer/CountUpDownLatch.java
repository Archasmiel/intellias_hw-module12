package net.archasmiel.timer;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class CountUpDownLatch {

  private static final class Sync extends AbstractQueuedSynchronizer {
    private static final long serialVersionUID = 4982264981922014374L;
    public final int startCount;

    Sync(int count) {
      this.startCount = count;
      setState(startCount);
    }

    int getCount() {
      return getState();
    }

    protected int tryAcquireShared(int acquires) {
      return (getState() == 0) ? 1 : -1;
    }

    protected boolean tryReleaseShared(int releases) {
      for (;;) {
        int c = getState();
        if (c == 0)
          return false;
        int nextc = c - 1;
        if (compareAndSetState(c, nextc))
          return nextc == 0;
      }
    }

    public void reset() {
      setState(startCount);
    }
  }

  private final Sync sync;

  public CountUpDownLatch(int count) {
    if (count < 0) throw new IllegalArgumentException("count < 0");
    this.sync = new Sync(count);
  }

  public void await() throws InterruptedException {
    sync.acquireSharedInterruptibly(1);
  }

  public boolean await(long timeout, TimeUnit unit)
      throws InterruptedException {
    return sync.tryAcquireSharedNanos(1, unit.toNanos(timeout));
  }

  public void reset() {
    sync.reset();
  }

  public void countDown() {
    sync.releaseShared(1);
  }

  public long getCount() {
    return sync.getCount();
  }

  public String toString() {
    return super.toString() + "[Count = " + sync.getCount() + "]";
  }
}
