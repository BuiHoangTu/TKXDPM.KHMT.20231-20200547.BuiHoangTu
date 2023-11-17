package hust.mssv20200547.pttkhtaims.database;

import hust.mssv20200547.pttkhtaims.models.Media;
import jakarta.json.Json;
import jakarta.json.JsonReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class DatabaseImp implements IDatabase{
    private final Map<Media, Long> data = new HashMap<>();

    public DatabaseImp() {
        try (JsonReader jsonReader = Json.createReader(DatabaseImp.class.getResourceAsStream("/hust/mssv20200547/pttkhtaims/services/place-order-service-test/database.json"))) {
            var databaseJson = jsonReader.readArray();

            for(var item: databaseJson) {
                var title = item.asJsonObject().getString("title");
                var quantity = item.asJsonObject().getInt("quantity");

                var media = new Media();
                media.setTitle(title);
                this.data.put(media, (long) quantity);
            }
        }
    }

    @Override
    public Map<Media, Long> get(List<Media> medias) {
        return medias.stream().collect(Collectors.toMap(key ->
            key, data::get
        ));
    }
}
