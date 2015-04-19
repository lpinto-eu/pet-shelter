App.AnimalsAnimalController = Ember.Controller.extend({
    isEditing: false,

    primaryColorIsNull: function () {
        if (this.get('model').get('primaryColor') === undefined) {
            return true;
        }
    }.property('model.primaryColor'),

    secondaryColorIsNull: function () {
        if (this.get('model').get('secondaryColor') === undefined) {
            return true;
        }
    }.property('model.primaryColor'),

    actions: {
        toggleProp:  function() {
            this.toggleProperty("isEditing");
        },

        cancel: function() {
            if (this.get('content.isDirty') && confirm('Unsaved changes will be lost.')) {
                this.get('model').rollback();
            }
            this.toggleProperty("isEditing");
        },

        update: function() {
            if (this.get('content.isDirty')) {
                var self = this;
                this.get('model').save()
                        .then(function() {
                            self.transitionToRoute('animals');
                            self.toggleProperty("isEditing");
                        }, function (reason) {
                            self.send('error', reason);
                        });
            } else {
                this.transitionToRoute('animals');
                this.toggleProperty("isEditing");
            }
        },

        remove: function() {
            if (confirm('Delete: ' + this.get('name') + '?')) {
                var animal = this.get('model');
                animal.deleteRecord();
                var self = this;
                animal.save()
                        .then(function() {
                            self.toggleProperty("isEditing");
                            self.transitionToRoute('animals');
                        }, function (reason) {
                            self.send('error', reason);
                        });
            }
        }
    }
});
