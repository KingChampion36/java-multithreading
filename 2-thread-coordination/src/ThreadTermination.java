import java.math.BigInteger;

public class ThreadTermination {
  public static void main(String[] args) {
    Thread thread = new Thread(new BlockingTask());
    thread.start();

    // Stop the blocking thread
    thread.interrupt();

    Thread longComputationalThread = new Thread(new LongComputationalTask(new BigInteger("2000000"), new BigInteger("10000000")));
    longComputationalThread.start();
    longComputationalThread.interrupt();
  }

  private static class BlockingTask implements Runnable {

    @Override
    public void run() {
      try {
        Thread.sleep(500000);
      } catch (InterruptedException e) {
        System.out.println("Exiting blocking thread");
      }
    }
  }

  private static class LongComputationalTask implements Runnable {

    private final BigInteger base;
    private final BigInteger power;

    private LongComputationalTask(BigInteger base, BigInteger power) {
      this.base = base;
      this.power = power;
    }

    @Override
    public void run() {
      System.out.println(base + "^" + power + " = " + pow(base, power));
    }

    private BigInteger pow(BigInteger base, BigInteger power) {
      BigInteger result = BigInteger.ONE;
      for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i=i.add(BigInteger.ONE)) {
        // Handle the case when thread is interrupted
        // If not done, thread will keep running until the for loop is completed
        if (Thread.currentThread().isInterrupted()) {
          System.out.println("Prematurely interrupted computation");
          return BigInteger.ZERO;
        }
        result = result.multiply(base);
      }
      return result;
    }
  }
}
