package gaea.hubeimobile.statics.common;

public enum ResultCode {

    SUCCESS(1,"成功"),
    PARAME_INVALIDE(1001,"参数无效");

    private  Integer code;
    private  String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    public Integer code(){
        return this.code;
    }

    public String message(){
        return this.message;
    }




}
