window.App = Ember.Application.create();

App.ApplicationStore = DS.Store.extend();

App.AlertController = Ember.Controller.extend({
    alert: false,
    observeAlert: function () {
        if (this.alert != false) {
            $('#flash').addClass('alert alert-' + this.alert[0] + ' alert-dismissable');
            $('#flash span:last').text(this.alert[1]);
            $('#flash').fadeIn();
        } else {
            $('#flash').hide();
        }
    }.observes('alert')
});