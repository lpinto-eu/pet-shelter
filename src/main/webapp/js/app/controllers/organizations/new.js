/*
 * 
    @author Vítor Martins - varmartins@varmartins.com
*/

App.OrganizationsNewController = Ember.ObjectController.extend({
    actions: {
        save: function () {
                var self = this;
                this.get('model').save()
                        .then(function () {
                            self.transitionToRoute('organizations.organization', self.get("id"));
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
});


