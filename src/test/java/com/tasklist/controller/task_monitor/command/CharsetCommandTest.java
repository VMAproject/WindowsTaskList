package com.tasklist.controller.task_monitor.command;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.nio.charset.Charset;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/beans.xml")
public class CharsetCommandTest {

    @Autowired
    private Command<Charset> charsetCommand;

    @Test
    public void testRun() throws Exception {
        charsetCommand.init();
        Charset charset = charsetCommand.execute();
        assertEquals("Wrong charset", "IBM866", charset.toString());
    }
}