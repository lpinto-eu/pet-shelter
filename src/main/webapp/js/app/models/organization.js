/*
 *
    @author Vítor Martins - varmartins@varmartins.com
*/

App.Organization = DS.Model.extend({
    created:       DS.attr('isodate'),
    updated:       DS.attr('isodate'),
    name:          DS.attr('string'),
    logo:          DS.attr(),
    capacity:      DS.attr('number'),
//    animals:       DS.hasMany('animal'),

    bgimg: function () {
        return ('background-image: url("' + this.get("logo") + '");').htmlSafe();;
    }.property('logo')
});