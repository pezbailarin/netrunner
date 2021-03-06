package it.ck.cyberdeck.persistance;

import it.ck.cyberdeck.model.Faction;

import java.lang.reflect.Type;

import com.google.gson.*;

public class FactionDeserializer implements JsonDeserializer<Faction> {

  @Override
  public Faction deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    String value = json.getAsJsonPrimitive().getAsString();
    return Faction.fromString(value);
  }

}
