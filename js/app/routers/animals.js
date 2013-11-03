App.AnimalsRoute = Ember.Route.extend({
    model: function () {
        return this.store.find("Animal");
    }
});

App.AnimalsNewRoute = Ember.Route.extend({
    model: function() {
        return this.store.createRecord("Animal");
    }
});
