package homework;

import homework.annotations.After;
import homework.annotations.Before;
import homework.annotations.Test;

public class HomeworkTest {

    @Before
    public void doBefore() {
        System.out.println("Before");
    }

    @Test
    public void test1() {
        System.out.println("Test1");
    }

    @Test
    public void test2() {
        System.out.println("Test2");
    }

    @Test
    public void test3() {
        throw new RuntimeException();
    }

    @After
    public void doAfter() {
        System.out.println("After");
    }
}
