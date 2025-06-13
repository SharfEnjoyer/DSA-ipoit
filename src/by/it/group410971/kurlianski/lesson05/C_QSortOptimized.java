package by.it.group410971.kurlianski.lesson05;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class C_QSortOptimized {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = C_QSortOptimized.class.getResourceAsStream("dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result = instance.getAccessory2(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Segment[] segments = new Segment[n];
        int[] points = new int[m];
        int[] result = new int[m];

        // Чтение отрезков
        for (int i = 0; i < n; i++) {
            int start = scanner.nextInt();
            int stop = scanner.nextInt();
            segments[i] = new Segment(Math.min(start, stop), Math.max(start, stop));
        }

        // Чтение точек
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }

        // Оптимизированная сортировка отрезков
        quickSort(segments, 0, segments.length - 1);

        // Для каждой точки находим количество покрывающих отрезков
        for (int i = 0; i < m; i++) {
            int point = points[i];
            // Бинарный поиск первого подходящего отрезка
            int first = findFirstSegment(segments, point);
            if (first == -1) {
                result[i] = 0;
                continue;
            }
            // Линейный поиск остальных подходящих отрезков
            int count = 0;
            for (int j = first; j < segments.length && segments[j].start <= point; j++) {
                if (segments[j].stop >= point) {
                    count++;
                }
            }
            result[i] = count;
        }

        return result;
    }

    // Быстрая сортировка с 3-разбиением и элиминацией хвостовой рекурсии
    private void quickSort(Segment[] arr, int left, int right) {
        while (left < right) {
            int[] pivotIndices = partition(arr, left, right);
            if (pivotIndices[0] - left < right - pivotIndices[1]) {
                quickSort(arr, left, pivotIndices[0] - 1);
                left = pivotIndices[1] + 1;
            } else {
                quickSort(arr, pivotIndices[1] + 1, right);
                right = pivotIndices[0] - 1;
            }
        }
    }

    // 3-разбиение (Dutch National Flag)
    private int[] partition(Segment[] arr, int left, int right) {
        Segment pivot = arr[left + (right - left) / 2];
        int i = left;
        int j = left;
        int k = right;

        while (j <= k) {
            int cmp = arr[j].compareTo(pivot);
            if (cmp < 0) {
                swap(arr, i++, j++);
            } else if (cmp > 0) {
                swap(arr, j, k--);
            } else {
                j++;
            }
        }
        return new int[]{i, k};
    }

    private void swap(Segment[] arr, int i, int j) {
        Segment temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Бинарный поиск первого отрезка, который может содержать точку
    private int findFirstSegment(Segment[] segments, int point) {
        int left = 0;
        int right = segments.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (segments[mid].start <= point) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    private class Segment implements Comparable<Segment> {
        int start;
        int stop;

        Segment(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Segment o) {
            return Integer.compare(this.start, o.start);
        }
    }
}