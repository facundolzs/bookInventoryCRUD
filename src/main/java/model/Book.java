package model;

import java.sql.Date;

public class Book {

    private Integer id;
    private String title;
    private String genre;
    private Date pubDate;
    private String authorName;
    private short pageCount;
    private Date createdAt;
    private Date updatedAt;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public String getAuthorName() {
        return authorName;
    }

    public short getPageCount() {
        return pageCount;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setPageCount(short pageCount) {
        this.pageCount = pageCount;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title=" + title + ", genre=" + genre + ", pubDate=" + pubDate + ", authorName=" + authorName + ", pageCount=" + pageCount + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }

}
