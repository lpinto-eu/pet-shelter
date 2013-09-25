App.Store = DS.Store.extend({
    adapter: DS.LSAdapter.create()
});

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

/*
App.Store = DS.Store.extend({
    adapter: 'App.LSAdapter'
});

App.LSAdapter = DS.LSAdapter.extend({
    namespace: 'app-emberjs'
});
*/
