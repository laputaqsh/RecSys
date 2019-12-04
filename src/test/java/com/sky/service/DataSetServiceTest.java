package com.sky.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSetServiceTest {

    private DataSetService service;

    @Autowired
    public void setService(DataSetService service) {
        this.service = service;
    }

    @Test
    public void writeDataSet() {
        service.writeDataSet();
    }
}
