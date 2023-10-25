package persistence;

import org.json.JSONObject;

// interface for json types to have convert method
// CITATION: from JsonSerializationDemo
public interface MakeJsonType {
    //EFFECTS: returns this as a JSON Object
    JSONObject convertToJson();
}