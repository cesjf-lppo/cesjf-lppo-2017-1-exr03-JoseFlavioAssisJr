<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edição de Cadastro</title>
    </head>
    <body>
        <form method="POST">
        <h2>Editar Cadastro</h2>
            <p><input type="hidden" name="id" value="${visitante.id}"/>ID: ${visitante.id} </p>
            <p>Nome: <input type="text" name="nome" value="${visitante.nome}" </p>
            <p>Idade: <input type="text" name="idade" value="${visitante.idade}" </p>
            <br> <br>
            <input type="submit" value="Editar"/>
            <input type="reset" value="Limpar"/>
        </form>
    </body>
</html>
