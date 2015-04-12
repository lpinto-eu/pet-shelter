/*
 *
    @author Vítor Martins - varmartins@varmartins.com
*/

App.OrganizationsOrganizationController = Ember.ObjectController.extend({
    isEditing: false,

    actions: {
        toggleProp:  function() {
            this.toggleProperty("isEditing");
        },

        cancel: function() {
            if (this.get('content.isDirty') && confirm('Unsaved changes will be lost.')) {
                this.get('model').rollback();
                self.transitionToRoute('organzations');
            }
            this.toggleProperty("isEditing");
        },

        update: function() {
            if (this.get('content.isDirty')) {
                var self = this;
                this.get('model').save()
                        .then(function() {
                            self.toggleProperty("isEditing");
                            self.transitionToRoute('organizations');
                        }, function (reason) {
                            self.send('error', reason);
                        });
            } else {
                this.toggleProperty("isEditing");
                this.transitionToRoute('organizations');
            }
        },

        remove: function() {
            if (confirm('Delete: ' + this.get('name') + '?\nThis will also delete all the animals!')) {
                var organization = this.get('model');
                organization.deleteRecord();
                var self = this;
                organization.save()
                        .then(function() {
                            self.toggleProperty("isEditing");
                            self.transitionToRoute('organizations');
                        }, function (reason) {
                            self.send('error', reason);
                    });
            }
        }
    }
});



