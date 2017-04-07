package museu;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Lista_Visitantes", urlPatterns = {"/lista.html"})
public class Lista_Visitantes extends HttpServlet {
    
    private static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List <Visitante> visitantes = new ArrayList<>();
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/lppo-2017-1", "usuario", "senha");
            Statement operacao = conexao.createStatement();
            ResultSet resultado = operacao.executeQuery("SELECT * FROM visitante");

        while(resultado.next()){
            Visitante vis = new Visitante();
            vis.setId(resultado.getLong("Id"));
            vis.setNome(resultado.getString("nome"));
            vis.setIdade(resultado.getInt("idade"));
            String d = df.format(resultado.getDate("entrada"));
            vis.setEntrada(resultado.getTimestamp("entrada"));
            vis.setSaida(resultado.getTimestamp("saida"));
            visitantes.add(vis); }
            }
        
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Lista_Visitantes.class.getName()).log(Level.SEVERE, null, ex);}
        catch (SQLException ex) {
            Logger.getLogger(Lista_Visitantes.class.getName()).log(Level.SEVERE, null, ex);}
        
        request.setAttribute("visitantes", visitantes);
        request.getRequestDispatcher("WEB-INF/listaVisitante.jsp").forward(request, response);
    }
}
