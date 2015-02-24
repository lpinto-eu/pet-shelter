App.Animal = DS.Model.extend({
    name: DS.attr('string'),
    age: DS.attr(),
    species: DS.attr('string'),
    breed: DS.attr('string'),
    drugs: DS.attr('string'),
    created: DS.attr('isodate'),
    sex: DS.attr('string'),
    sterilized: DS.attr('boolean'),
    picture: DS.attr('string'),
    observations: DS.attr('string'),

    recordDate: function () {
        return moment(this.get("created")).format('MMMM Do YYYY, h:mm:ss a');
    }.property("content.created")
});
