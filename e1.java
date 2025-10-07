import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet.*;

public class e1 extends HttpServlet{

    public static void main(String args[]){
        try{
            Connection conn = DBConnection(); //Conexión con la base de datos
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM catalogo");

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + " Título: " + rs.getString("titulo"));
            }


        }catch(SQLException e){
            System.out.println("ERROR EN EL STATEMENT: " + e);
        }
        
        
        
    }

    public static Connection DBConnection(){
        String url = "jdbc:mysql://localhost:3306/crud-peliculas";
        String username = "root";
        String password = "";

        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected!");
            return connection;
        }catch(SQLException e){
            throw new IllegalStateException("No se pudo conectar a la base! ", e);
        }
    }
}