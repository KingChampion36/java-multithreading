package exercise;

import java.util.ArrayList;
import java.util.List;

public class MultiExecutor {

  // Add any necessary member variables here
  private final List<Runnable> tasks;

  /*
   * @param tasks to executed concurrently
   */
  public MultiExecutor(List<Runnable> tasks) {
    this.tasks = tasks;
  }

  /**
   * Starts and executes all the tasks concurrently
   */
  public void executeAll() {
    List<Thread> threads = new ArrayList<>();
    tasks.forEach(task -> threads.add(new Thread(task)));
    threads.forEach((Thread::start));
  }
}
