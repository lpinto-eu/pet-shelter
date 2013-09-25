App.Router.map(function () {
    this.resource('animals', function () {
        this.route('new');
    });
    this.route("animal", { path: "/animals/:animal_id"});
});
