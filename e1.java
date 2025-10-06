import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class e1{

    public static void main(String args[]){

        System.out.println("Inicio del programa");
        DBConnection(); //Conexión con la base de datos

    }

    public static void DBConnection(){
        String url = "jdbc:mysql://localhost:3306/crud-peliculas";
        String username = "root";
        String password = "";

        try(Connection connection = DriverManager.getConnection(url, username, password)){
            System.out.println("Database connected!");
        }catch(SQLException e){
            throw new IllegalStateException("No se pudo conectar a la base! ", e);
        }
    }

}