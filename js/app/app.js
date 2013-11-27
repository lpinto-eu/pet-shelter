window.App = Ember.Application.create();

/*
 * Helpers for handlebars
 */
Ember.Handlebars.registerBoundHelper('date', function (date) {
    return moment(date).fromNow();
});

/*
 * Foto upload and Preview
 */
App.FotoPreview = Ember.View.extend({
    attributeBindings: ['src'],
    tagName: 'img'
});

App.FotoUp = Ember.TextField.extend({
    type: 'file',

    change: function (evt) {
        var input = evt.target;
        if (input.files && input.files[0]) {
            var that = this;

            var reader = new FileReader();
            reader.onload = function (e) {
                var data = e.target.result;
                that.set('parentView.content', data);
            };
            reader.readAsDataURL(input.files[0]);
        }
    }
});

App.Sex = [
    {
        id : "",
        name: "Select one..."
    },
    {
        id : "M",
        name: "Male"
    },
    {
        id: "F",
        name: "Female"
    }
];
