package by.it.group410971.kurlianski.lesson06;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class B_LongDivComSubSeq {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = B_LongDivComSubSeq.class.getResourceAsStream("dataB.txt");
        B_LongDivComSubSeq instance = new B_LongDivComSubSeq();
        int result = instance.getDivSeqSize(stream);
        System.out.print(result);
    }

    int getDivSeqSize(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] sequence = new int[n];

        // Чтение последовательности
        for (int i = 0; i < n; i++) {
            sequence[i] = scanner.nextInt();
        }

        // Массив для хранения длин подпоследовательностей, заканчивающихся в каждой позиции
        int[] dp = new int[n];
        int maxLength = 1;

        // Инициализация: минимальная длина подпоследовательности для каждого элемента - 1
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        // Заполнение массива dp
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (sequence[i] % sequence[j] == 0 && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    if (dp[i] > maxLength) {
                        maxLength = dp[i];
                    }
                }
            }
        }

        return maxLength;
    }
}