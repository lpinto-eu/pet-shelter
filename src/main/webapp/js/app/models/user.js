App.ApiKey = DS.Model.extend({
  accessToken: DS.attr('string'),
  user:        DS.belongsTo('user', {
    async: true
  })
});

App.User = DS.Model.extend({
  created:               DS.attr(),
  updated:               DS.attr(),
  email:                 DS.attr('string'),
  serialVersionUID:      DS.attr(),
  name:                  DS.attr('string'),
  password:              DS.attr('string'),
  apiKeys:               DS.hasMany('apiKey'),
  organizations:         DS.hasMany('organization', { async: true }),
  errors:                {}
});