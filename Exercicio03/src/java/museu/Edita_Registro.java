package museu;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Edita_Registro", urlPatterns = {"/editaregistro.html"})
public class Edita_Registro extends HttpServlet {

    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Visitante visit = new Visitante();
        Long id = Long.parseLong(request.getParameter("id"));

        try {
            Calendar c = Calendar.getInstance();
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/lppo-2017-1", "usuario", "senha");
            Statement operacao = conexao.createStatement();
            ResultSet resultado = operacao.executeQuery("SELECT * FROM visitante WHERE id=" + id);
        
            if (resultado.next()) {
                visit = new Visitante();
                visit.setId(resultado.getLong("id"));
                visit.setNome(resultado.getString("nome"));
                visit.setIdade(resultado.getInt("idade"));
                visit.setEntrada(resultado.getTimestamp("entrada"));
                visit.setSaida(resultado.getTimestamp("saida"));
            }
        } 
        
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Lista_Visitantes.class.getName()).log(Level.SEVERE, null, ex);}
        catch (SQLException ex) {
            Logger.getLogger(Lista_Visitantes.class.getName()).log(Level.SEVERE, null, ex);}
        
        request.setAttribute("visit", visit);
        request.getRequestDispatcher("WEB-INF/editaRegistro.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Visitante visit = new Visitante();

        try {
            visit.setId(Long.parseLong(request.getParameter("id")));
            visit.setNome(request.getParameter("nome"));
            visit.setIdade(Integer.parseInt(request.getParameter("idade")));
            visit.setEntrada(df.parse(request.getParameter("entrada")));
            visit.setSaida(df.parse(request.getParameter("saida")));

        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/lppo-2017-1", "usuario", "senha");
        Statement operacao = conexao.createStatement();
        String Query = "UPDATE visitante SET nome='"
                + visit.getNome() + "',idade="
                + visit.getIdade() + ",entrada='"
                + df.format(visit.getEntrada()) + "', saida='"
                + df.format(visit.getSaida()) + "' WHERE id="
                + visit.getId();
        System.out.println(Query);
        operacao.executeUpdate(Query);
        } 
        
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Lista_Visitantes.class.getName()).log(Level.SEVERE, null, ex);}
        catch (SQLException ex) {
            Logger.getLogger(Lista_Visitantes.class.getName()).log(Level.SEVERE, null, ex);}
        catch (ParseException ex) {
            Logger.getLogger(Lista_Visitantes.class.getName()).log(Level.SEVERE, null, ex);}

        response.sendRedirect("lista.html"); 
    }
}
