package com.apiobject.framework.api;

import com.apiobject.framework.actions.ApiActionsModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ApiObjectModel {
    private String name;
    private HashMap<String, ApiActionsModel> actions;
    private HashMap<String, String> abVatiables = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, ApiActionsModel> getActions() {
        return actions;
    }

    public void setActions(HashMap<String, ApiActionsModel> actions) {
        this.actions = actions;
    }

    public HashMap<String, String> getAbVatiables() {
        return abVatiables;
    }

    public void setAbVatiables(HashMap<String, String> abVatiables) {
        this.abVatiables = abVatiables;
    }

    public static ApiObjectModel load(String path) throws IOException {
        //反序列化
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(new File(path), ApiObjectModel.class);
    }
}
