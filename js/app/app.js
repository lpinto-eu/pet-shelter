window.App = Ember.Application.create();

//window.App = Ember.Application.create({
//  LOG_TRANSITIONS: true // LOGGING ROUTE CHANGES
//});

App.ApplicationStore = DS.Store.extend();

App.ApplicationAdapter = DS.RESTAdapter.extend({
    host: 'http://localhost:8080',
    namespace: 'petshelter-webapp/api'
});

App.ApplicationSerializer = DS.RESTSerializer.extend({
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
