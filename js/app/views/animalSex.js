App.AnimalSexView = Ember.View.extend({
    tagName: "i",
    classNameBindings: ['aa'],

    'aa': function() {
        if (this.content === "F") {
            return "ion-female text-danger";
        } else if (this.content === "M") {
            return "ion-male text-primary";
        }
    }.property('content')
});
