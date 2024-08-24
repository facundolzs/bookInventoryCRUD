package controller;

import dao.BookDAO;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Book;
import util.DateUtils;

@WebServlet(name = "BookControllerServlet", urlPatterns = {"/BookCtrl"})
public class BookControllerServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String requestType = request.getParameter("requestType");
        switch (requestType) {
            case "add": {
                handleAdd(request, response);
                break;
            }

            case "update": {
                handleUpdate(request, response);
                break;
            }

            case "fetchAll": {
                handleFetchAll(request, response);
                break;
            }

            case "delete": {
                BookDAO bookDAO = new BookDAO();
                Integer bookToDelete = Integer.valueOf(request.getParameter("book_id"));

                bookDAO.delete(bookToDelete);
                redirectToIndexJSP(request, response);
                break;
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String requestType = request.getParameter("requestType");

        switch (requestType) {
            case "add": {
                BookDAO bookDAO = new BookDAO();
                Book book = new Book();
                try {
                    book.setTitle(request.getParameter("book_title"));
                    book.setGenre(request.getParameter("book_genre"));
                    book.setAuthorName(request.getParameter("book_authorName"));
                    Date sqlPubdate = DateUtils.convertStringToSqlDate(request.getParameter("book_pubDate"));
                    book.setPubDate(sqlPubdate);
                    book.setPageCount(Short.parseShort(request.getParameter("book_pageCount")));
                    book.setCreatedAt(DateUtils.getCurrentDate());

                    bookDAO.create(book);
                    redirectToIndexJSP(request, response);
                } catch (ParseException ex) {
                    Logger.getLogger(BookControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }

            case "update": {
                BookDAO bookDAO = new BookDAO();
                Book book = new Book();
                try {
                    book.setId(Integer.valueOf(request.getParameter("book_id")));
                    book.setTitle(request.getParameter("book_title"));
                    book.setGenre(request.getParameter("book_genre"));
                    book.setAuthorName(request.getParameter("book_authorName"));
                    Date sqlPubdate = DateUtils.convertStringToSqlDate(request.getParameter("book_pubDate"));
                    book.setPubDate(sqlPubdate);
                    book.setPageCount(Short.parseShort(request.getParameter("book_pageCount")));
                    book.setUpdatedAt(DateUtils.getCurrentDate());

                    bookDAO.update(book);
                    redirectToIndexJSP(request, response);
                } catch (ParseException ex) {
                    Logger.getLogger(BookControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    /**
     * Handles the HTTP GET request for adding a new Book.
     *
     * @param request the HttpServletRequest object that contains the request the client made of the servlet
     * @param response the HttpServletResponse object that will contain the response to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private void handleAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/add.jsp");
        requestDispatcher.forward(request, response);
    }

    /**
     * Handles the HTTP GET request for updating an existing item.
     *
     * @param request the HttpServletRequest object that contains the request the client made of the servlet
     * @param response the HttpServletResponse object that will contain the response to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private void handleUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer bookID = Integer.valueOf(request.getParameter("book_id"));
        BookDAO bookDAO = new BookDAO();
        Book bookToUpdate = bookDAO.fetch(bookID);

        request.setAttribute("bookToUpdate", bookToUpdate);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/update.jsp");
        requestDispatcher.forward(request, response);
    }

    /**
     * Handles the HTTP GET request for fetching all items.
     *
     * @param request the HttpServletRequest object that contains the request the client made of the servlet
     * @param response the HttpServletResponse object that will contain the response to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private void handleFetchAll(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BookDAO bookDAO = new BookDAO();
        List<Book> bookRecords = bookDAO.fetchAll();

        request.setAttribute("bookRecords", bookRecords);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/fetchAll.jsp");
        requestDispatcher.forward(request, response);
    }

    /**
     * Redirects the request to the <code>index.jsp</code> page.
     *
     * @param request the HttpServletRequest object that contains the request the client made of the servlet
     * @param response the HttpServletResponse object that will contain the response to the client
     * @throws ServletException if a servlet-specific error occurs while forwarding the request
     * @throws IOException if an I/O error occurs while forwarding the request
     */
    private void redirectToIndexJSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(request, response);
    }

}
