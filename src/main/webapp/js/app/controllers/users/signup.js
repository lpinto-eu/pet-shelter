// https://github.com/WebCloud/EmberJS-Auth-Example/tree/master/app
//http://webcloud.github.io/blog/2014/04/07/emberjs-authentication-the-right-way-javascript-version/

App.UsersSignupController = Ember.Controller.extend({
  // requires the sessions controller
  needs: ['sessions'],
  
  actions: {
    // the user creation action
    createUser: function() {
      // get the data from the form
      var data = this.getProperties(
                    'name',
                    'email',
                    'password'
                  );
      

      // get the model passed from the UserSignupRoute
      user = this.get('model');

      // set the properties for the user based on the data from the form
      user.setProperties(data);

      // Save the user object into the database through the api
      // to understand the Ember.DS (Ember Data) calls to api read more on:
      // http://emberjs.com/guides/models/connecting-to-an-http-server
      var self = this; //
      user.save().then(function(user) {
        // clear the form data
        // // record.setProperties({ firstName: 'Charles', lastName: 'Jolley' });
        self.setProperties({
          name:                  null,
          email:                 null,
          password:              null});


        // get the sessions controller object, defined on line 3
        // and set the properties to proceed to the login action
        sessionsController = self.get('controllers.sessions'),
        sessionsController.setProperties({
          name: data.name,
          username_or_email: data.email,
          password: data.password});

        // remove the record from the localstorage to avoid duplication on the users list
        // as it will come from the api
        user.deleteRecord();

        // call the loginUser action to authenticate the created user
        sessionsController.send('loginUser');
        
      }, function(error) {
            // if the api request returns a HTTP status 422 create an errors object to return to the user
            if (error.status === 422) {
              errs = JSON.parse(error.responseText);
              user.set('errors', errs.errors);
            };
        });
    }
  }

});