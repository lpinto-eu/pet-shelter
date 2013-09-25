App.AnimalsController = Ember.ArrayController.extend({
    pushSort: function(attribute) {
        if (this.get("sortProperties.firstObject") == attribute) {
            this.toggleProperty("sortAscending");
        } else {
            this.set("sortProperties", [attribute]);
        }
    },
});
