package hust.mssv20200547.pttkhtaims.database;

import hust.mssv20200547.pttkhtaims.models.Media;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IDatabase {
    Map<Media, Long> get(Collection<Media> medias) throws SQLException;

    <M extends Media> M getMedia(M media) throws SQLException;
}
