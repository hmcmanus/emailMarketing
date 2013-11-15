package controllers;

import models.Email;
import play.*;
import play.data.Form;
import play.db.ebean.Model;
import play.db.ebean.Model.*;
import play.mvc.*;

import views.html.createEmail;
import views.html.index;

import static play.data.Form.form;

public class Application extends Controller {

    static Form<Email> emailForm = form(Email.class);

    public static Result index() {
        return redirect(controllers.routes.Application.emails());
    }

    public static Result emails() {
        return ok(
            views.html.index.render(Email.all(), emailForm)
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
            return redirect(controllers.routes.Application.emails());
        }
    }

    public static Result deleteEmail(Long id) {
        Email.delete(id);
        return redirect(controllers.routes.Application.emails());
    }
}
