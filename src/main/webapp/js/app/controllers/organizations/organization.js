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
                this.get('model').save().then(function() {
                    self.toggleProperty("isEditing");
                    self.transitionToRoute('organizations');
                    });
            } else {
                this.transitionToRoute('organizations.table');
                this.toggleProperty("isEditing");
            }
        },

        remove: function() {
            if (confirm('Delete: ' + this.get('name') + '?')) {
                var organization = this.get('model');
                organization.deleteRecord();
                var self = this;
                organization.save().then(function() {
                        self.toggleProperty("isEditing");
                        self.transitionToRoute('organizations');
                    });
            }
        }
    }
});



