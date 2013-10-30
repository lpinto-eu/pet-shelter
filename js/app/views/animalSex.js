App.AnimalSexView = Ember.View.extend({
    tagName: "i",
    classNameBindings: ['aa'],

    'aa': function() {
        if (this.content === "F") {
            return "fa fa-female text-danger";
        } else if (this.content === "M") {
            return "fa fa-male text-primary";
        }
    }.property('content')
});
