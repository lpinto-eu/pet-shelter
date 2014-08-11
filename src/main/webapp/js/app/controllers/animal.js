App.AnimalsAnimalController = Ember.ObjectController.extend({
    isEditing: false,

    isMale: function () {
        return this.get('sex') === 'M';
    }.property('content.sex'),

    isFemale: function () {
        return this.get('sex') === 'F';
    }.property('content.sex'),

    description: function () {
        return this.get("content.name") + " | " + this.get("content.age");
    }.property("content.name", "content.age"),
    
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
                    });
            }
        }
    }
});
