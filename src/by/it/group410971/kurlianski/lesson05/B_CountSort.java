package by.it.group410971.kurlianski.lesson05;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class B_CountSort {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = B_CountSort.class.getResourceAsStream("dataB.txt");
        B_CountSort instance = new B_CountSort();
        int[] result = instance.countSort(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

    int[] countSort(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] points = new int[n];

        // Чтение входных данных
        for (int i = 0; i < n; i++) {
            points[i] = scanner.nextInt();
        }

        // Так как числа не превышают 10, создаем массив счетчиков размером 11
        // (индексы от 0 до 10)
        int[] count = new int[11];

        // Подсчет количества каждого числа
        for (int num : points) {
            count[num]++;
        }

        // Заполнение отсортированного массива
        int index = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                points[index++] = i;
                count[i]--;
            }
        }

        return points;
    }
}