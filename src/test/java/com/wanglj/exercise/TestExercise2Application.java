package com.wanglj.exercise;


import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Wanglj
 * @date 2021/8/2 23:28
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
/*
@SpringBootTest(classes = {ExerciseSpringBootApplication.class})
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)*/

public class TestExercise2Application {

    @Test
    @DisplayName("异常测试")
    public void exceptionTest() {
        ArithmeticException exception = Assertions.assertThrows(
                //扔出断言异常
                ArithmeticException.class, () -> System.out.println(1 % 1));

    }

    @Test
    @DisplayName("异常测试")
    void streamTest() {
        //ArrayList<Object> objects = new ArrayList<>();
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(1);
        objects.forEach(System.out::println);

    }

    @Test
    @DisplayName("超时测试")
    public void timeoutTest() {
        //如果测试方法时间超过1s将会异常
        Assertions.assertTimeout(Duration.ofMillis(1000), () -> Thread.sleep(500));
    }

    @Test
    void myFirstTest() {
        assertEquals(2, 1 + 1);
    }


    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3"})
        // @EnumSource(value = ActivityLimitEnum.class,names = {"LIMIT"})
        // @CsvFileSource(resources = "")  //csv文件入参
        //@MethodSource("method")
        //方法返回值入参
    void test(String connectionID) {
        System.out.println("connectionID is " + connectionID);
        Assertions.assertNotNull(connectionID);

    }

    @ParameterizedTest
    @CsvSource(value = {"1|2", "3|4"}, delimiter = '|')
    public void parameterizedCvsSourceTest(String fruit, int rank) {
        System.out.println(fruit);
        System.out.println(rank);
    }


    @RepeatedTest(10) //表示重复执行10次
    @DisplayName("重复测试")
    public void testRepeated() {
        Assertions.assertTrue(1 == 1);
        System.out.println(1);
    }


    static Stream<String> method() {
        return Stream.of("apple", "banana");
    }

/*    @BeforeEach
    @DisplayName("每条用例开始时执行")
    void start() {
        System.out.println("----start-----");
    }

    @AfterEach
    @DisplayName("每条用例结束时执行")
    void end() {
        System.out.println("----end-----");
    }*/

/*    @BeforeAll
    @DisplayName("每条用例开始时执行")
    static void startAll() {
        System.out.println("----startAll-----");
    }

    @AfterAll
    @DisplayName("每条用例结束时执行")
    static void endAll() {
        System.out.println("----endAll-----");
    }*/

    @Test
    @DisplayName("描述测试用例╯°□°）╯")
    void testWithDisplayName() {

    }

/*    @Test
    @Disabled("这条用例暂时跑不过，忽略!")
    void myFailTest(){
        assertEquals(1,2);
    }*/

    @Test
    @DisplayName("运行一组断言")
    public void assertAllCase() {
        assertAll("groupAssert",
                () -> assertEquals(2, 1 + 1),
                () -> assertTrue(1 > 0)
        );
    }

    @Test
    @DisplayName("依赖注入1")
    public void testInfo(final TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName());
    }

    @Test
    @DisplayName("依赖注入2")
    public void testReporter(final TestReporter testReporter) {
        testReporter.publishEntry("name", "Alex");
    }

    @Test
    @DisplayName("Nested")
    void isInstantiatedWithNew() {
        System.out.println("最一层--内嵌单元测试");
    }

    @Nested
    @DisplayName("Nested2")
    class Nested2 {

        @BeforeEach
        void Nested2_init() {
            System.out.println("Nested2_init");
        }

        @Test
        void Nested2_test() {
            System.out.println("第二层-内嵌单元测试");
        }


        @Nested
        @DisplayName("Nested3")
        class Nested3 {

            @BeforeEach
            void Nested3_init() {
                System.out.println("Nested3_init");
            }

            @Test
            void Nested3_test() {
                System.out.println("第三层-内嵌单元测试");
            }
        }
    }

}
