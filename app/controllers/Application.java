package controllers;

import models.Email;
import play.*;
import play.data.Form;
import play.db.ebean.Model;
import play.db.ebean.Model.*;
import play.mvc.*;

import views.html.index;

public class Application extends Controller {

    static Form<Email> emailForm = Form.form(Email.class);

    public static Result index() {
        return redirect(controllers.routes.Application.emails());
    }

    public static Result emails() {
        return ok(
            views.html.index.render(Email.all(), emailForm)
        );
    }

    public static Result newEmail() {
        Form<Email> filledForm = emailForm.bindFromRequest();
        if (filledForm.hasErrors()) {
            return badRequest(
                    views.html.index.render(Email.all(), filledForm)
            );
        } else {
            Email.create(filledForm.get());
            return redirect(routes.Application.emails());
        }
    }

    public static Result deleteEmail(Long id) {
        Email.delete(id);
        return redirect(routes.Application.emails());
    }
}
