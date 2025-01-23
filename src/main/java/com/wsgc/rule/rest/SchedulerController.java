package com.wsgc.rule.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wsgc.rule.impl.SchedulerService;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class SchedulerController {

    private final SchedulerService schedulerService;
    private final ScheduledAnnotationBeanPostProcessor postProcessor;
    private final ObjectMapper objectMapper;
    private static final String SCHEDULER_SERVICE_NAME = "schedulerService";

    public SchedulerController(SchedulerService schedulerService,
                               ScheduledAnnotationBeanPostProcessor postProcessor,
                               ObjectMapper objectMapper)
    {
        this.schedulerService = schedulerService;
        this.postProcessor = postProcessor;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/stopScheduler")
    public String stopScheduler()
    {
        postProcessor.postProcessBeforeDestruction(schedulerService, SCHEDULER_SERVICE_NAME);
        return "OK";
    }

    @GetMapping(value = "/startScheduler")
    public String startSchedule(){
        postProcessor.postProcessAfterInitialization(schedulerService, SCHEDULER_SERVICE_NAME);
        return "OK";
    }

    @GetMapping(value = "/listScheduler")
    public String listSchedules() throws JsonProcessingException
    {
        Set<ScheduledTask> setTasks = postProcessor.getScheduledTasks();
        if(!setTasks.isEmpty()){
            return objectMapper.writeValueAsString(setTasks);
        }else{
            return "No running tasks !";
        }
    }

}
