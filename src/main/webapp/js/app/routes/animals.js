App.AnimalsIndexRoute =  App.AuthenticatedRoute.extend({
    controllerName: 'Animals',
    model: function() {
        return this.store.find("Animal");
    }
});

App.AnimalsListRoute = App.AuthenticatedRoute.extend({
    controllerName: 'Animals',
    model: function() {
        return this.store.find('Animal');
    }
});

App.AnimalsRoute = App.AuthenticatedRoute.extend({
    controllerName: 'Animals',
    model: function() {
        return this.store.find("Animal");
    }
});

App.AnimalsNewRoute = App.AuthenticatedRoute.extend({
    model: function(params) {
        return this.store.createRecord('Animal', params);
    }
});