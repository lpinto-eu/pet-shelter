App.OrganizationsIndexRoute =  App.AuthenticatedRoute.extend({
    controllerName: 'Organizations',
    model: function() {
        return this.store.find("Organization");
    }
});

App.OrganizationsTableRoute = App.AuthenticatedRoute.extend({
    controllerName: 'Organizations',
    model: function() {
        return this.store.find('Organization');
    }
});

App.OrganizationsNewRoute = App.AuthenticatedRoute.extend({
    model: function(params) {
        return this.store.createRecord('Organization', params);
    }
});

