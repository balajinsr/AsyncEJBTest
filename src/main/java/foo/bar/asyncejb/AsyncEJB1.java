package foo.bar.asyncejb;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import java.util.concurrent.Future;
import java.util.logging.Logger;

@Stateless
public class AsyncEJB1 {

    private final Logger LOG = Logger.getLogger(getClass().getSimpleName());

    @Asynchronous
    public Future<Integer> simpleCall(int i) {
        LOG.info(">> simpleCall " + i + " Thread name: " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOG.info("<< simpleCall " + i);
        return new AsyncResult<>(i);
    }

}
