package hust.mssv20200547.pttkhtaims.database;

import hust.mssv20200547.pttkhtaims.models.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class MySqlAims implements IDatabase{
    @Override
    public Map<Media, Long> get(Collection<Media> medias) throws SQLException {
        List<Long> ids = medias.stream().map(Media::getId).toList();

        return accessDB((mysql) -> {
            var preparedStatement = mysql.prepareStatement(
                    "Select id, title, category, value, price, quantity " +
                    "From media " +
                    "Where id In (?)"
            );
            var arrayId = mysql.createArrayOf("INTEGER", ids.toArray());
            preparedStatement.setArray(1, arrayId);

            var rs = preparedStatement.executeQuery();

            Map<Media, Long> res = new HashMap<>();
            while (rs.next()) {
                long id = rs.getLong(1);
                String title = rs.getString(2);
                String category = rs.getString(3);
                long value = rs.getLong(4);
                long price = rs.getLong(5);
                long quantity = rs.getLong(6);

                switch (category.toUpperCase(Locale.ROOT)) {
                    case "BOOK" -> res.put(
                            new Book(id, title, price, value, null, null, null, null, null, null, 0),
                            quantity
                    );
                    case "CD" -> res.put(
                            new CD(id, title, price, value, null, null, null, null, null),
                            quantity
                    );
                    case "DIGITALVIDEODISC" -> res.put(
                            new DigitalVideoDisc(id, title, price, value, null, null, null, null, null, null, null, null),
                            quantity
                    );
                    case "LONGPLAYRECORD" -> res.put(
                            new LongPlayRecord(id, title, price, value, null, null, null, null, null, null, null, null),
                            quantity
                    );
                    default -> res.put(new Media(id, title, price, value) {}, quantity);
                }
            }

            return res;
        });
    }

    private <T> T accessDB(SqlAction<Connection, T> action) throws SQLException {
        try (var mysql = DriverManager.getConnection("jdbc:mysql://localhost:3306/personalaims", "root", "")) {
            return action.call(mysql);
        }
    }
}
