package co.com.ias.demos.canopus.adapters;

import com.google.gson.*;
import org.bson.types.ObjectId;

import java.lang.reflect.Type;

public class ObjectIdAdapter implements JsonSerializer<ObjectId> {
    @Override
    public JsonElement serialize(ObjectId objectId, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(objectId.toString());
    }
}
