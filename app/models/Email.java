package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

/**
 * Description:
 */
@Entity
public class Email extends Model{

    @Id
    public long id;
    @Constraints.Required
    public String firstName;
    @Constraints.Required
    public String lastName;
    @Constraints.Required
    @Constraints.Email
    public String emailAddress;

    public static List<Email> all() {
        return find.all();
    }

    public static Finder<Long,Email> find = new Finder(Long.class, Email.class);

    public static void create(Email email) {
        email.save();
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }

    public static List<Email> filterEmailAddress(String filter) {
        List<Email> emails;
        if (filter == "") {
            emails = all();
        } else {
            emails = find.where().ilike("emailAddress", "%" + filter + "%").findList();
        }
        return emails;
    }
}
