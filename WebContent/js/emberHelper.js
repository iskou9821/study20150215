window.EmberHelper = {};

EmberHelper.loadItems = function(target) {
	$.each(target, function(i, val) {
		if (i.indexOf('init') == 0) val();
	});	
};

EmberHelper.initApp = function(templateNames, templatePath, initObjs, callback) {
	//テンプレートの読み込みを行うローカル関数
	var path = templatePath == null ? './template/' : templatePath + '/';
	var registerTemplate = function(tmplId) {
		return $.get(path + tmplId + '.hbs').success(function(data) {
			/*
			 * 非同期処理で読み込んだテンプレートファイルを
			 * EmberJS内で利用するデータとして、名前を付けて読み込む。
			 */
			Ember.TEMPLATES[tmplId] = Ember.Handlebars.compile(data);
		}).fail(function(req, sts, err) {
			alert("Can't read template:" + tmplId);
		});
	};
	
	//配列で受け取ったテンプレートを非同期処理で読み込む
	var promises = [];
	$.each(templateNames, function(i, val) {
		promises.push(registerTemplate(val));
	});
	
	//非同期処理の待ち合わせを行い、全ての処理が完了したらEmberJSの初期化を行う
	$.when.apply(null, promises).then(function() {
		//Ember.Application.create()は、テンプレートの読み込みが終わった後でなければならない。
		window.App = Ember.Application.create({});
		
		//ビューなどの初期化
		$.each(initObjs, function(i, val) {
			EmberHelper.loadItems(val);
		});
						
		//後続の初期化処理。引数でコールバック関数を受け取って実行。
		if (callback != null) callback(); 
	});
};