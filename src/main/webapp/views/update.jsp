<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Actualizar libro</title>
    </head>
    <body>
        <h2>Completar formulario</h2>

        <form action="BookCtrl" method="POST" accept-charset="UTF-8">
            <c:set var="book" value="${bookToUpdate}"></c:set>
                <input type="hidden" name="requestType" value="update">
                <input type="hidden" name="book_id" value="${book.id}">
            <table border="1">
                <tr>
                    <td>Título:</td>
                    <td><input type="text" name="book_title" size="50" value="${book.title}"></td>
                </tr>
                <tr>
                    <td>Género:</td>
                    <td><input type="text" name="book_genre" size="32" value="${book.genre}"></td>
                </tr>
                <tr>
                    <td>Nombre autor:</td>
                    <td><input type="text" name="book_authorName" size="32" value="${book.authorName}"></td>
                </tr>
                <tr>
                    <td>Fecha publicación (año-mes-día):</td>
                    <td><input type="text" name="book_pubDate" size="32" value="${book.pubDate}"></td>
                </tr>
                <tr>
                    <td>Cantidad páginas:</td>
                    <td><input type="text" name="book_pageCount" size="5" value="${book.pageCount}"></td>
                </tr>
            </table>

            <input type="submit" value="Actualizar">
        </form>

    </body>
</html>
