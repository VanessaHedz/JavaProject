import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class e1 extends HttpServlet{

    private Connection conn; //Conexión con la base de datos

    @Override
    public void init() throws ServletException{
        super.init();
        conn = DBConnection();
    }

    /*
    //Los Servlets no tienen el método **main**, sino que    
    public static void main(String args[]){
        try{
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM catalogo");

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + " Título: " + rs.getString("titulo"));
            }


        }catch(SQLException e){
            System.out.println("ERROR EN EL STATEMENT: " + e);
        }
        
    }*/

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        StringBuilder json = new StringBuilder();
        json.append("{\"peliculas\": [");

        try{
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM catalogo");
            
            boolean first = true;
            while (rs.next()) {
                if (!first) json.append(",");
                json.append("{")
                    .append("\"id\": \"").append(rs.getString("id")).append("\", ")
                    .append("\"titulo\": \"").append(rs.getString("titulo")).append("\"")
                    .append("}");
                first = false;
            }
            
            json.append("]}");
            out.print(json.toString());

        }catch(SQLException e){
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\":\"Error en la base de datos\"}");
        }

        out.flush();

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