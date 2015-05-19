/*
 *
 * @author Vítor Martins - varmartins@varmartins.com
 * @author Luis Pinto - luis.pinto@petshelter.pt
 */

/*
 * Organization
 */
App.OrganizationsRoute = App.AuthenticatedRoute.extend({
    model: function () {
        return this.store.find("Organization");
    }
});

App.OrganizationsOrganizationIndexRoute = App.AuthenticatedRoute.extend({
    controllerName: 'OrganizationsOrganization'
});

App.OrganizationsNewRoute = App.AuthenticatedRoute.extend({
    model: function (params) {
        return this.store.createRecord('Organization', params);
    }
});

/*
 * Animals
 */
App.OrganizationsOrganizationAnimalsRoute = App.AuthenticatedRoute.extend({
    controllerName: 'Animals',
    model: function () {
        this.store.find('Animal');
        return this.store.find('Organization', this.modelFor('organizationsOrganization').get("id")).then(function (o) {return o.get("animals");});
    }
});

App.OrganizationsOrganizationAnimalsIndexRoute =  App.AuthenticatedRoute.extend({
    controllerName: 'Animals',
    model: function() {
        return this.store.find('Organization', this.modelFor('organizationsOrganization').get("id")).then(function (o) {return o.get("animals");});
    }
});

App.OrganizationsOrganizationAnimalNewRoute = App.AuthenticatedRoute.extend({
    model: function(params) {
        return {organization: this.modelFor('organizationsOrganization').get("id")};
    }
});
