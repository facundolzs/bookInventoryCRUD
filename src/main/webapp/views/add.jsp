<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Agregar libro</title>
    </head>
    <body>
        <h2>Completar formulario</h2>

        <form action="BookCtrl" method="POST" accept-charset="UTF-8">
            <input type="hidden" name="requestType" value="add">
            <table border="1">
                <tr>
                    <td>Título:</td>
                    <td><input type="text" name="book_title" size="50"></td>
                </tr>
                <tr>
                    <td>Género:</td>
                    <td><input type="text" name="book_genre" size="32"></td>
                </tr>
                <tr>
                    <td>Nombre autor:</td>
                    <td><input type="text" name="book_authorName" size="32"></td>
                </tr>
                <tr>
                    <td>Fecha publicación (año-mes-día):</td>
                    <td><input type="text" name="book_pubDate" size="32"></td>
                </tr>
                <tr>
                    <td>Cantidad páginas:</td>
                    <td><input type="text" name="book_pageCount" size="5"></td>
                </tr>
            </table>

            <input type="submit" value="Agregar">
        </form>

    </body>
</html>

