JavaStudy.HogeService = {};

JavaStudy.HogeService.findAll = function() {
  var def = $.Deferred();
  $.ajax('./rest/hoge', 'get')
    .success(function(data) {
      var items = Ember.ArrayProxy.create({content:[]});
      var setItem = function(data) {
        var item = App.HogeModel.create();
        item.setProperties(data);
        items.pushObject(item);
      }
      if (data.hogeModel instanceof Array) {
        $.each(data.hogeModel, function(i, val) {
          setItem(val);
        });
      } else {
        setItem(data.hogeModel);
      }
      def.resolve(items);
    })
    .fail(function(req, sts, err) {
      def.reject(req, sts, err);
    });
  return def;
};

JavaStudy.HogeService.findOne = function() {

};

JavaStudy.HogeService.save = function(formSelector, isUpdate) {
  var sendType = isUpdate == true ? 'put' : 'post';
  var dt = new FormData($(formSelector)[0]);
  return $.ajax({
    type : sendType,
    url : './rest/hoge',
    processData : false,
    contentType : false,
    data : dt,
    dataType : 'json',
  });
};