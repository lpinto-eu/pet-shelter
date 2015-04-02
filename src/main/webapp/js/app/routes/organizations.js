/*
 *
    @author Vítor Martins - varmartins@varmartins.com
*/

App.OrganizationsRoute =  App.AuthenticatedRoute.extend({
    model: function() {
        return this.store.find("Organization");
    }
});

App.OrganizationsNewRoute = App.AuthenticatedRoute.extend({
    model: function(params) {
        return this.store.createRecord('Organization', params);
    }
});

