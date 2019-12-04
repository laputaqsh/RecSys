package com.sky.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecServiceTest {

    private RecService service;

    @Autowired
    public void setService(RecService service) {
        this.service = service;
    }

    @Test
    public void saveRecRes() {
        service.saveRecRes();
    }
}
