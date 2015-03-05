App.Animal = DS.Model.extend({
    name: DS.attr('string'),
    age: DS.attr(),
    species: DS.attr('string'),
    breed: DS.attr('string'),
    drugs: DS.attr('string'),
    created: DS.attr('isodate'),
    sex: DS.attr('string'),
    location: DS.attr(),
    sterilized: DS.attr('boolean'),
    picture: DS.attr('string'),
    observations: DS.attr('string'),

    recordDate: function () {
        return moment(this.get("created")).format('MMMM Do YYYY, h:mm:ss a');
    }.property("content.created"),
    
    isShelter: function () {
        return this.get('location') === 1;
    }.property('location'),

    isAdopted: function () {
        return this.get('location') === 2;
    }.property('location'),
    
    isFostering: function () {
        return this.get('location') === 3;
    }.property('location'),
});
