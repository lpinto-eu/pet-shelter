App.AnimalsRoute = Ember.Route.extend({
    model: function () {
        return App.Animal.find();
    }
});

App.AnimalsNewRoute = Ember.Route.extend({
    model: function() {
        return App.Animal.createRecord();
    }
});
