App.Router.map(function () {
    this.resource('animals', function () {
        this.route('new')
    }),
            
    this.resource("animal", { path: ":animal_id"})
});
