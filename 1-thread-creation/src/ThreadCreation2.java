public class ThreadCreation2 {
  public static void main(String[] args) {
    Thread thread = new NewThread();
    thread.start();
  }

  // A better way to create thread is to create a new class which Extends Thread
  private static class NewThread extends Thread {
    @Override
    public void run() {
      // Code that executes on the new thread
      // Instead of using static Thread.currentThread(), we can simply use "this"
      System.out.println("Hello from " + this.getName());
    }
  }
}
