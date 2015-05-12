App.OrganizationsOrganizationAnimalsAnimalController = Ember.Controller.extend({
    isEditing: false,

    primaryColorIsNull: function () {
        if (this.get('model.primaryColor') === undefined) {
            return true;
        }
    }.property('model.primaryColor'),

    secondaryColorIsNull: function () {
        if (this.get('model.secondaryColor') === undefined) {
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

        update: function () {
            if (this.get('content.isDirty')) {
                var self = this;
                Ember.$.ajax({
                    url: "api/organizations/" + this.get("model.organization.id") + "/animals/" + this.get("model.id"),
                    type: "PUT",
                    data: JSON.stringify(this.get('model')),
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    success: function () {
                        self.toggleProperty("isEditing");
                        self.transitionToRoute('organizations.organization.animals');
                    }
                });
            } else {
                this.toggleProperty("isEditing");
                this.transitionToRoute('organizations.organization.animals');
            }
        },

        remove: function() {
            if (confirm('Delete: ' + this.get('model.name') + '?')) {
                var animal = this.get('model');
                animal.deleteRecord();
                var self = this;
                animal.save()
                        .then(function() {
                            self.toggleProperty("isEditing");
                            self.transitionToRoute('organizations.organization.animals');
                        }, function (reason) {
                            self.send('error', reason);
                        });
            }
        }
    }
});
