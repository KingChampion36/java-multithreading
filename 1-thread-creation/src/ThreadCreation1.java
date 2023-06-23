public class ThreadCreation1 {
  public static void main(String[] args) throws InterruptedException {

    // We can pass an object of a class that implements the Runnable interface into the thread's constructor
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        // Code that will run in a new thread
        System.out.println("We are now in a new thread " + Thread.currentThread().getName());
        // Get priority of the thread
        System.out.println("Current thread priority is " + Thread.currentThread().getPriority());
      }
    });

    Thread misbehavingThread = new Thread(new Runnable() {
      @Override
      public void run() {
        throw new RuntimeException("Intentional Exception");
      }
    });

    // JVM by default sets a very unhelpful name of the thread
    // We can provide a custom name to our threads
    thread.setName("New Worker Thread");

    // We can also set the priority of the thread ourselves
    // Thread.NORM_PRIORITY is the default value
    thread.setPriority(Thread.MAX_PRIORITY);

    System.out.println("We are in thread: " + Thread.currentThread().getName() + " before starting a new thread");

    // Start the thread
    thread.start();

    System.out.println("We are in thread: " + Thread.currentThread().getName() + " before starting a new thread");

    misbehavingThread.setName("Misbehaving Thread");

    // This will be called when an exception is thrown inside a thread
    // This is a place where we can clear resources and/or log additional informations
    misbehavingThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
      @Override
      public void uncaughtException(Thread t, Throwable e) {
        System.out.println("A critical error happened in thread " + t.getName() + " the error is " + e.getMessage());
      }
    });

    misbehavingThread.start();

    // Don't execute the thread until the given time passes
    // During this time, thread doesn't consume any memory
    // This line stops the main thread for 10 seconds
    Thread.sleep(10000);
  }
}
