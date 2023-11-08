package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    Scanner scanner;
    int setNumber;
    List<SetNumber3> setNumberArray;

    App() {
        scanner = new Scanner(System.in);
        setNumber = 0;
        setNumberArray = new ArrayList<>();
    }

    void run() {
        System.out.println("== 명언 앱 ==");


        while (true) {
            System.out.print("명령) ");
            String line = scanner.nextLine();

            Rq rq = new Rq(line);


            if (line.equals("종료")) {
                break;
            } else if (line.equals("등록")) {
                actionWrite();
            } else if (line.equals("목록")) {
                actionList();
            } else if (line.startsWith("삭제?")) {
                actionRemove(line);
            }
        }
    }


    void actionWrite() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();

        System.out.print("작가 : ");
        String author = scanner.nextLine();

        setNumber++;
        int id = setNumber;

        SetNumber3 setNumber3 = new SetNumber3(id, content, author);
        setNumberArray.add(setNumber3);

        System.out.printf("%d번 명언이 등록되었습니다.\n", setNumber);


    }

    void actionList() {
        System.out.println("번호 / 작가 / 명언" + "\n ------------------");

        if (setNumberArray.isEmpty())
            System.out.println("등록된 명언이 없습니다.");

        for (int i = setNumberArray.size() - 1; i >= 0; i--) {
            SetNumber3 setNumber3 = setNumberArray.get(i);
            System.out.printf("%d / %s / %s\n", setNumber3.id, setNumber3.author, setNumber3.content);

        }


    }

    void actionRemove(String line) {
        String[] lineBits = line.split("\\?", 2);
        String action = lineBits[0];
        String queryString = lineBits[1];

        String[] queryStringBits = queryString.split("&");

        int id = 0;

        for (int i = 0; i < queryStringBits.length; i++) {
            String queryParamStr = queryStringBits[i];
            String[] queryParamStrBits = queryParamStr.split("=", 2);
            String paramName = queryParamStrBits[0];
            String paramValue = queryParamStrBits[1];

            if(paramName.equals("id")){
                id = Integer.parseInt(paramValue);
            }
        }

        System.out.printf("%d번 명언을 삭제합니다.\n", id);
    }
}