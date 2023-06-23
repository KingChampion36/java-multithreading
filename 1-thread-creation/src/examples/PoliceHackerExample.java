package examples;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PoliceHackerExample {

  public static final int MAX_PASSWORD = 999;

  public static void main(String[] args) {
    Random random = new Random();
    Vault vault = new Vault(random.nextInt(MAX_PASSWORD));

    List<Thread> threads = new ArrayList<>();
    threads.add(new AscendingHackerThread(vault));
    threads.add(new DescendingHackerThread(vault));
    threads.add(new PoliceThread());

    threads.forEach(Thread::start);
  }

  private static class Vault {
    private final int password;

    private Vault(int password) {
      this.password = password;
    }

    public boolean isPasswordCorrect(int guessedPassword) {
      try {
        Thread.sleep(25);
      } catch (Exception ignored) {
      }
      return guessedPassword == this.password;
    }
  }

  private static class HackerThread extends Thread {
    protected Vault vault;

    public HackerThread(Vault vault) {
      this.vault = vault;
      this.setName(this.getClass().getSimpleName());
      this.setPriority(Thread.MAX_PRIORITY);
    }

    @Override
    public void start() {
      System.out.println("Starting thread " + this.getName());
      super.start();
    }
  }

  private static class AscendingHackerThread extends HackerThread {
    public AscendingHackerThread(Vault vault) {
      super(vault);
    }

    @Override
    public void run() {
      for (int guess = 0; guess < MAX_PASSWORD; guess++) {
        if (vault.isPasswordCorrect(guess)) {
          System.out.println(this.getName() + " guessed the password " + guess);
          System.exit(0);
        }
      }
    }
  }

  private static class DescendingHackerThread extends HackerThread {
    public DescendingHackerThread(Vault vault) {
      super(vault);
    }

    @Override
    public void run() {
      for (int guess = MAX_PASSWORD; guess >= 0; guess--) {
        if (vault.isPasswordCorrect(guess)) {
          System.out.println(this.getName() + " guessed the password " + guess);
          System.exit(0);
        }
      }
    }
  }

  private static class PoliceThread extends Thread {
    @Override
    public void run() {
      for (int i = 10; i > 0; i--) {
        try {
          Thread.sleep(1000);
        } catch (Exception ignored) {
        }
        System.out.println(i);
      }
      System.out.println("Game over for you hackers");
      System.exit(0);
    }
  }
}
