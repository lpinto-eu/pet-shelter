App.ApiKey = DS.Model.extend({
  accessToken: DS.attr('string'),
  user:        DS.belongsTo('user', {
    async: true
  })
});

App.User = DS.Model.extend({
  name:                  DS.attr('string'),
  email:                 DS.attr('string'),
  password:              DS.attr('string'),
  apiKeys:               DS.hasMany('apiKey'),
  organization:          DS.hasMany('organization', { async: true }),
  errors:                {}
});