import com.avaje.ebean.Ebean;
import models.User;
import play.GlobalSettings;
import play.libs.Yaml;

import java.io.File;
import java.util.List;

/**
 * Description:
 */
public class Global extends GlobalSettings {

    public void onStart(controllers.Application app) {
        // Check if the database is empty
        if (User.find.findRowCount() == 0) {
            Ebean.save((List) Yaml.load("initial-data.yml"));
        }
    }
}
