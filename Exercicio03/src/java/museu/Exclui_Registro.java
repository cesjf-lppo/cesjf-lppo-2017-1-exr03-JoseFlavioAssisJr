package museu;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Exclui_Registro", urlPatterns = {"/exclui.html"})
public class Exclui_Registro extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //1 Pega ID dos parâmetros
       Long id = Long.parseLong(request.getParameter("id"));
        //2 Exclui registro do banco
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/lppo-2017-1", "usuario", "senha");
            Statement operacao = conexao.createStatement();
            operacao.executeUpdate("DELETE FROM visitante WHERE id=" + id);
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Lista_Visitantes.class.getName()).log(Level.SEVERE, null, ex);} 
        catch (SQLException ex) {
            Logger.getLogger(Lista_Visitantes.class.getName()).log(Level.SEVERE, null, ex);}
        // 3 Redireciona para a lista
        response.sendRedirect("lista.html");
    }
}
