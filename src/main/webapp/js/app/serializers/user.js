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