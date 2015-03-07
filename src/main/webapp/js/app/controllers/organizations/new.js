/*
 * 
    @author Vítor Martins - varmartins@varmartins.com
*/

App.OrganizationsNewController = Ember.ObjectController.extend({
    actions: {
        save: function () {
            if (this.get("model")
                    && this.get("model").get("logo")
                    && this.get("model").get("logo").length > 65535) {
                alert("Logo is too large! max 50kb.");
            } else {
                var self = this;
                this.get('model').save().then(function () {
                            self.transitionToRoute('organizations.table');
                        });
            }
        },
        cancel: function () {
            if (this.get('content.isDirty')) {
                if (this.get('content.isNew') && confirm('Unsaved changes will be lost.')) {
                    this.get('content').deleteRecord();
                    this.transitionToRoute('organizations.table');
                }
            }
        }
    }
});


