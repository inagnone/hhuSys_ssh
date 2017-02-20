package cjcx.Exception;

/**
 * Created by Administrator on 2017/2/18.
 */
public class UserException extends RuntimeException{
    public UserException(){
        super();
    }

    public UserException(String message){
        super(message);
    }
}
