import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Created by Administrator on 2017/2/13.
 */
public class Log4jTest {

    @Test
    public void testLog(){
        Logger rootLogger = Logger.getRootLogger();
        rootLogger.debug("demo");
    }
}
