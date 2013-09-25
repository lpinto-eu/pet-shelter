/*
App.AnimalsNewController = Ember.ArrayController.extend({

save: function () {
        var name = this.get('name');
        if (!title.trim()) { return; }
        var age = this.get('age');
        if (!age.trim()) { return; }

        // Create the new Animal model
        var animal = App.Animal.createRecord({
              name: name,
              age: age
        });

        // Save the new model
        animal.save();
    },
});
*/
App.AnimalsNewController = Ember.ObjectController.extend({
    save: function() {
        this.get("model.transaction").commit();
        this.get("target").transitionTo("animals");
    }
});
