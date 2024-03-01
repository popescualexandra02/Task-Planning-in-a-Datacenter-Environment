/* Implement this class. */

import java.util.List;

public class MyDispatcher extends Dispatcher {

    public MyDispatcher(SchedulingAlgorithm algorithm, List<Host> hosts) {
        super(algorithm, hosts);
    }
    int Host_ID = 0;
    @Override
    public synchronized void addTask(Task task) {
        if(algorithm.equals(SchedulingAlgorithm.ROUND_ROBIN)){
            hosts.get(( Host_ID + 1) % hosts.size()).addTask(task);
            Host_ID = ( Host_ID + 1) % hosts.size();
        }
        if(algorithm.equals(SchedulingAlgorithm.SHORTEST_QUEUE)){
            int min = hosts.get(0).getQueueSize();
            for(Host h : hosts){
                if(min >= h.getQueueSize()){
                    min = h.getQueueSize();
                }
            }
            for(int i = 0; i < hosts.size(); i++){
                if(hosts.get(i).getQueueSize() == min){
                    hosts.get(i).addTask(task);
                    break;
                }
            }
        }
        if(algorithm.equals(SchedulingAlgorithm.SIZE_INTERVAL_TASK_ASSIGNMENT)){
            if(task.getType().equals(TaskType.SHORT)){
                hosts.get(0).addTask(task);
            }
            if(task.getType().equals(TaskType.MEDIUM)){
                hosts.get(1).addTask(task);
            }
            if(task.getType().equals(TaskType.LONG)){
                hosts.get(2).addTask(task);
            }
        }
        if(algorithm.equals(SchedulingAlgorithm.LEAST_WORK_LEFT)){
            long min = hosts.get(0).getWorkLeft();
            for(Host h : hosts){
                if(min >= h.getWorkLeft()){
                    min = h.getWorkLeft();
                }
            }
            for(int i = 0; i < hosts.size(); i++){
                if(hosts.get(i).getWorkLeft() == min){
                    hosts.get(i).addTask(task);
                    break;
                }
            }
        }
    }
}
