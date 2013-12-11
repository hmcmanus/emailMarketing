import com.avaje.ebean.Ebean;
import com.google.common.collect.ImmutableMap;
import org.junit.Before;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;
import play.libs.Yaml;
import play.mvc.Result;

import java.util.List;

import static junit.framework.Assert.assertEquals;

import static junit.framework.Assert.assertNull;
import static play.test.Helpers.*;

/**
 * Description:
 */
public class LoginTest {

    @Before
    public void setUp() {
        start(fakeApplication(inMemoryDatabase(), fakeGlobal()));
        Ebean.save((List) Yaml.load("test-data.yml"));
    }

    @Test
    public void shouldAuthenticateUser() {
        Result result = callAction(
            controllers.routes.ref.Application.authenticate(),
            fakeRequest().withFormUrlEncodedBody(ImmutableMap.of(
                "email", "test@marketing.com",
                "password", "tester"))
        );
        assertEquals(303, status(result));
        assertEquals("test@marketing.com", session(result).get("email"));
    }

    @Test
    public void shouldRestrictBadUser() {
        Result result  = callAction(
            controllers.routes.ref.Application.authenticate(),
            fakeRequest().withFormUrlEncodedBody(ImmutableMap.of(
                "email", "test@marketing.com",
                "password", "rubbish"))
        );
        assertEquals(400, status(result));
        assertNull(session(result).get("email"));
    }
}
