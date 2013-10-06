App.AnimalController = Ember.ObjectController.extend({
    isEditing: false,

    doneEditing: function () {
        this.set('isEditing', false);
        this.get('store').commit();
    },

    description: function () {
        return this.get("content.name") + " | " + this.get("content.age");
    }.property("content.name", "content.age"),
});
