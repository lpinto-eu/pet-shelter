App.Animal = DS.Model.extend({
    name: DS.attr('string'),
    age: DS.attr(),
    species: DS.attr('string'),
    breed: DS.attr('string'),
    drugs: DS.attr('string'),
    created: DS.attr('isodate'),
    sex: DS.attr('string'),
    status: DS.attr(),
    sterilized: DS.attr('boolean'),
    picture: DS.attr('string'),
    observations: DS.attr('string'),
    admission: DS.attr('isodate'),

    recordDate: function () {
        return moment(this.get("created")).format('MMMM Do YYYY, h:mm:ss a');
    }.property("content.created"),
    
    isShelter: function () {
        return this.get('status') === 1;
    }.property('status'),

    isAdopted: function () {
        return this.get('status') === 2;
    }.property('status'),
    
    isFostering: function () {
        return this.get('status') === 3;
    }.property('status'),
    
    isMissing: function () {
        return this.get('status') === 4;
    }.property('status'),
    
    isDeceased: function () {
        return this.get('status') === 5;
    }.property('status'),
});
