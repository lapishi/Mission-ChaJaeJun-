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

            switch (rq.getAction()) {
                case "종료":
                    return;
                case "등록":
                    actionWrite();
                    break;
                case "목록":
                    actionList();
                    break;
                case "삭제":
                    actionRemove(rq);
                    break;
                case "수정":
                    actionModify(rq);
                    break;
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

        if (setNumberArray.isEmpty()) System.out.println("등록된 명언이 없습니다.");

        for (int i = setNumberArray.size() - 1; i >= 0; i--) {
            SetNumber3 setNumber3 = setNumberArray.get(i);
            System.out.printf("%d / %s / %s\n", setNumber3.id, setNumber3.author, setNumber3.content);

        }
    }

    void actionRemove(Rq rq) {
        int id = rq.getParamAsInt("id", 0);

        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요");
            return;
        }
        System.out.printf("%d번 명언을 삭제합니다.\n", id);
    }

    void actionModify(Rq rq) {
        int id = rq.getParamAsInt("id", 0);

        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return;
        }
        System.out.printf("%d번 명언을 수정합니다.\n", id);
    }

}