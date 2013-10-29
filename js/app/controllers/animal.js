App.AnimalController = Ember.ObjectController.extend({
    isEditing: false,

    doneEditing: function () {
        this.set('isEditing', false);
        this.get('store').commit();
    },

    isMale: function () {
        return this.get('sex') === 'M';
    }.property('content.sex'),

    isFemale: function () {
        return this.get('sex') === 'F';
    }.property('content.sex'),

    description: function () {
        return this.get("content.name") + " | " + this.get("content.age");
    }.property("content.name", "content.age"),
});
