import java.sql.*;

public class ProductDAO {
    private Connection con = null;

    public ProductDAO(String DBName, String ip, String port) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://"+ip+":" + port + "/" +DBName;
        con = DriverManager.getConnection(url,"postgres","admin");
    }

    public void stop() throws SQLException{
        con.close();
    }

    public boolean addProduct(int id, String name,int categoryId, double price, int quantity){
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO products (id, name, price, quantity, category) VALUES (?,?,?,?,?)");
            ps.setInt(1,id);
            ps.setString(2,name);
            ps.setDouble(3,price);
            ps.setInt(4,quantity);
            ps.setInt(5,categoryId);
            ps.executeUpdate();
            System.out.println("Product " + name+ " has been added");
            return true;

        } catch (SQLException throwables) {
            System.out.println("Error! Product " + name+ " hasn't been added");
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
            return false;
        }
    }


    public boolean deleteProduct(int id){
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM products WHERE id = "+ id);
            int c  = ps.executeUpdate();
            if(c > 0) {
                System.out.println("Product " + id + " has been deleted");
            }
            else{
                System.out.println("Product " + id + " doesn't exist");
            }
            return true;
        } catch (SQLException throwables) {
            System.out.println("Error! Product " + id + " hasn't been deleted");
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
            return false;
        }
    }

    public void getProducts(){
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM products");
            System.out.println("Products list:");
            while (rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                double price = rs.getDouble(3);
                int quantity = rs.getInt(4);
                int categoryId = rs.getInt(5);
                System.out.println(id + " | " + name + " | " + price + " | " + quantity + " | " + categoryId);
            }
            rs.close();
        } catch (SQLException throwables) {
            System.out.println("Error! Can't obtain products");
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
        }
    }

    public void getProductsByCategory(int categoryId){
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM products WHERE category = "+categoryId);
            System.out.println("Products in category " + categoryId + ":");
            while (rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                double price = rs.getDouble(3);
                int quantity = rs.getInt(4);
                int category = rs.getInt(5);
                System.out.println(id + " | " + name + " | " + price + " | " + quantity + " | " + category);
            }
            rs.close();
        } catch (SQLException throwables) {
            System.out.println("Error! Can't obtain products");
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
        }
    }

    public boolean updateProduct(int id, String name,int categoryId, double price, int quantity){
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE products SET name = ?, price = ?, quantity = ?, category = ? WHERE id = ?");
            ps.setString(1,name);
            ps.setDouble(2,price);
            ps.setInt(3,quantity);
            ps.setInt(4,categoryId);
            ps.setInt(5,id);
            int c = ps.executeUpdate();
            if(c>0)
                System.out.println("Product " + name + " has been updated");
            return true;

        } catch (SQLException throwables) {
            System.out.println("Error! Product " + name+ " hasn't been updated");
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
            return false;
        }
    }

}
