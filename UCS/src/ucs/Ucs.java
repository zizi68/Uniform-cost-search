/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucs;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Ucs {

    public static void main(String[] args) {
        int maTranKe[][];
        int soDinh;
        int source = 0;
        int destination = 0;
        int chiPhi;
        Scanner scan = new Scanner(System.in);
        try {
            System.out.print("Nhập số đỉnh của đồ thị: ");
            soDinh = scan.nextInt();

            maTranKe = new int[soDinh][soDinh];
            System.out.println("Nhập ma trận trọng số của đồ thị: ");
            for (int i = 0; i < soDinh; i++) {
                for (int j = 0; j < soDinh; j++) {
                    maTranKe[i][j] = scan.nextInt();
                    if (i == j) {
                        maTranKe[i][j] = 0;
                        continue;
                    }
                    if (maTranKe[i][j] == 0) {
                        maTranKe[i][j] = UniformCostSearch.MAX_VALUE;
                    }
                }
            }

            System.out.println("\nMa trận trọng số: ");
            System.out.print("     ");
            for (int i = 0; i < soDinh; i++) {
                System.out.printf("%-5s", i + 1);
            }
            System.out.println("");
            for (int i = 0; i < soDinh; i++) {
                System.out.printf("%-5s", i + 1);
                for (int j = 0; j < soDinh; j++) {
                    if (maTranKe[i][j] == 0 || maTranKe[i][j] == UniformCostSearch.MAX_VALUE) {
                        System.out.printf("%-5s", "oo");
                    } else {
                        System.out.printf("%-5s", maTranKe[i][j]);
                    }
                }
                System.out.println("\n");
            }

            do {
                System.out.print("Nhập đỉnh nguồn: ");
                source = scan.nextInt();
                if (source < 1 || source > soDinh) {
                    System.out.println("Đỉnh không tồn tại! Mời nhập lại!");
                }
            } while (source < 1 || source > soDinh);
            System.out.println("");
            do {
                System.out.print("Nhập đỉnh đích: ");
                destination = scan.nextInt();
                if (destination < 1 || destination > soDinh) {
                    System.out.println("Đỉnh không tồn tại! Mời nhập lại!");
                }
            } while (destination < 1 || destination > soDinh);
            System.out.println("");
            UniformCostSearch uniformCostSearch = new UniformCostSearch(soDinh);
            chiPhi = uniformCostSearch.uniformCostSearch(maTranKe, source - 1, destination - 1);
            uniformCostSearch.printPath();

            System.out.println("\nChi phí từ đỉnh " + source + " đến đỉnh " + destination + " tương ứng với đường đi trên là " + chiPhi);

        } catch (InputMismatchException inputMismatch) {
            System.out.println("Nhập sai định dạng đầu vào!");
        }
        scan.close();
    }
}
