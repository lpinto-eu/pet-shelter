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
            }
            this.toggleProperty("isEditing");
        },
        
        update: function() {
            if (this.get('content.isDirty')) {
                var self = this;
                this.get('model').save()
                    .then(function() {
                        self.transitionToRoute('organzations');
                        self.toggleProperty("isEditing");
                    });
            } else {
                this.transitionToRoute('organizations');
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
                        self.transitionToRoute('organizations.table');
                    });
            }
        }
    }
});



