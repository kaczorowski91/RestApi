package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import com.crud.tasks.service.TrelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    private static final String SUBJECT = "Tasks: Once a day email";

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    TrelloService trelloService;

    //@Scheduled(fixedDelay = 10000)
    //@Scheduled(cron = "0 0 10 * * * ")
    public void sendInformationEmail() {
        long size = taskRepository.count();
        String taskAmount = " task";
        if (size > 1) {
            taskAmount = " tasks";
        }
        simpleEmailService.send(new Mail(adminConfig.getAdminMail(),
                SUBJECT, "Currenty in database got: " + size + taskAmount, null));
    }

    @Scheduled(cron = "0 0 10 * * * ")
    //@Scheduled(fixedDelay = 10000)
    public void sendInformationEmailWithTemplates() {
        trelloService.sendTaskCount();
    }

}