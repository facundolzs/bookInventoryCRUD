<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Inventario</title>
    </head>
    <body>
        <h2>Todos los libros</h2>

        <table border="1">

            <tr>
                <td><b>ID</b></td>
                <td><b>Título</b></td>
                <td><b>Género</b></td>
                <td><b>Publicado</b></td>
                <td><b>Autor</b></td>
                <td><b>Páginas</b></td>
                <td><b>Fue agregado</b></td>
                <td><b>Fue actualizado</b></td>
            </tr>
            <c:forEach var="book" items="${bookRecords}">
                <tr>
                    <td><c:out value="${book.id}"></c:out></td>
                    <td><c:out value="${book.title}"></c:out></td>
                    <td><c:out value="${book.genre}"></c:out></td>
                    <td><c:out value="${book.pubDate}"></c:out></td>
                    <td><c:out value="${book.authorName}"></c:out></td>
                    <td><c:out value="${book.pageCount}"></c:out></td>
                    <td><c:out value="${book.createdAt}"></c:out></td>
                    <td><c:out value="${book.updatedAt}"></c:out></td>
                    <td><a href="BookCtrl?requestType=update&book_id=<c:out value="${book.id}"></c:out>">Actualizar</a></td>
                    <td><a href="BookCtrl?requestType=delete&book_id=<c:out value="${book.id}"></c:out>">Eliminar</a></td>
                    </tr>
            </c:forEach>

        </table>
    </body>
</html>
