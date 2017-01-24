package co.com.ias.demos.canopus.adapters;

import co.com.ias.demos.canopus.domain.Location;
import co.com.ias.demos.canopus.domain.Store;
import com.google.gson.*;
import java.lang.reflect.Type;

public class StoreAdapter implements JsonDeserializer<Store>{

    @Override
    public Store deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonObject locationObj = jsonObject.get("location").getAsJsonObject();
        Location location = new Location(locationObj.get("latitude").getAsDouble(), locationObj.get("longitude").getAsDouble());
        return new Store(jsonObject.get("name").getAsString(), location, jsonObject.get("address").getAsString());
    }
}
