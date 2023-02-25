package exercice;

import java.util.Arrays;

public class SurpasserCount {

    // Méthode 1 : Naive approach (Complexity: O(n^2))
    public static int[] findSurpasserCountNaive(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = i + 1; j < n; j++) {
                if (arr[i] < arr[j]) {
                    count++;
                }
            }
            res[i] = count;
        }
        return res;
    }

    // Méthode 2 : Utilise le tri par fusion (Complexité: O(n log n))
    public static int[] findSurpasserCountMergeSort(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        int[] indexes = new int[n];
        for (int i = 0; i < n; i++) {
            indexes[i] = i;
        }
        mergeSort(arr, indexes, 0, n - 1, res);
        return res;
    }

    private static void mergeSort(int[] arr, int[] indexes, int left, int right, int[] res) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(arr, indexes, left, mid, res);
        mergeSort(arr, indexes, mid + 1, right, res);
        merge(arr, indexes, left, right, res);
    }

    private static void merge(int[] arr, int[] indexes, int left, int right, int[] res) {
        int mid = left + (right - left) / 2;
        int i = left, j = mid + 1, k = 0;
        int[] temp = new int[right - left + 1];
        while (i <= mid && j <= right) {
            if (arr[indexes[i]] <= arr[indexes[j]]) {
                temp[k++] = indexes[i++];
            } else {
                res[indexes[i]] += right - j + 1;
                temp[k++] = indexes[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = indexes[i++];
        }
        while (j <= right) {
            temp[k++] = indexes[j++];
        }
        for (int p = 0; p < temp.length; p++) {
            indexes[left + p] = temp[p];
        }
    }

    // Exemple d'utilisation
    public static void main(String[] args) {
        int[] arr = {2, 7, 5, 3, 0, 8, 1};
        System.out.println("Array: " + Arrays.toString(arr));
        int[] res1 = findSurpasserCountNaive(arr);
        int[] res2 = findSurpasserCountMergeSort(arr);
        System.out.println("Surpasser counts (naive approach): " + Arrays.toString(res1));
        System.out.println("Surpasser counts (merge sort approach): " + Arrays.toString(res2));
    }
}
