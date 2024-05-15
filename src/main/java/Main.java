import java.util.Scanner;
public class Main {
  public static char nf = '□';
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int a;
    //firstDgt и secondDgt отвечает за цифру при поставлении в зону, используя функцию
    int firstDgt;
    int secondDgt;
    boolean isX = true;
    char[][] zone = {
      {nf, nf, nf}, 
      {nf, nf, nf}, 
      {nf, nf, nf}
    };
    //Основной цикл
    while (true) {
      System.out.print("\033[H\033[2J");
    printZone(zone);
      System.out.print("\u001B[33m");
      System.out.print(isX ? "Введите координаты клетки, чтобы поставить крестик: " :  "Введите координаты клетки, чтобы поставить нолик: ");
      System.out.print("\u001B[37m");
      a = sc.nextInt();
      firstDgt = parsePut(a)[0];
      secondDgt = parsePut(a)[1]; 
      //Проверка на свободность клетки
        if (zone[firstDgt - 1][secondDgt - 1] != nf) {
            System.out.println("Эта клетка уже занята");
          wait(1000);
        } else {
            if (isX) {
                zone[secondDgt - 1][firstDgt - 1] = 'x';
            } else {
                zone[secondDgt - 1][firstDgt - 1] = 'o';
            }
        }
      //Проверка на победу
      if (isWin(zone)) {
        System.out.println(isX ? "Победили крестики" : "Победили нолики");
        break;
      }
      //Проверка на ничью
      if (isNoWin(zone)) {
        System.out.println("Ничья");
        break;
      }
    isX = !isX;
    }
  }
  //Функция для вывода поля
  public static void printZone(char[][] toPrint) {
    for (int i = 0; i < toPrint.length; i++) {
      for (int j = 0; j < toPrint[i].length; j++) {
        System.out.print(toPrint[i][j]);
        System.out.print(" ");
      }
      System.out.println();
    }
  }
  public static int[] parsePut (int put) {
    int[] parsed = { put / 10, put % 10 };
    return parsed;
  }
  //Функция для проверки на победу
  public static boolean isWin(char[][] z) {
    boolean win = false;
    for (int i = 0; i < 3; i++) {
    if (z[i][0] == z[i][1] && z[i][1] == z[i][2] && z[i][0] != nf) {
      win = true;
    } else if (z[0][i] == z[1][i] && z[1][i] == z[2][i] && z[0][i] != nf) {
      win = true;
    } else if ((z[0][0] == z[1][1] && z[1][1] == z[2][2] && z[0][0] != nf) || (z[0][2] == z[1][1] && z[1][1] == z[2][0] && z[0][2] != nf)) {
      win = true;
    } else {
      win = false;
    }
  }
    return win;
  }
  public static void wait(int time) {
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  //Функция проверки на ничью
  public static boolean isNoWin(char[][] p) {
    return p[0][0] != nf && p[0][1] != nf && p[0][2] != nf && p[1][0] != nf && p[1][1] != nf && p[1][2] != nf && p[2][0] != nf && p[2][1] != nf && p[2][2] != nf;
  }
}