/*
 * 
    @author Vítor Martins - varmartins@varmartins.com
*/

App.ClinicalEpisode = DS.Model.extend({
    animal: DS.belongsTo('animal'),
    type: DS.belongsTo('clinicalEpisodeType'),
    date: DS.attr(),
    observations: DS.attr('string'),
});


