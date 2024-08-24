package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import model.Book;
import connection.ConnectionPool;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private boolean sqlOperationSucceeded;

    /**
     * Retrieves a {@code Connection} from the connection pool.
     *
     * @return A {@code Connection} object from the pool.
     */
    private Connection getConnection() {
        return ConnectionPool.getConnection();
    }

    /**
     * Inserts a new {@code Book} into the database.
     * <p>
     * Starts a transaction, commits if successful, or rolls back in case of failure.
     * </p>
     *
     * @param book The {@code Book} object to insert. Must not be {@code null}.
     * @return {@code true} if the insertion was successful, {@code false} otherwise.
     */
    public boolean create(Book book) {
        preparedStatement = null;
        sqlOperationSucceeded = false;
        connection = getConnection();

        try {
            connection.setAutoCommit(false);

            String sql = "INSERT INTO books(bk_title, bk_genre, bk_pub_date, bk_author_name, bk_page_count, bk_created_at) "
                    + "VALUES(?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getGenre());
            preparedStatement.setDate(3, book.getPubDate());
            preparedStatement.setString(4, book.getAuthorName());
            preparedStatement.setShort(5, book.getPageCount());
            preparedStatement.setDate(6, book.getCreatedAt());

            sqlOperationSucceeded = preparedStatement.executeUpdate() > 0;
            if (sqlOperationSucceeded) {
                connection.commit();
            } else {
                connection.rollback();
            }
        } catch (SQLException ex) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            ex.printStackTrace();
        } finally {
            closeResource(preparedStatement);
            closeResource(connection);
        }

        return sqlOperationSucceeded;
    }

    /**
     * Updates an existing {@code Book} in the database.
     * <p>
     * Starts a transaction, commits if successful, or rolls back in case of failure.
     * </p>
     *
     * @param book The {@code Book} object with updated data. Must not be {@code null}.
     * @return {@code true} if the update was successful, {@code false} otherwise.
     */
    public boolean update(Book book) {
        preparedStatement = null;
        sqlOperationSucceeded = false;
        connection = getConnection();

        try {
            connection.setAutoCommit(false);

            String sql = "UPDATE books SET bk_title = ?, bk_genre = ?, bk_pub_date = ?, bk_author_name = ?, bk_page_count = ?, bk_updated_at = ?"
                    + "WHERE bk_id = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getGenre());
            preparedStatement.setDate(3, book.getPubDate());
            preparedStatement.setString(4, book.getAuthorName());
            preparedStatement.setShort(5, book.getPageCount());
            preparedStatement.setDate(6, book.getUpdatedAt());
            preparedStatement.setInt(7, book.getId());

            sqlOperationSucceeded = preparedStatement.executeUpdate() > 0;
            if (sqlOperationSucceeded) {
                connection.commit();
            } else {
                connection.rollback();
            }
        } catch (SQLException ex) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            ex.printStackTrace();
        } finally {
            closeResource(preparedStatement);
            closeResource(connection);
        }

        return sqlOperationSucceeded;
    }

    /**
     * Deletes a {@code Book} from the database by its ID.
     * <p>
     * Starts a transaction, commits if successful, or rolls back in case of failure.
     * </p>
     *
     * @param bookID The ID of the {@code Book} to delete. Must not be {@code null}.
     * @return {@code true} if the deletion was successful, {@code false} otherwise.
     */
    public boolean delete(Integer bookID) {
        preparedStatement = null;
        sqlOperationSucceeded = false;
        connection = getConnection();

        try {
            connection.setAutoCommit(false);

            String sql = "DELETE FROM books WHERE bk_id = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, bookID);
            sqlOperationSucceeded = preparedStatement.executeUpdate() > 0;
            if (sqlOperationSucceeded) {
                connection.commit();
            } else {
                connection.rollback();
            }
        } catch (SQLException ex) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            ex.printStackTrace();
        } finally {
            closeResource(preparedStatement);
            closeResource(connection);
        }

        return sqlOperationSucceeded;
    }

    /**
     * Retrieves a {@code Book} record from the database by its ID.
     *
     * @param bookID The ID of the book to retrieve.
     * @return A {@code Book} object representing the record with the specified ID, or a {@code Book} with default values if no record is found.
     */
    public Book fetch(Integer bookID) {
        preparedStatement = null;
        sqlOperationSucceeded = false;
        connection = getConnection();

        ResultSet resultSet = null;
        Book book = new Book();

        try {
            String sql = "SELECT * FROM books WHERE bk_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, bookID);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                book.setId(resultSet.getInt("bk_id"));
                book.setTitle(resultSet.getString("bk_title"));
                book.setGenre(resultSet.getString("bk_genre"));
                book.setPubDate(resultSet.getDate("bk_pub_date"));
                book.setAuthorName(resultSet.getString("bk_author_name"));
                book.setPageCount(resultSet.getShort("bk_page_count"));
                book.setCreatedAt(resultSet.getDate("bk_created_at"));
                book.setUpdatedAt(resultSet.getDate("bk_updated_at"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeResource(resultSet);
            closeResource(preparedStatement);
            closeResource(connection);
        }

        return book;
    }

    /**
     * Retrieves all {@code Book} records from the database.
     *
     * @return A {@code List} of {@code Book} objects representing all records in the {@code books} table.
     */
    public List<Book> fetchAll() {
        preparedStatement = null;
        sqlOperationSucceeded = false;
        connection = getConnection();

        ResultSet resultSet = null;
        List<Book> bookRecords = new ArrayList<>();

        try {
            String sql = "SELECT * FROM books";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery(sql);

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("bk_id"));
                book.setTitle(resultSet.getString("bk_title"));
                book.setGenre(resultSet.getString("bk_genre"));
                book.setPubDate(resultSet.getDate("bk_pub_date"));
                book.setAuthorName(resultSet.getString("bk_author_name"));
                book.setPageCount(resultSet.getShort("bk_page_count"));
                book.setCreatedAt(resultSet.getDate("bk_created_at"));
                book.setUpdatedAt(resultSet.getDate("bk_updated_at"));

                bookRecords.add(book);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeResource(resultSet);
            closeResource(preparedStatement);
            closeResource(connection);
        }

        return bookRecords;
    }

    /**
     * Closes the given {@code AutoCloseable} resource, if it is not {@code null}.
     *
     * @param resource the {@code AutoCloseable} resource to be closed, such as a {@code Connection} or {@code PreparedStatement}. This can be {@code null}.
     */
    private void closeResource(AutoCloseable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
