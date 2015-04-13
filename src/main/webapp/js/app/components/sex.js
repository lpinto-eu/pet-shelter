/*
 * @author Vítor Martins - varmartins@varmartins.com
 */


App.SexDisplayComponent = Ember.Component.extend({
    tagName: "i",
    classNameBindings: ['sex-icon'],
    
    'sex-icon': function() {
        if (this.content === "F") {
            return "fa fa-venus text-pink";
        } else if (this.content === "M") {
            return "fa fa-mars text-blue";
        }
    }.property('content')
});

