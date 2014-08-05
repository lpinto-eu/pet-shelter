App.TimeagoView = Ember.View.extend({
    tagName: 'abbr',
    classNames: ['timeago'],
    attributeBindings: ['title'],
    title: null,

    didInsertElement: function() {
        this.$().timeago();
    }
});
