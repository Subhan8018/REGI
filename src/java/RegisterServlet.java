import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // You should securely store your database credentials
        String jdbcURL = "jdbc:mysql://localhost:3306/your_database";
        String dbUser = "root";
        String dbPassword = "Subhan@123";

        try {
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            String insertQuery = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            response.getWriter().write("Registration Successful!");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Registration Failed!");
        }
    }
}
