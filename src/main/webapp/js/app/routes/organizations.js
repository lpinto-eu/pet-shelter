/*
 *
 @author Vítor Martins - varmartins@varmartins.com
 */

App.OrganizationsRoute = App.AuthenticatedRoute.extend({
    model: function () {
        return this.store.find("Organization");
    }
});

App.OrganizationsNewRoute = App.AuthenticatedRoute.extend({
    model: function (params) {
        return this.store.createRecord('Organization', params);
    }
});

App.OrganizationsOrganizationAnimalsRoute = App.AuthenticatedRoute.extend({
    renderTemplate: function () {
        this.render('animals');
    },
    controllerName: 'Animals',
    model: function (params) {
        return this.store.find("Animal").filter(function (el) {
            return el.organization.id = params.organization_id;
        });
    }
});
//
//App.OrganizationAnimalsIndexRoute = App.AuthenticatedRoute.extend({
//    controllerName: 'Animals',
//    model: function () {
//        return this.store.find("Animal");
//    }
//});
//
//
//App.OrganizationAnimalsNewRoute = App.AuthenticatedRoute.extend({
//    model: function (params) {
//        return this.store.createRecord('Animal', params);
//    }
//});

