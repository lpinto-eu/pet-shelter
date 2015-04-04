App.AnimalsNewController = Ember.ObjectController.extend({
    actions: {
        save: function () {
            var self = this;
            this.get('model').save()
                    .then(function () {
                        self.transitionToRoute('animals.animal', self.get("id"));
                    }, function (reason) {
                        self.send('error', reason);
                    });
        },
        cancel: function () {
            if (this.get('content.isDirty')) {
                if (this.get('content.isNew') && confirm('Unsaved changes will be lost.')) {
                    this.get('content').deleteRecord();
                    this.transitionToRoute('animals');
                }
            }
        }
    }
});