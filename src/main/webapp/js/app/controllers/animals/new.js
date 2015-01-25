App.AnimalsNewController = Ember.ObjectController.extend({
    actions: {
        save: function () {
            if (this.get("model").get("picture") && this.get("model").get("picture").length > 65535) {
                alert("Image is to big! max 50kb.");
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
