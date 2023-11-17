package hust.mssv20200547.pttkhtaims.database;

import hust.mssv20200547.pttkhtaims.models.Media;

import java.util.List;
import java.util.Map;

public interface IDatabase {
    Map<Media, Long> get(List<Media> medias);
}
