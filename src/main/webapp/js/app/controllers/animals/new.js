App.OrganizationsOrganizationAnimalNewController = Ember.Controller.extend({
    needs: ['OrganizationsOrganization'],
    actions: {
        save: function (organizationID) {
            var self = this;
            Ember.$.ajax({
                url: "api/organizations/" + this.get("model.organization") + "/animals",
                type: "POST",
                data: JSON.stringify(this.get('model')),
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                success: function (result) {
                    self.transitionToRoute('organizations.organization.animals');
                }});
        },
        cancel: function () {
            if (this.get('content.isDirty')) {
                if (this.get('content.isNew') && confirm('Unsaved changes will be lost.')) {
                    this.get('content').deleteRecord();
                    this.transitionToRoute('organizations.organization.animals');
                }
            }
        }
    }
});