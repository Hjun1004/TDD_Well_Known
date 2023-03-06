package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private final Scanner sc;
    public App(Scanner sc){
        this.sc = sc;
    }

    void run(){
        System.out.println("== 명언 앱 ==");
        List<Wise_Saying> al = new ArrayList<>();

        long lastWiseSayingId=0;
        while(true){
            System.out.print("명령) ");

            String cmd = sc.nextLine().trim();
            //System.out.println("cmd = " + cmd);
            if (cmd.isEmpty()) {
                System.out.println("넘어간다.");
                continue;
            }


            switch (cmd) {
                case "종료":
                    System.out.println("프로그램이 종료되었습니다.");
                    return;

                case "등록":

                    System.out.print("명언 : ");
                    String content = sc.nextLine().trim();
                    //System.out.println("content = " + content);
                    System.out.print("작가 : ");
                    String authorName = sc.nextLine().trim();
                    //System.out.println("authorName = " + authorName);

                    long id = ++lastWiseSayingId;

                    al.add(new Wise_Saying(id, content, authorName));

                    System.out.printf("%d번 명언이 등록되었습니다.", id);
                    break;

                case "목록":
                    System.out.println("번호 / 작가 / 명언");
                    System.out.println("----------------------");
                    for(int i = al.size() ; i > 0 ; i--){
                        String contents = al.get(i-1).getContent();
                        String authorNames = al.get(i-1).getAuthorName();
                        long list_Id = al.get(i-1).getId();

                        System.out.printf("%d / %s / %s",list_Id, authorNames, contents);
                    }
                    break;

                default:
                    System.out.printf("`%s`(은)는 올바르지 않은 명령입니다.\n", cmd);
                    break;
            }
        }


    }
}
