App.Animal = DS.Model.extend({
    name: DS.attr('string'),
    age: DS.attr('string')
});

App.Animal.FIXTURES = [
 {
    id: 1,
    name: 'Gato',
    age: '5'
 },
 {
    id: 2,
    name: 'cão',
    age: '3'
 },
 {
    id: 3,
    name: 'Peixe',
    age: '10'
 }
];
