package com.ll.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private final Scanner scanner;
    private int setNumber;
    private final List<SetNumber3> setNumberArray;

    public App() {
        scanner = new Scanner(System.in);
        setNumber = 0;
        setNumberArray = new ArrayList<>();
    }

    public void run() {
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

    private void actionWrite() {
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

    private void actionList() {
        System.out.println("번호 / 작가 / 명언" + "\n ------------------");

        if (setNumberArray.isEmpty()) System.out.println("등록된 명언이 없습니다.");

        for (int i = setNumberArray.size() - 1; i >= 0; i--) {
            SetNumber3 setNumber3 = setNumberArray.get(i);
            System.out.printf("%d / %s / %s\n", setNumber3.getId(), setNumber3.getAuthor(), setNumber3.getContent());

        }
    }

    private void actionRemove(Rq rq) {
        int id = rq.getParamAsInt("id", 0);

        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요");
            return;
        }


        int index = getIndexofsetNumberArrayById(id);

        if (index == -1) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return; // 리턴 빼서 오류남
        }

        setNumberArray.remove(index);

        System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
    }

    private int getIndexofsetNumberArrayById(int id) {
        for (int i = 0; i < setNumberArray.size(); i++) {
            SetNumber3 setNumber3 = setNumberArray.get(i);

            if (setNumber3.getId() == id) {
                return i;
            }
        }
        return -1;
    }

    private void actionModify(Rq rq) {
        int id = rq.getParamAsInt("id", 0);

        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return;
        }

        int index = getIndexofsetNumberArrayById(id);

        if (index == -1) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }
        SetNumber3 setNumber3 = setNumberArray.get(index);
        System.out.printf("명언(기존): %s\n", setNumber3.getContent());
        System.out.print("명언 : ");
        String content = scanner.nextLine();

        System.out.printf("작가(기존): %s\n", setNumber3.getAuthor());
        System.out.print("작가 : ");
        String author = scanner.nextLine();

        setNumber3.setContent(content);
        setNumber3.setAuthor(author);

        System.out.printf("%d번 명언이 수정되었습니다.\n", id);
    }

}
