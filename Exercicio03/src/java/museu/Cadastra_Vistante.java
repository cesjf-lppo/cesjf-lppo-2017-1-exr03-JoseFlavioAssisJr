package museu;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@WebServlet(name = "Cadastra_Visitante", urlPatterns = {"/cadastra.html"})
public class Cadastra_Vistante extends HttpServlet {

    private static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/cadastraVistante.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        String nome = request.getParameter("nome");
            int idade = Integer.parseInt(request.getParameter("idade"));
            
            Logger.getLogger(Cadastra_Vistante.class.getName()).log(Level.INFO, "POST: " +nome+" "+idade+" ");

            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                String url = "jdbc:derby://localhost:1527/lppo-2017-1";
                Connection conexao = DriverManager.getConnection(url, "usuario", "senha");
                System.out.println("Sucesso na conexão!");
             
             String sql = " INSERT INTO visitante (nome, idade) VALUES('"
                     +nome+ "' , '"
                     +idade+")";
             Statement operacao = conexao.createStatement();
             operacao.executeUpdate(sql);   
        } 
            catch (Exception e) {
            Logger.getLogger(Cadastra_Vistante.class.getName()).log(Level.SEVERE, "Erro!" +e);
            }
            response.sendRedirect("lista.html");
    }
}

