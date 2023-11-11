package com.example.MyReadingCorner;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

    private String author;

    private String status; // "Want To Read" "Currently Reading" "Finished"

    public Integer getId() {
        return id;
      }
    
      public void setId(Integer id) {
        this.id = id;
      }
    
      public String getName() {
        return name;
      }
    
      public void setName(String name) {
        this.name = name;
      }

       public String getAuthor() {
        return author;
      }
    
      public void setAuthor(String author) {
        this.author = author;
      }

      public String getStatus() {
        return status;
      }
    
      public void setStatus(String status) {
        this.status = status;
      }
}
