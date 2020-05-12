package first;

public class Normal {
    public static void main(String[] args) {
        int[] array1 = {123, 156, 187};
        int[] array2 = {145, 154, 199, 201};
        merge(array1, array2);
    }

    public static void merge(int[] array1, int[] array2) {
        int i = 0, j = 0, k = 0;
        int[] array = new int[array1.length + array2.length];
        while (i < array1.length && j < array2.length) {
            if (array1[i] < array2[j])
                array[k++] = array1[i++];
            else
                array[k++] = array2[j++];
        }
        while (i < array1.length)
            array[k++] = array1[i++];
        while (j < array2.length)
            array[k++] = array2[j++];
        System.out.print("Merged array{");
        for (i = 0; i < array.length; i++)
            System.out.print(array[i]+", ");
        System.out.println("}");
        System.out.print("Middlemost:"+array[i/2]);
    }
}
