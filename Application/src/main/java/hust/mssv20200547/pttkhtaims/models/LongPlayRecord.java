package hust.mssv20200547.pttkhtaims.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class LongPlayRecord extends Media{
    private String director;
    private LocalTime runtime;
    private String studio;
    private LocalDate publicationDate;
    private String discFormat;
    private String language;
    private String subtitles;
    private String genre;

}
