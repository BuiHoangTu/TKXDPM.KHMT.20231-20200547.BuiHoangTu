package hust.mssv20200547.pttkhtaims.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class Book extends Media{
    private String genre;
    private String authors;
    private String cover;
    private String publisher;
    private LocalDate publicationDate;
    private String language;
    private long numberOfPages;
}
