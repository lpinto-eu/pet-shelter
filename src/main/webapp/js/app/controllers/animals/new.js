App.AnimalsNewController = Ember.ObjectController.extend({

    actions: {
        save: function () {
            if (this.get("model")
                    && this.get("model").get("picture")
                    && this.get("model").get("picture").length > 65535) {
                alert("Image is too large! max 50kb.");
            } else {
                var self = this;
                this.get('model').save()
                        .then(function () {
                            self.transitionToRoute('animals.animal', self.get("id"));
                        });
            }
        },
        cancel: function () {
            if (this.get('content.isDirty')) {
                if (this.get('content.isNew') && confirm('Unsaved changes will be lost.')) {
                    this.get('content').deleteRecord();
                    this.transitionToRoute('application');
                }
            }
        }
    }
});
//
//                // Create the new Todo model
//                var animal = this.store.createRecord('animal', {
//                    name: this.get('name'),
//                    age: this.get('age'),
//                    species: this.get('species'),
//                    breed: this.get('breed'),
//                    drugs: this.get('drugs'),
//                    created: this.get('created'),
//                    sex: this.get('sex'),
//                    status: this.get('status'),
//                    sterilized: this.get('sterilized'),
//                    picture: this.get('picture'),
//                    observations: this.get('observations'),
//                    admission: this.get('admission')
//                });
//
//                // Save the new model
//                animal.save()