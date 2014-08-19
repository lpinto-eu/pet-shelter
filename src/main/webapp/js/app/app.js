//window.App = Ember.Application.create();

window.App = Ember.Application.create({
  LOG_TRANSITIONS:          true,
  LOG_TRANSITIONS_INTERNAL: true
});

App.ApplicationStore = DS.Store.extend();

App.ApplicationAdapter = DS.RESTAdapter.extend({
    host: 'http://localhost:8080',
    namespace: 'petshelter/api'
});


App.AnimalSerializer = DS.RESTSerializer.extend({
    /* add root element on incoming json */
    normalizePayload: function(payload) {
        return {animal: payload};
    },
    /* remove root element from outgoing json */
    serializeIntoHash: function(hash, type, record, options) {
        Ember.merge(record.get("data"), this.serialize(record, options));
        Ember.merge(hash, record.get("data"));
    }
});

App.SessionSerializer = DS.RESTSerializer.extend({
    /* add root element on incoming json */
    normalizePayload: function(payload) {
        return {api_key: payload};
    },
    /* remove root element from outgoing json */
    serializeIntoHash: function(hash, type, record, options) {
        Ember.merge(record.get("data"), this.serialize(record, options));
        Ember.merge(hash, record.get("data"));
    }
});

App.UserSerializer = DS.RESTSerializer.extend({
    /* add root element on incoming json */
    normalizePayload: function(payload) {
        return {user: payload};
    },
    /* remove root element from outgoing json */
    serializeIntoHash: function(hash, type, record, options) {
        Ember.merge(record.get("data"), this.serialize(record, options));
        Ember.merge(hash, record.get("data"));
    }
});



/* ************************************************************************************ */
App.User = DS.Model.extend({
  name:                  DS.attr('string'),
  password:              DS.attr('string'),
  apiKeys:               DS.hasMany('apiKey'),
  errors:                {}
});

App.ApiKey = DS.Model.extend({
  accessToken: DS.attr('string'),
  user:        DS.belongsTo('user', {
    async: true
  })
});


App.ApiKeyAdapter = DS.LSAdapter.extend({
  namespace: 'emberauth-keys'
});



App.ApplicationController = Ember.Controller.extend({
  // requires the sessions controller
  needs: ['sessions'],

  // creates a computed property called currentUser that will be
  // binded on the curretUser of the sessions controller and will return its value
  currentUser: (function() {
    return this.get('controllers.sessions.currentUser');
  }).property('controllers.sessions.currentUser'),

  // creates a computed property called isAuthenticated that will be
  // binded on the curretUser of the sessions controller and will verify if the object is empty
  isAuthenticated: (function() {
    return !Ember.isEmpty(this.get('controllers.sessions.currentUser'));
  }).property('controllers.sessions.currentUser')
});
