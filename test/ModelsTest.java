import models.User;
import org.junit.Before;
import org.junit.Test;
import play.test.WithApplication;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNull;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.fakeGlobal;
import static play.test.Helpers.inMemoryDatabase;

public class ModelsTest extends WithApplication {

    @Before
    public void setUp() {
        start(fakeApplication(inMemoryDatabase(), fakeGlobal()));
    }

    @Test
    public void shouldCreateAndRetrieveUser() {
        // Create
        User newUser = new User("test@tester.com", "testMe", "hashed");
        newUser.save();

        // Retrieve
        User retrievedUser = User.find.where().eq("email", "test@tester.com").findUnique();
        assertNotNull(retrievedUser);
        assertEquals("Test user is the same one we created", newUser.email, retrievedUser.email);
    }

    @Test
    public void shouldBeAbleToAuthenticateUser() {
        User newUser = new User("test2@tester.com", "testMe", "hashed");
        newUser.save();

        assertNotNull(User.authenticate("test2@tester.com", "hashed"));
        assertNull(User.authenticate("test2@tester.com", ""));
        assertNull(User.authenticate("test2@tester.com", "blabla"));
        assertNull(User.authenticate("te2@tester.com", "blabla"));

    }
}
