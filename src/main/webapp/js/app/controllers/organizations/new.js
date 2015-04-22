/*
 *
    @author Vítor Martins - varmartins@varmartins.com
*/

App.OrganizationsNewController = Ember.Controller.extend({
    actions: {
        save: function () {
            var self = this;
                this.get('model').save()
                        .then(function () {
                            self.transitionToRoute('organizations');
                        }, function (reason) {
                            self.send('error', reason);
                        });
        },
        cancel: function () {
            if (this.get('content.isDirty')) {
                if (this.get('content.isNew') && confirm('Unsaved changes will be lost.')) {
                    this.get('content').deleteRecord();
                    this.transitionToRoute('organizations');
                }
            }
        }
    }
});


