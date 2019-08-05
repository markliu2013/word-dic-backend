package com.zfwhub.word.po;


import java.time.LocalDate;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.zfwhub.word.Application;
import com.zfwhub.word.po.Word;

// https://ajayiyengar.com/2017/07/08/testing-jpa-entities-in-a-spring-boot-application/
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(classes=Application.class)
@Transactional
public class EntitySchemaTest {
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Test
    @Rollback(false)
    public void initData() {
        
        
    }
    
    

}
