package tests;

import org.testng.annotations.*;

public class BaseTest {

    @BeforeGroups(groups = {"smoke"})
    public void setUp() {
        System.out.println("starting tests");
    }
}
