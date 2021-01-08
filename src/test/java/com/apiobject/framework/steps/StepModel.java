package com.apiobject.framework.steps;

import com.apiobject.framework.global.ApiLoader;
import com.apiobject.framework.global.GlobalVariables;
import com.apiobject.utils.PlaceholderUtils;
import com.wechatinterface.department.Demo_01_base;
import io.restassured.response.Response;
import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * 用例中的step对象
 */
public class StepModel {
    /**
     * 1、需要定义AssertModel类
     */
    private static final Logger logger = LoggerFactory.getLogger(Demo_01_base.class);
    private String api;
    private String action;
    private ArrayList<String> actualParameter;
    private ArrayList<AssertModel> asserts;
    private HashMap<String,String> save;
    private HashMap<String, String> saveGlobal;
    /**
     * 需要定义setResult类
     */
    private ArrayList<String> finalActualParaeter = new ArrayList<>();
    private HashMap<String, String> stepVariables= new HashMap<>();
    private StepResult stepResult = new StepResult();
    private ArrayList<Executable> assertList = new ArrayList<>();

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<String> getActualParameter() {
        return actualParameter;
    }

    public void setActualParameter(ArrayList<String> actualParameter) {
        this.actualParameter = actualParameter;
    }

    public ArrayList<AssertModel> getAsserts() {
        return asserts;
    }

    public void setAsserts(ArrayList<AssertModel> asserts) {
        this.asserts = asserts;
    }

    public HashMap<String, String> getSave() {
        return save;
    }

    public void setSave(HashMap<String, String> save) {
        this.save = save;
    }

    public HashMap<String, String> getSaveGlobal() {
        return saveGlobal;
    }

    public void setSaveGlobal(HashMap<String, String> saveGlobal) {
        this.saveGlobal = saveGlobal;
    }

    public StepResult run(HashMap<String,String> testCaseVariables){
        /**
         * 3、需要定义AssertModel类
         */
        if(actualParameter !=null){
            finalActualParaeter.addAll(PlaceholderUtils.resolveList(actualParameter,testCaseVariables));
        }
        /**
         * 4、根据case中配置的API对象和action信息，取出并执行相应的action
         */
        Response response = ApiLoader.getAction(api, action).run(finalActualParaeter);
        /**
         * 5、存储save
         */
        if(save !=null){
            save.forEach((variablesName, path)->{
                String value = response.path(path.toString());
                stepVariables.put(variablesName, value);
                logger.info("局部变量更新"+stepVariables);
            });
        }
        /**
         * 6、存储saveGlobal
         */
        if(saveGlobal !=null){
            saveGlobal.forEach((variablesName, path)->{
                String value = response.path(path.toString());
                GlobalVariables.getGlobalVariables().put(variablesName, value);
                logger.info("更新全局变量"+GlobalVariables.getGlobalVariables());
            });
        }
        /**
         * 7、处理软断言需要的中间断言数据
         */
        if (asserts!=null){
            asserts.stream().forEach(assertModel -> {
                assertList.add(()->{
                    assertThat(assertModel.getReason(),response.path(assertModel.getActual()).toString(),equalTo(assertModel.getExpect()));
                });
            });
        }
        /**
         * 将response和断言结果存储到stepResult对象中返回
         */
        stepResult.setAssertList(assertList);
        stepResult.setStepVariables(stepVariables);
        stepResult.setResponse(response);
        return stepResult;
    }




}
