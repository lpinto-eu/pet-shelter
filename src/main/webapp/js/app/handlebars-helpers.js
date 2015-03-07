/*
 * Helpers for handlebars
 */
Ember.Handlebars.registerBoundHelper('date', function (date) {
    return moment(date).fromNow();
});

Ember.Handlebars.registerBoundHelper('dateFormat', function (date) {
    return moment(date).format('MM/DD/YYYY, h:mm:ss');
});