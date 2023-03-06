package com.ll;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

public class AppTestRunner {
    public static String run(String cmd) {
        cmd.stripIndent().trim(); // 이걸 안해주면 안녕, 종료가 안들어가서 오류가 자꾸 발생함
        cmd += "\n종료"; //테스트할 때는 항상 종료로 끝나야 하기 때문에

        Scanner sc = TestUtil.genScanner(cmd); // stripIndent로 인해서 안녕, 종료가 입력되는 것이다.
        //.stripIndent().trim())

        ByteArrayOutputStream output = TestUtil.setOutToByteArray();

        new App(sc).run();

        String rs = output.toString();

        TestUtil.clearSetOutToByteArray(output);

        return rs;
    }
}
