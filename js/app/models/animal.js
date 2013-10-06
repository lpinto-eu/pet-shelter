App.Animal = DS.Model.extend({
    name: DS.attr('string'),
    age: DS.attr('integer'),
    species: DS.attr('string'),
    breed: DS.attr('string'),
    drugs: DS.attr('string'),
    cTime: DS.attr('date'),
});

App.Animal.FIXTURES = [
    {
        id: 1,
        name: 'Trun Trun',
        age: 9,
        species: "Cat",
        breed: "N.A.",
        drugs: "Medicamento 1 x2/dia",
        cTime: new Date('12-24-2012')
    },
    {
        id: 2,
        name: 'Na Nac',
        age: 2,
        species: "Cat",
        breed: "N.A.",
        drugs: "Medicamento 1 x2/dia",
        cTime: new Date('12-24-2012')
    },
    {
        id: 3,
        name: 'Bobi',
        age: 10,
        species: "Dog",
        breed: "N.A.",
        drugs: "Medicamento 1 x2/dia",
        cTime: new Date('09-20-2013')
    },
    {
        id: 4,
        name: 'Cão com 4 nomes',
        age: 20,
        species: "Dog",
        breed: "N.A.",
        drugs: "Medicamento 1 x2/dia",
        cTime: new Date('09-20-2013')
    },
    {
        id: 5,
        name: 'Cão 1',
        age: 2,
        species: "Dog",
        breed: "N.A.",
        drugs: "Medicamento 1 x2/dia",
        cTime: new Date('09-20-2013')
    },
    {
        id: 6,
        name: 'Na Nac',
        age: 6,
        species: "Dog",
        breed: "N.A.",
        drugs: "Medicamento 1 x2/dia",
        cTime: new Date('09-20-2013')
    },
    {
        id: 7,
        name: 'Na Nac',
        age: 11,
        species: "Dog",
        breed: "N.A.",
        drugs: "Medicamento 1 x2/dia",
        cTime: new Date('09-20-2013')
    },
    {
        id: 8,
        name: 'Na Nac',
        age: 15,
        species: "Dog",
        breed: "N.A.",
        drugs: "Medicamento 1 x2/dia",
        cTime: new Date('09-20-2013')
    }
];
