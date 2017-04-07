<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listagem de Visitantes</title>
    </head>
    <body>
        <h2>Lista de Visitantes</h2>
        <table>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Idade</th>
                <th>Data de entrada</th>
                <th>Data de saída</th>
            </tr>
            <c:forEach var="visitante" items="${visitantes}">
                <tr>
                    <td><a href="edita.html?id=${visitante.id}">${visitante.id}</a></td>
                    <td>${visitante.nome}</td>
                    <td>${visitante.idade}</td>
                    <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${visitante.entrada}" /></td>
                    <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${visitante.saida}" /></td>
                    <td><a href="exclui.html?id=${visitante.id}">X</a></td>   
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
