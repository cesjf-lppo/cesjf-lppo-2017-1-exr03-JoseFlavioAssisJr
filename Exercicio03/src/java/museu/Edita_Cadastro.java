package museu;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Edita_Cadastro", urlPatterns = {"/edita.html"})
public class Edita_Cadastro extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Visitante v1 = new Visitante();
        Long id = Long.parseLong(request.getParameter("id"));

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/lppo-2017-1", "usuario", "senha");
            Statement operacao = conexao.createStatement();
            ResultSet resultado = operacao.executeQuery("SELECT * FROM visitante WHERE id=" + id);
            if (resultado.next()) {
                v1 = new Visitante();
                v1.setId(resultado.getLong("id"));
                v1.setNome(resultado.getString("nome"));
                v1.setIdade(resultado.getInt("idade"));
                }
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Lista_Visitantes.class.getName()).log(Level.SEVERE, null, ex);}
        catch (SQLException ex) {
            Logger.getLogger(Lista_Visitantes.class.getName()).log(Level.SEVERE, null, ex);}

        request.setAttribute("v1", v1);
        request.getRequestDispatcher("WEB-INF/editaCadastro.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            Visitante v1 = new Visitante();
            v1.setId(Long.parseLong(request.getParameter("id")));
            v1.setNome(request.getParameter("nome"));
            v1.setIdade(Integer.parseInt(request.getParameter("idade")));

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/lppo-2017-1", "usuario", "senha");
            Statement operacao = conexao.createStatement();
            operacao.executeUpdate("UPDATE visitante SET nome='"
                    + v1.getNome() + "',idade="
                    + v1.getIdade() + "WHERE id="
                    + v1.getId());
        } 
        
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Lista_Visitantes.class.getName()).log(Level.SEVERE, null, ex);}
        catch (SQLException ex) {
            Logger.getLogger(Lista_Visitantes.class.getName()).log(Level.SEVERE, null, ex);}

        response.sendRedirect("lista.html");
    }
}
