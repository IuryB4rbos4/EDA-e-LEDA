package problems;


public class BinarySearchRotatedArrayImpl implements BinarySearchRotatedArray{
   
    public int findRotations(int[] array){
        int result = 0;
        // Não é necessário fazer essa verificação
        if(array != null && array.length > 0){
            result = findRotationsBinary(array, 0, array.length - 1);
        }

        return result;
    }

    private int findRotationsBinary(int[] array, int leftIndex, int rightIndex) {
        int result = 0;
        if(!(leftIndex > rightIndex)){
            int mid = (leftIndex + rightIndex) / 2;

            if(mid < array.length - 1 && array[mid] > array[mid + 1]){
                result = mid + 1;
            } else {
                if(array[leftIndex] > array[rightIndex] && array[leftIndex] > array[mid]){
                    result = findRotationsBinary(array, leftIndex, mid - 1);
                } else {
                    result = findRotationsBinary(array, mid + 1, rightIndex);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] numbers1 = { 5, 1, 2, 3, 4 }; // 1
        int[] numbers2 = { 4, 5, 1, 2, 3 }; // 2
        int[] numbers3 = { 3, 4, 5, 1, 2 }; // 3
        int[] numbers4 = { 2, 3, 4, 5, 1 }; // 4
        int[] numbers5 = { 1, 2, 3, 4, 5 }; // 0
        BinarySearchRotatedArrayImpl arrocha = new BinarySearchRotatedArrayImpl();
        System.out.println(arrocha.findRotations(numbers1));
        System.out.println(arrocha.findRotations(numbers2));
        System.out.println(arrocha.findRotations(numbers3));
        System.out.println(arrocha.findRotations(numbers4));
        System.out.println(arrocha.findRotations(numbers5));
    }
}
