package cjcx.Exception;

/**
 * Created by Administrator on 2017/2/20.
 */
public class TooMuchDataException extends RuntimeException {
    public TooMuchDataException(){
        super();
    }

    public TooMuchDataException(String message){
        super(message);
    }
}
