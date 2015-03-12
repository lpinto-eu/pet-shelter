/*
 * Helpers for handlebars
 */
Ember.Handlebars.registerBoundHelper('date', function (date) {
    return moment(date).fromNow();
});

Ember.Handlebars.registerBoundHelper('dateFormat', function (date) {
    return moment(date).format('MM/DD/YYYY, h:mm:ss');
});

Ember.Handlebars.registerBoundHelper("getStyle", function(o){
    return "background-color:" + o + ";" + "border-radius: 10px; padding: 5px; display: inline-block; vertical-align: middle; border:1px solid black"; 
});