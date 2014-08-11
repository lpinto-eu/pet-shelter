App.AnimalsListController = Ember.ArrayController.extend({

    sortProperties: ['name'],

    actions: {
        pushSort: function(attribute) {
            if (this.get("sortProperties.firstObject") === attribute) {
                this.toggleProperty("sortAscending");
            } else {
                this.set("sortProperties", [attribute]);
            }
        }
    }
});
