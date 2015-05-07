/*
 *
    @author Vítor Martins - varmartins@varmartins.com
*/

App.Organization = DS.Model.extend({
    created:       DS.attr(),
    updated:       DS.attr(),
    name:          DS.attr('string'),
    logo:          DS.attr(),
//    animals:       DS.hasMany('animal'),

    bgimg: function () {
        return ('background-image: url("' + this.get("logo") + '");').htmlSafe();;
    }.property('logo')
});