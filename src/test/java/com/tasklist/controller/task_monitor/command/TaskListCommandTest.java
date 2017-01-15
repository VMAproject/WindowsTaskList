package com.tasklist.controller.task_monitor.command;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/beans.xml")
public class TaskListCommandTest {

    @Autowired
    private Command<List<String>> command;

    @Test
    public void testRun() throws Exception {
        List<String> lines = command.execute();
        assertNotNull(lines);
        assertTrue(lines.size() >= 4);
    }
}