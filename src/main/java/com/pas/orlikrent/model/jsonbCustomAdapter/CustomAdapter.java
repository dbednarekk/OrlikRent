package com.pas.orlikrent.model.jsonbCustomAdapter;

import javax.json.Json;
import javax.json.JsonValue;
import javax.json.bind.adapter.JsonbAdapter;

public class CustomAdapter  implements JsonbAdapter<String, JsonValue> {
    @Override
    public JsonValue adaptToJson(String s) throws Exception {
        return Json.createValue("");
    }

    @Override
    public String adaptFromJson(JsonValue value) throws Exception {
        return value.toString();
    }
}
