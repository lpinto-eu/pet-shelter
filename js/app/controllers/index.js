App.IndexController = Ember.ArrayController.extend({

    sex: function() {
        var males = this.filter(function(animal) {
            return animal.get('sex') == "M";
        });

        var females = this.filter(function(animal) {
            return animal.get('sex') == "F";
        });

        return [
            {key: 'Male', value: males.length},
            {key: 'Female', value: females.length}
            ];
    }.property('@each.sex'),

    age: function() {
        var under1 = this.filter(function(animal) {
            return animal.get('age') < 1;
        });

        var under5 = this.filter(function(animal) {
            return animal.get('age') < 5;
        });

        var under10 = this.filter(function(animal) {
            return animal.get('age') < 10;
        });

        var over10 = this.filter(function(animal) {
            return animal.get('age') >= 10;
        });

        return [
                {key: '<1', value: under1.length},
                {key: '1-5', value: under5.length},
                {key: '5-10', value: under10.length},
                {key: '>10', value: over10.length}
                ];
    }.property('@each.age'),
});
