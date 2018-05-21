package dima.order;

import dima.service.ActionService;
import dima.service.ExecutionOrder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayDeque;


/**
 * Created by Dmitriy on 20.05.2018.
 */
public class Execute implements ExecutionOrder {
    @Autowired
    ActionService actionService;
    private int priority;

    private static ArrayDeque<String> inputParam = new ArrayDeque<String>();

    public static ArrayDeque<String> getInputParam() {
        return inputParam;
    }

    public static void setInputParam(ArrayDeque<String> inputParam) {
        Execute.inputParam = inputParam;
    }

    public Execute(int priority) {
        this.priority = priority;
    }

    Execute next;

    public void setNext(Execute next) {
        this.next = next;
    }

    @Override
    public void order(String action, String taskName, int level) {
        if (level <= priority) {
            synchronized (this) {
                actionService.execution(action, taskName);
            }

        } else {
            synchronized (this) {
                String actionInput = inputParam.pop();
                String nameInput = inputParam.pop();
                actionService.execution(actionInput, nameInput);
                String actionInputPar = inputParam.pop();
                String nameInputPar = inputParam.pop();
                Thread thr = new Thread(new Runnable() {
                    public void run() {
                        actionService.execution(actionInputPar, nameInputPar);
                    }
                });
                try {
                    thr.start();
                    thr.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
