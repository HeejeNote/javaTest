package com.example.javatest;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

class JavaTestApplicationTests {

  @Test
  @DisplayName("테스트실행")
  public void test() {
      /**
       * @Test :::
       * 테스트 메서드를 실행한다.
       */
      System.out.println("test");
  }

  @Test
  @Disabled
  @DisplayName("테스트다시실행")
  public void testAgain() {
      /**
       *  @Disabled :::
       *  테스트 메서드를 실행하지 않는다.
       */
      System.out.println("test1");
  }

  @BeforeAll
  static void beforeAll(){
      /**
       * @BeforeAll :::
       * 모든 테스트가 실행되기 이전에 한번 실행
       */
      System.out.println("beforeAll");
  }

  @AfterAll
  static void afterAll(){
      /**
       * @AfterAll :::
       * 모든 테스트가 실행된 후에 한번 실행
       */
      System.out.println("afterAll");
  }

  @BeforeEach
  void beforeEach(){
      /**
       * @BeforeEach :::
       * 각 테스트가 실행되기 이전에 실행
       */
      System.out.println("beforeEach");
  }

  @AfterEach
  void afterEach(){
      /**
       * @AfterEach :::
       * 각 테스트가 실행된 후에 실행
       */
      System.out.println("afterEach");
  }

}
