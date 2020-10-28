package gaea.hubeimobile.statics.base;

import lombok.Data;

@Data
public class BaseResponse {
    private  int result;
    private  String msg;
    private  Object data;

    public BaseResponse(int result, String msg) {
        this.result = result;
        this.msg = msg;
    }

    public BaseResponse(int result, String msg, Object data) {
        this.result = result;
        this.msg = msg;
        this.data = data;
    }

    public BaseResponse(int result) {
        this.result = result;
    }


    public static BaseResponse fail() {
        return new BaseResponse(0, GlobalExceptionHandler.DEFAULT_ERROR_MESSAGE);
    }

    public static BaseResponse fail(int result,String msg) {
        return new BaseResponse(result, msg);
    }

    public static BaseResponse fail(String msg) {
        return new BaseResponse(0, msg);
    }

    public static BaseResponse fail(int result) {
        return new BaseResponse(result);
    }

    public static BaseResponse success(String msg,Object data) {
        return new BaseResponse(1, msg,data);
    }

    public static BaseResponse success() {
        return new BaseResponse(1, "success");
    }

    public static BaseResponse success(String msg) {
        return new BaseResponse(1, msg);
    }


}
