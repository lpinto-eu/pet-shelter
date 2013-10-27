App.Animal = DS.Model.extend({
    name: DS.attr('string'),
    age: DS.attr('integer'),
    species: DS.attr('string'),
    breed: DS.attr('string'),
    drugs: DS.attr('string'),
    cTime: DS.attr('date'),
    sex: DS.attr('string'),

    recordDate: function () {
        return moment(this.get("cTime")).format('MMMM Do YYYY, h:mm:ss a');
    }.property("content.cTime"),

    isMale: function () {
        return this.get('sex') === 'M';
    }.property('content.sex'),

    isFemale: function () {
        return this.get('sex') === 'F';
    }.property('content.sex')

});

App.Animal.FIXTURES = [
    {
        id: 1,
        name: 'Trun Trun',
        age: 9,
        species: "Cat",
        breed: "N.A.",
        drugs: "Medicamento 1 x2/dia",
        cTime: new Date('12-24-2012'),
        sex: 'M'
    },
    {
        id: 2,
        name: 'Na Nac',
        age: 2,
        species: "Cat",
        breed: "N.A.",
        cTime: new Date('12-24-2012'),
        sex: 'M'
    },
    {
        id: 3,
        name: 'Bobi',
        age: 10,
        species: "Dog",
        breed: "N.A.",
        cTime: new Date('09-20-2013'),
        sex: 'F'
    },
    {
        id: 4,
        name: 'Cão com 4 nomes',
        age: 20,
        species: "Dog",
        breed: "N.A.",
        cTime: new Date('09-20-2013'),
        sex: 'M'
    },
    {
        id: 5,
        name: 'Cão 1',
        age: 2,
        species: "Dog",
        breed: "N.A.",
        cTime: new Date('09-20-2013'),
        sex: 'F'
    },
    {
        id: 6,
        name: 'Na Nac',
        age: 6,
        species: "Dog",
        breed: "N.A.",
        drugs: "Medicamento 1 x2/dia",
        cTime: new Date('09-20-2013'),
        sex: 'M'
    },
    {
        id: 7,
        name: 'Na Nac',
        age: 11,
        species: "Dog",
        breed: "N.A.",
        drugs: "Medicamento 1 x2/dia",
        cTime: new Date('09-20-2013'),
        sex: 'F'
    },
    {
        id: 8,
        name: 'Na Nac',
        age: 15,
        species: "Dog",
        breed: "N.A.",
        drugs: "Medicamento 1 x2/dia",
        cTime: new Date('09-20-2013'),
        sex: 'F'
    }
];
