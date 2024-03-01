/* Implement this class. */

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

class NumberComparator implements Comparator<Task> {
    @Override
    public int compare(Task num1, Task num2){
        if(num2.getPriority() == num1.getPriority()){
            return num1.getStart() - num2.getStart();
        }
        return num2.getPriority() - num1.getPriority();
    }
}
public class MyHost extends Host {
    PriorityBlockingQueue<Task> coada_taskuri = new PriorityBlockingQueue<>(1,new NumberComparator());
    Task task_now ;
    boolean run_now = true;
    int yes = 0;
    @Override
    public void run() {
        while(run_now) {
            if(!coada_taskuri.isEmpty()){
                synchronized (this){
                    task_now = coada_taskuri.peek();
                }
                while(task_now.getLeft() > 0) {
                    yes = 1;
                    if (yes == 1) {
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    task_now.setLeft(task_now.getLeft() - 1000);
                    if (task_now.isPreemptible()) {
                        if (task_now.getLeft() <= 0) {
                            task_now.finish();
                            coada_taskuri.remove(task_now);
                        } else {
                            task_now = coada_taskuri.peek();
                            yes = 0;
                        }
                    } else {
                        if (task_now.getLeft() <= 0) {
                            task_now.finish();
                            coada_taskuri.remove(task_now);
                        }
                    }
                }

            }
        }
    }


    @Override
    public synchronized void addTask(Task task) {
        coada_taskuri.add(task);
    }

    @Override
    public int getQueueSize() {
        return coada_taskuri.size();
    }

    @Override
    public long getWorkLeft() {
        long timp_ramas = 0;
        for(Task task_now: coada_taskuri){
            timp_ramas += task_now.getLeft();
        }
        return timp_ramas;
    }

    @Override
    public void shutdown() {
        run_now = false;
    }
}
