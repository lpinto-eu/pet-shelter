App.Router.map(function() {
    this.resource('sessions', function() {
        this.route('logout');
        this.route('login');
    });
    this.resource('users', function() {
        this.route('signup');
        this.route('user', {path: '/user/:user_id'}, function () {
            this.route('organizations');
        });
    });

    this.resource('animals', function() {
        this.route('list'),
        this.route('table'),
        this.route('new');
        this.route("animal", {path: ":animal_id"});
    });
    
    this.resource('project', { path: 'project/:id' }, function () {
    this.route('participants');
  });
    
      this.resource('organizations', function() {
        this.route('table'),
        this.route('new');
        this.route("organization", {path: ":organization_id"}, function () {
            this.route('users');
        });
    });
});

App.ApplicationRoute = Ember.Route.extend({
  actions: {
    // create a global logout action
    logout: function() {
      // get the sessions controller instance and reset it to then transition to the sessions route
      this.controllerFor('sessions').reset();
      this.transitionTo('sessions');
    },
    error: function(reason, transition) {
        alert(reason.status);
        console.log(reason.status + " - " + reason);
        }
  }
});

App.SessionsRoute = Ember.Route.extend({
    // setup the SessionsController by resetting it to avoid data from a past authentication
      setupController: function(controller, context) {
        controller.reset();
      },

      beforeModel: function(transition) {
        // before proceeding any further, verify if the token property is not empty
        // if it is, transition to the secret route
        if (!Ember.isEmpty(this.controllerFor('sessions').get('token'))) {
          this.transitionTo('animals');
        }
      }
});

// create a base object for any authentication protected route with the required verifications
App.AuthenticatedRoute = Ember.Route.extend({
    // verify if the token property of the sessions controller is set before continuing with the request
    // if it is not, redirect to the login route (sessions)
    beforeModel: function(transition) {
        if (Ember.isEmpty(this.controllerFor('sessions').get('token'))) {
            return this.redirectToLogin(transition);
        }
    },
    // Redirect to the login page and store the current transition so we can
    // run it again after login
    redirectToLogin: function(transition) {
        this.controllerFor('sessions').set('attemptedTransition', transition);
        return this.transitionTo('sessions');
    },

    actions: {
        // recover from any error that may happen during the transition to this route
        error: function(reason, transition) {
          // if the HTTP status is 401 (unauthorised), redirect to the login page
          if (reason.status === 401) {
              this.send('logout');
            //this.redirectToLogin(transition);
          } else {
            console.log('unknown problem');
          }
        }
    }
});

App.UsersSignupRoute = Ember.Route.extend({
  model: function() {
    // define the model for the UsersSignupController as a new record from the User model
    return this.store.createRecord('user');
  }
//  afterModel: function() {
//    // instantiating a jQuery/Twitter Bootstrap datepicker component
//    Ember.$(function() {
//      Ember.$('.input-group.date').datepicker
//        startView: 2,
//        autoclose: true
//    })
//  }
});


App.LoadingRoute = Ember.Route.extend({
    beforeModel: function(params) {
        Ember.$('.navbar-header').hide();
    },
    afterModel: function(params) {
        Ember.$('.navbar-header').show();
    }
});
