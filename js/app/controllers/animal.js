App.AnimalController = Ember.ObjectController.extend({
    isEditing: false,

    edit: function () {
        this.set('isEditing', true);
    },

    doneEditing: function () {
        this.set('isEditing', false);
        this.get('store').commit();
    },

    description: function () {
        return this.get("content.name") + " | " + this.get("content.age");
    }.property("content.name", "content.age"),
});
