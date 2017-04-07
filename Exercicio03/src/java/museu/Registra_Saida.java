package museu;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Registra_Saida", urlPatterns = {"/saida.html"})
public class Registra_Saida extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            Long id = Long.parseLong(request.getParameter("id"));

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/lppo-2017-1", "usuario", "senha");
            Statement operacao = conexao.createStatement();
            operacao.executeUpdate("UPDATE visitante SET saida=CURRENT_TIMESTAMP WHERE id=" + id);
            } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Lista_Visitantes.class.getName()).log(Level.SEVERE, null, ex);}
        catch (SQLException ex) {
            Logger.getLogger(Lista_Visitantes.class.getName()).log(Level.SEVERE, null, ex);}

        response.sendRedirect("lista.html");
    }
}
