import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        CategoryDAO categoryDAO = new CategoryDAO("grocery","localhost","5432" );
        categoryDAO.getCategories();
        categoryDAO.addCategory(1,"Diary", "Diary products like milk etc.");
        categoryDAO.addCategory(2,"Cereals", "Grain used for food, such as wheat, oats, or corn.");
        categoryDAO.addCategory(3, "Vegetables" , "Vegetables");
        categoryDAO.getCategories();
        categoryDAO.deleteCategory(3);
        categoryDAO.getCategories();
        categoryDAO.updateCategory(1,"Diary", "Milk products");
        categoryDAO.getCategories();
        categoryDAO.addCategory(3, "Vegetables" , "Vegetables");
        categoryDAO.getCategories();

        ProductDAO productDAO = new ProductDAO("grocery","localhost","5432" );
        productDAO.getProducts();
        productDAO.addProduct(10,"Prostokvashino Milk 2,5%",1, 27.99, 123);
        productDAO.addProduct(11,"Galischina Milk 2,5%",1, 26.99, 57);
        productDAO.addProduct(12,"Tomato Ukraine 1st Category",3, 34.49, 42);
        productDAO.addProduct(1,"Prostokvashino Yogurt Strawberry",1, 17.09, 45);
        productDAO.getProducts();
        productDAO.getProductsByCategory(1);
        productDAO.updateProduct(1,"Prostokvashino Yogurt Strawberry",1, 17.09, 64);
        productDAO.getProducts();
        productDAO.deleteProduct(1);
        productDAO.getProducts();
        productDAO.addProduct(1,"Prostokvashino Yogurt Strawberry",1, 17.09, 45);
    }
}
