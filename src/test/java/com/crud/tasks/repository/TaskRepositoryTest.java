package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void testRepositorySave(){
        //Given
        taskRepository.deleteAll();
        Task task = new Task("Test Task","Test content");
        //Then
        taskRepository.save(task);
        int count = taskRepository.findAll().size();
        //When
        Assert.assertEquals(1,count);
        //CleanUp
        taskRepository.deleteById(task.getId());
    }

    @Test
    public void testDeleteById(){
        //Given
        Task task1 = new Task("Test Task","Test content");
        Task task2 = new Task("Test Task","Test content");
        //Then
        taskRepository.save(task1);
        taskRepository.save(task2);
        taskRepository.deleteById(task1.getId());
        int count = taskRepository.findAll().size();
        //When
        Assert.assertEquals(1,count);
        //CleanUp
        taskRepository.deleteById(task2.getId());
    }

    @Test
    public void testFindById(){
        //Given
        Task task1 = new Task("Test Task","Test content");
        //Then
        taskRepository.save(task1);
        String tittle =taskRepository.findById(task1.getId()).get().getTitle();
        //When
        Assert.assertEquals(tittle,task1.getTitle());
        //CleanUp
        taskRepository.deleteById(task1.getId());
    }

}