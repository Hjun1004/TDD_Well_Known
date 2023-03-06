package com.ll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;


import static com.ll.AppTestRunner.run;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTests {
    @Test
    @DisplayName("스캐너에 키보드가 아닌 문자열을 입력으로 설정")
    public void t1() {
        Scanner sc = TestUtil.genScanner("안녕");

        String cmd = sc.nextLine().trim();
        assertThat(cmd).isEqualTo("안녕");
    }

    @Test
    @DisplayName("출력을 모니터에 하지 않고 문자열로 얻기")
    public void t2() {
        // System.out 에 대한 화면출력 금지 시작
        ByteArrayOutputStream output = TestUtil.setOutToByteArray();

        System.out.print("안녕"); // 이 안녕은 화면에 출력되지 않는다.

        // 그 동안 출력되지 않던 내용들을 문자열로 반환 // 지금까지 안녕이 나오지 않았다.
        String rs = output.toString();

        // System.out 에 대한 화면출력 금지 끝
        TestUtil.clearSetOutToByteArray(output); //약간 sc.close 마냥 화면 출력 막아놨던걸 끄는기능

        assertThat(rs).isEqualTo("안녕");
    }
    @Test
    @DisplayName("프로그램 시작시 타이틀 출력 그리고 종료")
    public void t3() {

        String rs = AppTestRunner.run(" ");


        assertThat(rs)
                .contains("== 명언 앱 ==")
                .contains("명령) ")
                .contains("프로그램이 종료되었습니다.")
                .doesNotContain("올바르지 않은 명령입니다.");
    }

    @Test
    @DisplayName("잘못된 명령어 입력에 대한 처리")
    public void t4() {
        String rs = AppTestRunner.run("안녕\n종료2"); //이렇게 했을때는 \n이 단순 줄바꿈


        assertThat(rs)
                .contains("올바르지 않은 명령입니다.");
    }

    @Test
    @DisplayName("등록화면에서 명언과 작가를 입력받고 명언을 생성한다.")
    public void t5() {
        String rs = AppTestRunner.run(""" 
                등록
                현재를 사랑하라.
                작자미상
                """); // 이렇게 하면 \n이 엔터로 동작하는거 같다.

        assertThat(rs)
                .contains("명언 : ")
                .contains("작가 : ")
                .contains("1번 명언이 등록되었습니다.");
    }

    @Test
    @DisplayName("명언이 등록될 때 마다 생성되는 명언의 번호가 1씩 증가한다.")
    public void t6() {
        String rs = AppTestRunner.run(""" 
                등록
                현재를 사랑하라.
                작자미상
                등록
                이순신 짱
                이순신
                """); // 이렇게 하면 \n이 엔터로 동작하는거 같다.

        assertThat(rs)
                .contains("1번 명언이 등록되었습니다.")
                .contains("2번 명언이 등록되었습니다.")
                .doesNotContain("4번 명언이 등록되었습니다.");
    }

    @Test
    @DisplayName("목록을 입력하면 번호 / 작가 / 명언 이 나와야 한다.")
    public void t7() {
        String rs = AppTestRunner.run(""" 
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                목록
                """); // 이렇게 하면 \n이 엔터로 동작하는거 같다.

        assertThat(rs)
                .contains("번호 / 작가 / 명언")
                .contains("----------------------")
                .contains("2 / 작자미상 / 과거에 집착하지 마라.")
                .contains("1 / 작자미상 / 현재를 사랑하라.");
    }



}
