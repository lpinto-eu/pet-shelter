App.AnimalsController = Ember.ArrayController.extend({
    sex: function() {
        var males = this.filter(function(animal) {
            return animal.get('sex') === "M";
        });

        var females = this.filter(function(animal) {
            return animal.get('sex') === "F";
        });

        return [
            {key: 'Male', value: males.length},
            {key: 'Female', value: females.length}
        ];
    }.property('@each.sex'),
    
    age: function() {
        var data = [
            {key: '<1', value: 0},
            {key: '1-4', value: 0},
            {key: '5-9', value: 0},
            {key: '>10', value: 0}
        ];
        return this.reduce(function(buckets, animal) {
            var a = animal.get('age');
            if (a < 1) {
                buckets[0].value++;
            } else if (a < 5) {
                buckets[1].value++;
            } else if (a < 10) {
                buckets[2].value++;
            } else {
                buckets[3].value++;
            }
            return buckets;
        }, data);
    }.property('@each.age'),
    
    cTimeMonth: function() {
        var data = new Array();
        this.map(function(animal) {
            return animal.get('created').getMonth();
        }).reduce(function(data, month) {
            data[month] = data[month] ? data[month] + 1 : 1;
            return data;
        }, data);
        for (var i = 0; i < 12; ++i) {
            if (!data[i]) {
                data[i] = 0;
            }
        }
        return data;
    }.property('@each.cTimeMonth'),

    actions: {
        pushSort: function(attribute) {
            if (this.get("sortProperties.firstObject") === attribute) {
                this.toggleProperty("sortAscending");
            } else {
                this.set("sortProperties", [attribute]);
            }
        }
    }
});
