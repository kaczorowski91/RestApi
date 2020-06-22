package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.exception.TaskNotFoundException;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbServiceTest {

    @Autowired
    private DbService dbService;

    @Autowired
    TaskRepository taskRepository;

    @Test
    public void getAllTasksTest(){
        //Given
        Task task = new Task("Test Task","Test content");
        //Then
        dbService.saveTask(task);
        int count = dbService.getAllTasks().size();
        //When
        Assert.assertEquals(1,count);
        //CleanUp
        dbService.deleteById(task.getId());
    }

    @Test
    public void testGetTask() throws TaskNotFoundException {
        //Given
        Task task1 = new Task("Test Task","Test content");
        //Then
        dbService.saveTask(task1);
        String tittle =dbService.getTask(task1.getId()).getTitle();
        //When
        Assert.assertEquals(tittle,task1.getTitle());
        //CleanUp
        dbService.deleteById(task1.getId());
    }



}