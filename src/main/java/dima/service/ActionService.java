package dima.service;

import dima.entity.Task;

/**
 * Created by Dmitriy on 18.05.2018.
 */
public interface ActionService {
    void execution(String action, String taskName);
    void print(String name);
    void random(String name);
    void completed(String name);
    void delayed(String name);
}
