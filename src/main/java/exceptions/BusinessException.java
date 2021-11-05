package exceptions;

public class BusinessException extends  Exception{
    public BusinessException(String message){
        super(message);
    }

    @Override
    public String getMessage() {
        return "Lỗi nhập thông tin sinh viên" + super.getMessage();
    }
}
