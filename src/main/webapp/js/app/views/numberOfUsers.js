App.NumberOfUsersView = Ember.View.extend({
    
    numberOfUsers: function () {
        for each (user in organization) {
            var count = count + 1;       
        }
        return count;
    }
});