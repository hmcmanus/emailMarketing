package models;

import play.data.Form;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 */
@Entity
public class Email extends Model{

    @Id
    public long id;
    @Constraints.Required
    public String name;

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
}
