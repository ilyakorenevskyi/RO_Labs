import java.sql.*;

public class CategoryDAO {
    private Connection con = null;
    
    public CategoryDAO(String DBName, String ip, String port) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://"+ip+":" + port + "/" +DBName;
        con = DriverManager.getConnection(url,"postgres","admin");
    }
    
    public void stop() throws SQLException{
        con.close();
    }
    
    public boolean addCategory(int id, String name, String description){
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO categories (id,name,description) VALUES (?,?,?)");
            ps.setInt(1,id);
            ps.setString(2,name);
            ps.setString(3,description);
            ps.executeUpdate();
            System.out.println("Category " + name+ " has been added");
            return true;

        } catch (SQLException throwables) {
            System.out.println("Error! Category " + name+ " hasn't been added");
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
            return false;
        }
    }

    public boolean deleteCategory(int id){
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM categories WHERE id = "+ id);
            int c  = ps.executeUpdate();
            if(c>0) {
                System.out.println("Category " + id + " has been deleted");
            }
            else{
                System.out.println("Category " + id + " doesn't exist");
            }
            return true;

        } catch (SQLException throwables) {
            System.out.println("Error! Category " + id+ " hasn't been deleted");
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
            return false;
        }
    }

    public void getCategories(){
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM categories");
            System.out.println("Categories list:");
            while (rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String description = rs.getString(3);
                System.out.println(id + " | " + name + " | " + description);
            }
            rs.close();
        } catch (SQLException throwables) {
            System.out.println("Error! Can't obtain categories");
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
        }
    }

    public boolean updateCategory(int id, String name, String description){
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE categories SET name = ?, description = ? WHERE id = ?");
            ps.setString(1,name);
            ps.setString(2,description);
            ps.setInt(3,id);
            ps.executeUpdate();
            System.out.println("Category " + name+ " has been updated");
            return true;

        } catch (SQLException throwables) {
            System.out.println("Error! Category " + name+ " hasn't been updated");
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
            return false;
        }
    }
}
