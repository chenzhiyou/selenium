package com.apiobject.test;
import com.apiobject.framework.actions.ApiActionsModel;
import com.apiobject.framework.api.ApiObjectModel;
import com.apiobject.utils.GetRequestName;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.wechatinterface.department.Demo_01_base;
import de.sstoehr.harreader.HarReader;
import de.sstoehr.harreader.HarReaderException;
import de.sstoehr.harreader.model.Har;
import de.sstoehr.harreader.model.HarRequest;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Test08_HarToYamlTest {
    private static final Logger logger = LoggerFactory.getLogger(Demo_01_base.class);

    @Test
    public void harTest() throws IOException, HarReaderException {
        HarReader harReader = new HarReader();
        Har har = harReader.readFromFile(new File("src/test/resources/har/qyapi.weixin.qq.com.har"));
        logger.info("debug");

        ApiObjectModel apiObjectModel = new ApiObjectModel();
        ApiActionsModel apiActionsModel = new ApiActionsModel();
        HashMap<String, ApiActionsModel> actions =  new HashMap<>();
        HashMap<String, String> queryMap = new HashMap<>();
        har.getLog().getEntries().forEach(entrie->{
            HarRequest harRequest = entrie.getRequest();
            harRequest.getQueryString().forEach(query->{
                queryMap.put(query.getName(),query.getValue());
            });
            String method = harRequest.getMethod().toString();
            String url = harRequest.getUrl();
            apiActionsModel.setQuery(queryMap);
            if(method.equals("get")){
                apiActionsModel.setGet(url);
            }else if (method.equals("post")){
                apiActionsModel.setPost(url);
            }
            actions.put(GetRequestName.getRequestName(url),apiActionsModel);
        });
        apiObjectModel.setName("token");
        apiObjectModel.setActions(actions);
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.writeValue(new File("src/test/resources/har/token.yaml"),apiObjectModel);
    }

    @Test
    public void runTest() throws IOException {
        ApiObjectModel apiObjectModel = ApiObjectModel.load("src/test/resources/har/tokenhelper.yaml");
        apiObjectModel.getActions().get("gettoken").run(null);
    }
}
