package dima.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import dima.entity.Pipeline;
import dima.entity.Task;
import dima.entity.enums.DoAction;
import dima.entity.enums.PipelineStatus;
import dima.entity.enums.TaskStatus;
import dima.order.Execute;
import dima.service.PipelineService;
import dima.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.hibernate.internal.util.ConfigHelper.getResourceAsStream;

/**
 * Created by Dmitriy on 18.05.2018.
 */
@Controller
public class ServerController {

    @Autowired
    private PipelineService pipelineService;
    @Autowired
    private TaskService taskService;

    private static final Logger log = Logger.getLogger(ServerController.class);

    @RequestMapping(
            value = "/",
            consumes = "text/json",
            produces = MediaType.TEXT_PLAIN_VALUE,
            method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void handleRequest(@RequestBody String file) throws IOException {
        Date dd = new Date();
        SimpleDateFormat stf = new SimpleDateFormat("yy:MM:dd: HH:mm:ss");
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        JsonNode root = mapper.readTree(getResourceAsStream(file));
        JsonNode taskNode = root.path("tasks");
        JsonNode transitionsNode = root.path("transitions");
        List<Task> list = new ArrayList<>();
        Task task = null;
        try {
            String name = root.path("name").asText();
            String description = root.path("description").asText();
            Pipeline pipeline = new Pipeline(name, description, list, PipelineStatus.PENDING);
            pipelineService.createPipeline(pipeline);
            for (JsonNode node : taskNode) {
                String nameT = node.path("name").asText();
                String descriptionT = node.path("description").asText();
                String action = node.path("action").asText();
                task = new Task(nameT, descriptionT, DoAction.valueOf(action.toUpperCase()),
                        TaskStatus.PENDING, pipeline);
                list.add(task);
                taskService.createTask(task);
            }
            ArrayList<String> transitions = new ArrayList<>();
            HashMap<String, Integer> hm = new HashMap<>();
            for (JsonNode node : transitionsNode) {
                String from = node.path("from").asText();
                String to = node.path("to").asText();
                transitions.add(from);
                transitions.add(to);
                hm.put(from, 1);
                hm.put(to, 2);
            }
            log.info("ExecutionId " + pipeline.getId());
            log.info("pipeline " + pipeline.getName());
            log.info("status " + pipeline.getPipelineStatus());
            log.info("Start time " + stf.format(dd));

            Execute ex = new Execute(1);
            ArrayDeque<String> inputParam = new ArrayDeque<String>();
            for (int i = 0; i < transitions.size(); i++) {
                Task taskE = taskService.getTaskByName(transitions.get(i));
                if (taskE.getTaskStatus().equals(TaskStatus.FAILED)) {
                    break;
                }
                if (hm.get(transitions.get(i)) == 2) {
                    inputParam.addFirst(taskE.getName());
                    inputParam.addFirst(taskE.getAction().toString());
                    if (inputParam.size() == 4) {
                        Execute.setInputParam(inputParam);
                        ex.order(taskE.getAction().toString(), taskE.getName(), hm.get(transitions.get(i)));
                    }
                } else {
                    ex.order(taskE.getAction().toString(), taskE.getName(), hm.get(transitions.get(i)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

