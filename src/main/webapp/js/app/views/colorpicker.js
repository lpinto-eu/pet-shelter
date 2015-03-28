/*
 * @author Vítor Martins - varmartins@varmartins.com
 */

App.ColorPicker = Ember.TextField.extend({
    type: 'color',
    didInsertElement: function () {
        $("#color").click(function () {
            $("#secondaryColor").css("display", "block");
        });
    }
});