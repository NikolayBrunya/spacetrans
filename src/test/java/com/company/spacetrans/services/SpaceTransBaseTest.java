package com.company.spacetrans.services;

import io.jmix.core.DataManager;
import io.jmix.core.security.SystemAuthenticator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SpaceTransBaseTest {

    @Autowired
    SystemAuthenticator systemAuthenticator;

    @Autowired
    protected DataManager dataManager;

    protected List<Object> entitiesToRemove = new ArrayList<>();

    @BeforeEach
    void setUp() {

        systemAuthenticator.begin("admin");

    }

    @AfterEach
    void tearDown() {

        dataManager.remove(entitiesToRemove);

        systemAuthenticator.end();


    }
}
