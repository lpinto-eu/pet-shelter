App.OrganizationsOrganizationAnimalsView = Ember.View.extend({

  dynheight: function() {
      var header = 81;
      var orgHeader = 75;
      var animalHeader = 61;
      var h = window.innerHeight - header - orgHeader - animalHeader - 11 - 20;
      return "height: " + h + "px;";
  }.property("window.innerHeight")
});