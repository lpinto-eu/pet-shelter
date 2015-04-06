App.AnimalsController = Ember.ArrayController.extend({

    sex: function () {
        var males = this.filter(function (animal) {
            return animal.get('sex') === "M";
        });

        var females = this.filter(function (animal) {
            return animal.get('sex') === "F";
        });

        if(males.length > 0 || females.length > 0) {
            return [
                {key: 'Male', value: males.length},
                {key: 'Female', value: females.length}
            ];
        } else {
            return null;
        }
    }.property('@each.sex'),

    age: function () {
        var flag = false;

        var data = [
            {key: '<1', value: 0},
            {key: '1-4', value: 0},
            {key: '5-9', value: 0},
            {key: '>10', value: 0}
        ];
        var x = this.reduce(function (buckets, animal) {
            var age = animal.get('age');
            if (age < 1) {
                buckets[0].value++;
                flag = true;
            } else if (age < 5) {
                buckets[1].value++;
                flag = true;
            } else if (age < 10) {
                buckets[2].value++;
                flag = true;
            } else if (age) {
                buckets[3].value++;
                flag = true;
            }
            return buckets;
        }, data);

        if(flag) {
            return x;
        } else {
            return null;
        }
    }.property('@each.age'),

    cTimeMonth: function () {
        var data = new Array();
        this.map(function (animal) {
            if(animal.get('created'))
                return animal.get('created').getMonth();
        }).reduce(function (data, month) {
            data[month] = data[month] ? data[month] + 1 : 1;
            return data;
        }, data);
        for (var i = 0; i < 12; ++i) {
            if (!data[i]) {
                data[i] = 0;
            }
        }
        return data;
    }.property('@each.created'),

    actions: {
        pushSort: function (attribute) {
            if (this.get("sortProperties.firstObject") === attribute) {
                this.toggleProperty("sortAscending");
            } else {
                this.set("sortProperties", [attribute]);
            }
        }
    }
});
