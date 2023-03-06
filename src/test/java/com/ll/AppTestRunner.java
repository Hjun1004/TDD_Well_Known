package com.ll;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppTestRunner {
    public static String run(String cmd) {
        cmd.stripIndent().trim(); // 이걸 안해주면 안녕, 종료가 안들어가서 오류가 자꾸 발생함
        cmd += "\n종료"; //테스트할 때는 항상 종료로 끝나야 하기 때문에



        Scanner sc = TestUtil.genScanner(cmd); // stripIndent로 인해서 안녕, 종료가 입력되는 것이다.
        //.stripIndent().trim())

        ByteArrayOutputStream output = TestUtil.setOutToByteArray();

        new App(sc).run();

        String rs = output.toString(); //App클래스에서 실행하면서 출력되어야 할 결과 문자열들을
        //출력하지 않고 rs에 저장한다.

        TestUtil.clearSetOutToByteArray(output); //출력 금지 중지 -> 이제 화면에 출력이 되게한다.

        System.out.println(rs);
        return rs;
    }
}
