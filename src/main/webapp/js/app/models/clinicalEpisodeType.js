/*
 * 
    @author Vítor Martins - varmartins@varmartins.com
*/

App.ClinicalEpisodeType = DS.Model.extend({
    clinicalEpisodes: DS.hasMany('clinicalEpisodes'),
    name: DS.attr('string'),
    created: DS.attr('isodate'),
    update: DS.attr('isodate'),
    observations: DS.attr('string'),
    enable: DS.attr('boolean'),
    
    recordDate: function () {
        return moment(this.get("created")).format('MMMM Do YYYY, h:mm:ss a');
    }.property("content.created"),
});

