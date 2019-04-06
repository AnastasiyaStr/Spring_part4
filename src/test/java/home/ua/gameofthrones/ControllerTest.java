package home.ua.gameofthrones;

import home.ua.gameofthrones.controller.CharacterController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTest {
    @Autowired
    private CharacterController controller;

    @Test
    public void contexLoads() throws Exception {
        assertNotNull(controller);
    }

}
