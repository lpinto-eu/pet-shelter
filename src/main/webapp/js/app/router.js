App.Router.map(function() {
    this.resource('animals', function() {
        this.route('list'),
        this.route('new');
        this.route("animal", {path: ":animal_id"});
    });
});

App.IndexRoute = Ember.Route.extend({
    model: function() {
        return this.store.find("Animal");
    }
});

App.AnimalsIndexRoute = Ember.Route.extend({
    model: function() {
        return this.store.find("Animal");
    }
});

App.AnimalsListRoute = Ember.Route.extend({
    model: function() {
        return this.store.find('Animal');
    }
});

App.AnimalsNewRoute = Ember.Route.extend({
    model: function(params) {
        return this.store.createRecord('Animal', params);
    }
});
