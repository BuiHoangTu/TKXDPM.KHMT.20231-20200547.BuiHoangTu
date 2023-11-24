package hust.mssv20200547.pttkhtaims.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CD extends Media{
    private List<String> trackList;
    private String recordLabel;
    private String artist;
    private String genre;
    private LocalDate publicationDate;

}
