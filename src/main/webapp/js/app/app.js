//window.App = Ember.Application.create();

window.App = Ember.Application.create({
  LOG_TRANSITIONS:          true,
  LOG_TRANSITIONS_INTERNAL: true
});

App.ApplicationStore = DS.Store.extend();
