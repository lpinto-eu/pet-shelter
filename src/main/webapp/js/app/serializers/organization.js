/*
 * 
    @author Vítor Martins - varmartins@varmartins.com
*/

App.OrganizationSerializer = DS.RESTSerializer.extend({
    /* add root element on incoming json */
    normalizePayload: function(payload) {
        return {organization: payload};
    },
    /* remove root element from outgoing json */
    serializeIntoHash: function(hash, type, record, options) {
        Ember.merge(record.get("data"), this.serialize(record, options));
        Ember.merge(hash, record.get("data"));
    }
});