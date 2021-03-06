package com.volunteer.demo.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


public class JsonResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String RESULT_CODE = "resultCode";
    public static final String ERR_DESC = "errDesc";
    public static final String RESULT = "result";

    public static final String SUCCESS_CODE = "1";
    public static final String SESSION_INVALID_CODE = "2";
    public static final String FAILED_CODE = "3";
    public static final String DECRYPT_FAIL_CODE = "4";
    public static final String SESSION_INVALID_MESSAGE = "登陆失效";
    public static final String DECRYPT_FAIL_MESSAGE = "无效请求";
    public static final String SERVER_ERR_CODE = "5";

    private T result;

    private Map<String, Object> map = new HashMap<>();

    public static <T> JsonResult<T> getInstance() {
        return new JsonResult<T>();
    }

    public static <T> Map<String, Object> success(T result){
        JsonResult<T> jsonResult = getInstance();
        jsonResult.setResult(result);
        return jsonResult.getMap();
    }

    public static <T> Map<String,Object> success(String key,Object value){
        JsonResult<T> jsonResult = getInstance();
        jsonResult.setKey(key,value);
        return jsonResult.getMap();
    }


    public JsonResult() {
        map.put(RESULT_CODE, SUCCESS_CODE);
    }

    /**
     * 设置错误码
     *
     * @param errCode
     * @param errMsg
     */
    public void setErrCode(String errCode, String errMsg) {
        if (StringUtils.isBlank(errCode)) {
            errCode = FAILED_CODE;
        }
        map.put(RESULT_CODE, errCode);
        if (!StringUtils.isBlank(errMsg)) {
            map.put(ERR_DESC, errMsg);
        }
    }

    /**
     * 设置错误消息
     *
     * @param errMsg
     */
    public void setErrMsg(String errMsg) {
        if (!StringUtils.isBlank(errMsg)) {
            map.put(ERR_DESC, errMsg);
            map.put(RESULT_CODE, FAILED_CODE);
        }
    }

    /**
     * 设置为成功
     */
    public void success() {
        map.put(RESULT_CODE, SUCCESS_CODE);
        map.remove(ERR_DESC);
    }

    /**
     * 检查是否成功
     *
     * @return
     */
    public boolean isSuccess() {
        if (map.containsKey(RESULT_CODE)) {
            return SUCCESS_CODE.equals(map.get(RESULT_CODE));
        }
        return false;
    }

    public void setKey(String key, Object data) {
        map.put(key, data);
    }

    /**
     * 把一个对象插入到map中
     *
     * @param obj
     */
    public void insertObject(final Object obj) {
        ReflectionUtils.doWithFields(obj.getClass(), new ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                field.setAccessible(true);
                Object fieldValue = field.get(obj);
                if (fieldValue != null) {
                    map.put(field.getName(), fieldValue);
                }
            }
        });
    }

    public Map<String, Object> getMap() {
        return map;
    }

    /**
     * 设置结果信息
     *
     * @param result e
     */
    public void setResult(T result) {
        this.result = result;
        if (result != null) {
            map.put(RESULT, result);
        }
    }

    public T getResult() {
        return result;
    }

    /**
     * 获取错误描述
     */
    public String getErrDesc() {
        Object errDesc = map.get(ERR_DESC);
        return errDesc == null ? "" : errDesc.toString();
    }


}
