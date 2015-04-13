/*
 * @author Vítor Martins - varmartins@varmartins.com
 */


App.StatusDisplayComponent = Ember.Component.extend({
    tagName: "i",
    classNameBindings: ['status-icon'],
    
'status-icon': function() {
        switch(this.content) {
            case 1:
                return "fa fa-life-saver text-primary";
            case 2:
                return "fa fa-home text-primary";
            case 3:
                return "fa fa-child text-primary";
            case 4:
                return "fa fa-paper-plane-o text-primary";
            case 5:
                return "fa fa-circle-thin text-primary";
        }
    }.property('content')
});

