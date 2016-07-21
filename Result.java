
import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * Created by yemeng on 16/7/21.
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -4699713095477151086L;
    //操作成功
    public static final int RESULT_OK = 0;
    //操作失败
    public static final int RESULT_FAIL = 1;
    //操作错误，提示,String型
    public static final int RESULT_ERROR = 2;

    /**
     * 是否成功
     */
    private int code;

    /**
     * 信息,只有在code＝＝RESULT_ERROR才有
     */
    private String message;

    /**
     * 数据
     */
    private T data;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result() {
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 正常操作时返回数据
     *
     * @param data 需要返回的数据
     */
    public Result(T data) {
        this.code = RESULT_OK;
        this.data = data;
    }

    /**
     * 操作失败
     */
    public Result<T> failure() {
        this.code = RESULT_FAIL;
        return this;
    }

    /**
     * 操作异常，返回提示信息
     *
     * @param message 提示信息
     */
    public Result<T> msg(String message) {
        code = RESULT_ERROR;
        this.message = message;
        return this;
    }

    public String genJSON() {
        return JSON.toJSONString(this);
    }
}
