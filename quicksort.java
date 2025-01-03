import java.util.HashSet;
import java.util.Random;

public class quicksort {

  static int count = 0;

  public static void quickSort(int[] arr, int left, int right) {
    if (left < right) {
      int pivotIndex = partition(arr, left, right);
      quickSort(arr, left, pivotIndex - 1);
      quickSort(arr, pivotIndex + 1, right);
    }
  }

  public static int partition(int[] arr, int left, int right) {
    int pivot = arr[right];
    int i = left - 1;

    for (int j = left; j < right; j++) {
      if (arr[j] <= pivot) {
        i++;
        swap(arr, i, j);
      }
    }

    swap(arr, i + 1, right);
    return i + 1;
  }

  public static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
    count++;
  }

  public static void mergeSort(int[] arr, int left, int right) {
    if (left < right) {
      int mid = (left + right) / 2;
      mergeSort(arr, left, mid);
      mergeSort(arr, mid + 1, right);
      merge(arr, left, mid, right);
    }
  }

  public static void merge(int[] arr, int left, int mid, int right) {
    int n1 = mid - left + 1;
    int n2 = right - mid;

    int[] leftArray = new int[n1];
    int[] rightArray = new int[n2];

    for (int i = 0; i < n1; i++) {
      leftArray[i] = arr[left + i];
    }
    for (int i = 0; i < n2; i++) {
      rightArray[i] = arr[mid + 1 + i];
    }

    int i = 0, j = 0, k = left;

    while (i < n1 && j < n2) {
      if (leftArray[i] <= rightArray[j]) {
        arr[k] = leftArray[i];
        i++;
      } else {
        arr[k] = rightArray[j];
        j++;
      }
      k++;
      count++;
    }

    while (i < n1) {
      arr[k] = leftArray[i];
      i++;
      k++;
    }

    while (j < n2) {
      arr[k] = rightArray[j];
      j++;
      k++;
    }

  }

  public static int[] generateRandomArray(int size) {
    int[] array = new int[size];
    Random random = new Random();
    HashSet<Integer> uniqueValues = new HashSet<>();

    for (int i = 0; i < size; i++) {
      int value;
      do {
        value = random.nextInt(size);
      } while (uniqueValues.contains(value));

      uniqueValues.add(value);
      array[i] = value;
    }
    return array;
  }

  public static void printArray(int[] array) {
    System.out.print("[");
    for (int i = 0; i < array.length; i++) {
      System.out.print(array[i]);
      if (i < array.length - 1) {
        System.out.print(", ");
      }
    }
    System.out.println("]");
  }

  public static void main(String[] args) {
    int[] sizes = { 10, 50, 100, 1000, 10000, 50000 };

    System.out.println("quickSort: " + "\n");

    for (int size : sizes) {
      int[] arr = generateRandomArray(size);
      long startTime = System.nanoTime();

      quickSort(arr, 0, arr.length - 1);

      long endTime = System.nanoTime();

      System.out
          .println("tamanho: " + size + "\n" + "trocas: " + count + "\n" + "tempo: " + ((endTime - startTime) / 1000));

      count = 0;
    }

    System.out.println("mergeSort: " + "\n");

    for (int size : sizes) {
      int[] arr = generateRandomArray(size);
      long startTime = System.nanoTime();

      mergeSort(arr, 0, arr.length - 1);

      long endTime = System.nanoTime();

      System.out
          .println("tamanho: " + size + "\n" + "trocas: " + count + "\n" + "tempo: " + ((endTime - startTime) / 1000));

      count = 0;
    }
  }
}