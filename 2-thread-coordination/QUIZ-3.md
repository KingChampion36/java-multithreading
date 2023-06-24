# Quiz: Thread Termination and Daemon Threads

**Question 1:**

Observe the following code:

```java
class Main {
  public static void main(String[] args) {
    Thread thread = new Thread(new WaitingForUserInput());
    thread.setName("InputWaitingThread");
    thread.start();
  }

  private static class WaitingForUserInput implements Runnable {
    @Override
    public void run() {
      try {
        while (true) {
          char input = (char) System.in.read();
          if (input == 'q') {
            return;
          }
        }
      } catch (IOException e) {
        System.out.println("An exception was caught " + e);
      }
    }
  }
}
```

Please choose the correct statement:

- [ ] We simply need to add `thread.interrupt();` in the end of the `main` method, which will stop the `InputWaitingThread` thread.
- [ ] We simply need to add `thread.interrupt();` in the end of the `main` method, which will stop the `InputWaitingThread` thread. And also add a new `catch` block in the `run` method inside the `WaitingForUserInput`, to handle an `InterruptedException`, and exit from the thread in that block of code.
- [x] The only ways to stop the application are: (1) For the user to type in the letter 'q'. (2) Set `thread.setDaemon(true);` in the main method, before starting the thread. (3) Forcefully kill the application.
- [ ] The application will stop immediately, since there is no `Thread.sleep()` call in the main method. And as soom as the `main` method exits, the application terminates.

**Question 2**

```java
class Main {
  public static void main(String [] args) {
    Thread thread = new Thread(new SleepingThread());
    thread.start();
    thread.interrupt();
  }

  private static class SleepingThread implements Runnable {
    @Override
    public void run() {
      while (true) {
        try {
          Thread.sleep(1000000);
        } catch (InterruptedException e) {
        }
      }
    }
  }
}
```

Please choose the correct statement:

- [ ] The application will stop anyway, when the main thread runs out of instructions to execute.
- [ ] The application will terminate as soon as the `thread.interrupt();` code is executed.
- [x] Without modifying the code, the application will not stop. We need to add a `return;` statement inside the `catch (InterruptedException e)` block to stop the application.
