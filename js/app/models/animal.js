App.Animal = DS.Model.extend({
    name: DS.attr('string'),
    age: DS.attr('string')
});

App.Animal.FIXTURES = [
    {
        id: 1,
        name: 'Trun Trun',
        age: '9'
    },
    {
        id: 2,
        name: 'Na Nac',
        age: '2'
    },
    {
        id: 3,
        name: 'Bobi',
        age: '10'
    },
    {
        id: 4,
        name: 'Cão com 4 nomes',
        age: '20'
    },
    {
        id: 5,
        name: 'Cão 1',
        age: '2'
    },
    {
        id: 6,
        name: 'Na Nac',
        age: '2'
    },
    {
        id: 7,
        name: 'Na Nac',
        age: '2'
    },
    {
        id: 8,
        name: 'Na Nac',
        age: '2'
    }
];
