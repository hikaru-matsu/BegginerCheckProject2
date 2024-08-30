package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StudentManagement {
  private static List<Student> students = new ArrayList<>();

  public static void ma in(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int choice;

    while (true) {
      System.out.println("\n1. 学生を追加");
      System.out.println("2. 学生を削除");
      System.out.println("3. 点数を更新");
      System.out.println("4. 平均点を計算");
      System.out.println("5. 全学生の情報を表示");
      System.out.println("6. 終了");
      System.out.println("選択してください:");
      choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 1:
          addStudent(scanner);
          break;
        case 2:
          removeStudent(scanner);
          break;
        case 3:
          updateScore(scanner);
          break;
        case 4:
          calculateAverageScore();
          break;
        case 5:
          displayAllStudents();
          break;
        case 6:
          System.out.println("プログラムを終了します。");
          scanner.close();
          return;
        default:
          System.out.println("無効な選択です。もう一度お試しください。");
      }
    }
  }

  private static void addStudent(Scanner scanner) {
    System.out.print("学生の名前を入力してください:");
    String name = scanner.nextLine();
    System.out.print(name + "の点数を入力してください:");
    int score = scanner.nextInt();
    scanner.nextLine();

    students.add(new Student(name, score));
    System.out.println(name + "を追加しました。");
  }

  private static void removeStudent(Scanner scanner) {
    System.out.print("削除する学生の名前を入力してください。");
    String name = scanner.nextLine();

    students = students.stream()
        .filter(student -> !student.getName().equals(name))
        .collect(Collectors.toList());
        System.out.println(name + "を削除しました。");
  }

  private static void updateScore(Scanner scanner) {
    System.out.print("点数を更新する学生の名前を入力してください:");
    String name = scanner.nextLine();
    System.out.print("新しい点数を入力してください:");
    int newScore = scanner.nextInt();
    scanner.nextLine();

    for (Student student : students) {
      if(student.getName().equals(name)) {
        student.setScore(newScore);
        System.out.println(name + "の点数を" + newScore + "に更新しました。");
        return;
      }
    }
    System.out.println("学生が見つかりませんでした。");
  }

  private static void calculateAverageScore() {
    double average = students.stream()
                              .mapToInt(Student::getScore)
                              .average()
                              .orElse(0.0);
    System.out.println("平均点：" + average + "点");
  }

  private static void displayAllStudents() {
  System.out.println("学生一覧：");
  for (Student student : students) {
    System.out.println(student.getName() + ": " + student.getScore() + "点");
    }
  }
}

class Student {
  private String name;
  private int score;

  public Student(String name, int score) {
    this.name = name;
    this.score = score;
  }

  public String getName() {
    return name;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }
}