package lesson3.homework.task1;

public class Demo {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.findProductsByPrice(100,50));
        System.out.println();
        System.out.println(solution.findProductsByName("toy"));
        System.out.println();
        System.out.println(solution.findProductsWithEmptyDescription());
    }
}
