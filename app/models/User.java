package models;

import org.mindrot.jbcrypt.BCrypt;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(name = "`user`")
public class User extends Model {

    @Id
    public String email;

    public String name;

    @Constraints.MinLength(5)
    @Constraints.Required
    public String password;

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = createPassword(password);
    }

    private String createPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static Finder<String, User> find = new Finder<String, User>(String.class, User.class);

    public static User authenticate(String email, String password) {
        User user = find.where().eq("email", email).findUnique();
        if (user != null && !BCrypt.checkpw(password, user.password)) {
            user = null;
        }
        return user;
    }
}
