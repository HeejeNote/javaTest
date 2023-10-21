package com.example.javatest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class StudyTest {

    @Test
    @DisplayName("스터디 만들기")
    public void testStudy() {
        Study study = new Study(-10);

        assertNotNull(study);
        /**
         *  assertEquals :::
         *  assertEquals(기대값, 실제값, 테스트실패시 메시지)
         */
//        assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디 초기값은" + StudyStatus.DRAFT + "여야 한다.");

        // 위 테스트 코드는 아래 처럼 작성할 수 있다.
//        assertEquals(StudyStatus.DRAFT, study.getStatus(), new Supplier<String>() {
//            @Override
//            public String get() {
//                return "스터디 초기값은" + StudyStatus.DRAFT + "여야 한다.";
//            }
//        });

        /**
         * 차이점 :::
         * 1.assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디 초기값은" + StudyStatus.DRAFT + "여야 한다.");
         * -> 성공실패 여부와는 상관없이 테스트 실행시마다 문자열 연산을 진행
         * 2.assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디 초기값은" + StudyStatus.DRAFT + "여야 한다.");
         * -> 람다를 사용하게 되면 실패시에만 문자열 연산을 진행
         * -> 람다를 사용하면 (Lazy Evaluation) 지연 연산이 가능하기 떄문에 실패시에만 해당 연산하는 것이 가능하다.
         */

        // 위 테스트 코드를 다시 람다로 변환할 수 있다.
        assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디 초기값은" + StudyStatus.DRAFT + "여야 한다.");
        assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야합니다.");

    }

    @Test
    @DisplayName("테스트 검증 여러개 한번에 처리 방법 ::: 스터디 만들기 ")
    public void testAll() {
        // given
        // when
        Study study = new Study(2);
        System.out.println("study = " + study);
        // then
        assertAll(
            () -> assertNotNull(study, "스터디 객체는 null이 아니어야 한다."),
            () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디 초기값은" + StudyStatus.DRAFT + "여야 한다."),
            () -> assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 합니다.")
        );
    }

    @Test
    @DisplayName("예외 발생시키기")
    public void testException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        assertEquals("limit은 0보다 커야 한다.", exception.getMessage());
    }

    @Test
    public void testTimeOut() {
        // 0.1초안에 끊나지 않으면 에러 발생
        assertTimeout(Duration.ofMillis(50), () -> {
            new Study(10);
            Thread.sleep(100);
        }, "코드 실행 시간이 0.5초를 넘어서는 안된다.");
    }
}