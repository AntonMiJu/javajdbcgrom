package hibernate.lesson2.homework.hql;

public class Demo {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();

        System.out.println(productDAO.findById(999));
        System.out.println(productDAO.findByName("toy"));
        System.out.println(productDAO.findByContainedName("o"));
        System.out.println(productDAO.findByPrice(100,30));
        System.out.println(productDAO.findByNameSortedAsc());
        System.out.println(productDAO.findByNameSortedDesc());
        System.out.println(productDAO.findByPriceSortedDesc(100,30));
    }
}
