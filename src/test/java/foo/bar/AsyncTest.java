package foo.bar;

import foo.bar.asyncejb.AsyncEJB1;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

@RunWith(Arquillian.class)
public class AsyncTest {

    private static final Logger LOG = Logger.getLogger(AsyncTest.class.getSimpleName());

    @Deployment
    public static WebArchive deploy() throws Exception {

        WebArchive war = ShrinkWrap.create(WebArchive.class)
                .addClass(AsyncEJB1.class);

        return war;
    }

    @EJB
    private AsyncEJB1 asyncEJB1;

    @Test
    public void test() throws Exception {

        for (int i = 1; i <= 11; i++) {
            try {
                Future<Integer> asyncResult = asyncEJB1.simpleCall(i);
            } catch (Exception e) {
                LOG.log(Level.SEVERE, "Error while scheduling task " + i, e);
                throw e;
            }
            LOG.info("Submitted task " + i);
        }

    }

}
