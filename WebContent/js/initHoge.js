$(function() {
  var templateNames = ['view-index'];
  EmberHelper.initApp(templateNames, './template/hoge', [JavaStudyModels, JavaStudy], function() {
    App.Router.map(function() {
      //this.route("edit-hoge", { path: "/edithoge" });
    });
  });
});
