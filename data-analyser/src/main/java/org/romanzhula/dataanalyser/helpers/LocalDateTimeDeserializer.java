package org.romanzhula.dataanalyser.helpers;

import com.google.gson.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

@Component
public class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(
            JsonElement jsonElement,
            Type type,
            JsonDeserializationContext jsonDeserializationContext
    ) throws JsonParseException {

        JsonArray jsonArray = jsonElement.getAsJsonArray();

        var year = jsonArray.get(0).getAsInt();
        var month = jsonArray.get(1).getAsInt();
        var day = jsonArray.get(2).getAsInt();
        var hour = jsonArray.get(3).getAsInt();
        var minute = jsonArray.get(4).getAsInt();
        var second = jsonArray.get(5).getAsInt();

        return LocalDateTime.of(year, month, day, hour, minute, second);
    }
}
