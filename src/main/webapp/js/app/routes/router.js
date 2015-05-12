App.Router.map(function () {
    this.route('error');

    this.route('sessions', function () {
        this.route('logout');
        this.route('login');
    });

    this.route('users', function () {
        this.route('signup');
        this.route('user', {path: ':user_id'}, function () {
            this.route('organizations');
        });
    });

    this.route('organizations', function () {
        this.route('new');
        this.route("organization", {path: ":organization_id"}, function () {
            this.route('animals', function () {
                this.route('new');
                this.route("animal", {path: ":animal_id"});
            });
        });
    });

    this.route('animals');
});

App.ApplicationRoute = Ember.Route.extend({
    actions: {
        // create a global logout action
        logout: function () {
            this.controllerFor('sessions').reset();
            this.transitionTo('application');
        },
        error: function (reason, transition) {
            this.handleError(reason, transition);
        }
    },
    events: {
        error: function (reason, transition) {
            this.handleError(reason, transition);
        }
    },

    handleError: function (reason, transition) {
        if (reason.status === 401) {
            this.send('logout');

        } else if (reason.status === 409) {
            alert(reason.responseText);

        } else {
            console.log(reason.status + " - " + reason.statusText + " | " + reason.responseText);
            this.transitionTo("error", {queryParams: {status: reason.status}});
        }
    }
});

App.SessionsRoute = Ember.Route.extend({
    // setup the SessionsController by resetting it to avoid data from a past authentication
    setupController: function (controller, context) {
        controller.reset();
    },
    beforeModel: function (transition) {
        // before proceeding any further, verify if the token property is not empty
        // if it is, transition to the secret route
        if (!Ember.isEmpty(this.controllerFor('sessions').get('token'))) {
            this.transitionTo('organizations');
        }
    }
});

// create a base object for any authentication protected route with the required verifications
App.AuthenticatedRoute = Ember.Route.extend({
    // verify if the token property of the sessions controller is set before continuing with the request
    // if it is not, redirect to the login route (sessions)
    beforeModel: function (transition) {
        if (Ember.isEmpty(this.controllerFor('sessions').get('token'))) {
            return this.redirectToLogin(transition);
        }
    },
    // Redirect to the login page and store the current transition so we can
    // run it again after login
    redirectToLogin: function (transition) {
        this.controllerFor('sessions').set('attemptedTransition', transition);
        return this.transitionTo('sessions');
    }
});

App.UsersSignupRoute = Ember.Route.extend({
    model: function () {
        // define the model for the UsersSignupController as a new record from the User model
        return this.store.createRecord('user');
    }
});
