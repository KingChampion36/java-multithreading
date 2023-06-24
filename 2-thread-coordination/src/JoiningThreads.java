import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JoiningThreads {
  public static void main(String[] args) throws InterruptedException {
    List<Long> inputNumbers = Arrays.asList(0L, 100000000L, 3435L, 35435L, 2324L, 4656L, 23L, 2435L, 5566L);
    List<FactorialThread> threads = new ArrayList<>();
    for (long inputNumber : inputNumbers) {
      threads.add(new FactorialThread(inputNumber));
    }

    for (Thread thread : threads) {
      thread.setDaemon(true);
      thread.start();
    }

    // Use join method
    // This will force the main thread to wait till all factorialThreads are finished
    for (Thread thread : threads) {
      // thread.join();
      thread.join(2000); // Timeout added to counter long-running threads
    }

    // The factorialThreads and main thread are in a race condition and we don't know which will finish execution first
    for (int i = 0; i < inputNumbers.size(); i++) {
      FactorialThread factorialThread = threads.get(i);
      if (factorialThread.isFinished()) {
        System.out.println("Factorial of " + inputNumbers.get(i) + " is " + factorialThread.getResult());
      } else {
        System.out.println("The calculation for " + inputNumbers.get(i) + " is still in progress");
      }
    }
  }

  private static class FactorialThread extends Thread {
    private final long inputNumber;
    private BigInteger result = BigInteger.ZERO;
    private boolean isFinished = false;

    private FactorialThread(long inputNumber) {
      this.inputNumber = inputNumber;
    }

    @Override
    public void run() {
      this.result = factorial(inputNumber);
      this.isFinished = true;
    }

    public BigInteger factorial(long n) {
      BigInteger tempResult = BigInteger.ONE;
      for (long i = n; i > 0; i--) {
        tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
      }
      return tempResult;
    }

    public BigInteger getResult() {
      return result;
    }

    public boolean isFinished() {
      return isFinished;
    }
  }
}
