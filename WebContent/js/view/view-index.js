JavaStudy.initIndexView = function() {
  
  App.IndexView = Ember.View.extend({
    layoutName : 'view-index'
  });
  
  App.IndexRoute = Ember.Route.extend({
    model : function() {
      return JavaStudy.HogeService.findAll();
    }
  })
  
  App.IndexController = Ember.ArrayController.extend({
	  
  });
  
};