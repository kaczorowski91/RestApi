package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void mapToTaskDtoTest(){
        //Given
        Task task = new Task(1L,"Test task", "Test content");
        //Then
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //When
        Assert.assertEquals(task.getId(),taskDto.getId());
        Assert.assertEquals(task.getTitle(),taskDto.getTitle());
        Assert.assertEquals(task.getContent(),task.getContent());
    }

    @Test
    public void mapToTaskTest(){
        //Given
        TaskDto taskDto = new TaskDto(1L,"Test task", "Test content");
        //Then
        Task task = taskMapper.mapToTask(taskDto);
        //When
        Assert.assertEquals(task.getId(),taskDto.getId());
        Assert.assertEquals(task.getTitle(),taskDto.getTitle());
        Assert.assertEquals(task.getContent(),task.getContent());
    }

    @Test
    public void mapToTaskDtoList(){
        //Given
        Task task1 = new Task(1L,"Test task 1", "Test content 1");
        Task task2 = new Task(2L,"Test task 2", "Test content 2");
        Task task3 = new Task(3L,"Test task 3", "Test content 3");
        List<Task>taskList= new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        //Then
        List<TaskDto>taskDtoList=taskMapper.mapToTaskDtoList(taskList);
        int count =taskList.size();
        //When
        Assert.assertEquals(count,taskDtoList.size());
        Assert.assertEquals(taskList.get(0).getTitle(),taskDtoList.get(0).getTitle());
        Assert.assertEquals(taskList.get(1).getId(),taskDtoList.get(1).getId());
        Assert.assertEquals(taskList.get(2).getContent(),taskDtoList.get(2).getContent());
    }

}