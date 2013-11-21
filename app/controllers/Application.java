package controllers;

import models.Email;
import play.*;
import play.data.Form;
import play.db.ebean.Model;
import play.db.ebean.Model.*;
import play.mvc.*;

import views.html.createEmail;
import views.html.updateEmail;
import views.html.index;

import static play.data.Form.form;
import java.util.ArrayList;
import java.util.List;

public class Application extends Controller {

    static Form<Email> emailForm = form(Email.class);

    public static Result index() {
        return redirect(controllers.routes.Application.emails(""));
    }

    public static Result emails(String filter) {
        return ok(
            views.html.index.render(Email.filterEmailAddress(filter), emailForm, filter)
        );
    }

    public static Result newEmail() {
        Form<Email> emailForm = form(Email.class);
        return ok(
            createEmail.render(emailForm)
        );
    }

    public static Result saveEmail() {
        Form<Email> filledForm = emailForm.bindFromRequest();
        if (filledForm.hasErrors()) {
            return badRequest(createEmail.render(filledForm)
            );
        } else {
            filledForm.get().save();
            flash("success", "Email " + filledForm.get().emailAddress + " has been added");
            return redirect(controllers.routes.Application.emails(""));
        }
    }

    public static Result deleteEmail(Long id) {
        Email.delete(id);
        return redirect(controllers.routes.Application.emails(""));
    }

    public static Result editEmail(Long id) {
        Form<Email> editForm = form(Email.class).fill(
            Email.find.byId(id)
        );
        return ok(
                updateEmail.render(id,editForm)
        );
    }

    public static Result updateEmail(Long id) {
        Form<Email> filledForm = emailForm.bindFromRequest();
        if (filledForm.hasErrors()) {
            return badRequest(updateEmail.render(id,filledForm));
        } else {
            filledForm.get().update(id);
            flash("success", "Email " + filledForm.get().emailAddress + " has been updated");
            return redirect(controllers.routes.Application.emails(""));
        }
    }
}
