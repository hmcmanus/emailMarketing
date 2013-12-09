import models.Email;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static play.test.Helpers.*;

/**
 * Description:
 */
public class EmailIntegrationTest {

    @Test
    public void shouldSaveEmail() {
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                Email newEmail = new Email();
                newEmail.emailAddress = "test@test.com";
                newEmail.firstName = "TestFirst";
                newEmail.firstName = "TestLast";
                newEmail.save();
                assertTrue(Email.all().size() > 0);
            }
        });
    }
}
