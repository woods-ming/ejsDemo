'use strict';

var del = require('del')
  , glob = require('glob')
  , path = require('path')
  , wiredep = require('wiredep').stream
  , gulp = require('gulp')
  , gutil = require('./node_modules/gulp/node_modules/gulp-util')
  , notify = require('gulp-notify')
  , ejsJson = require('gulp-ejs-json')
  , minifyHTML = require('gulp-minify-html')
  , server = require('gulp-server-livereload')
  , routeTable = require('./routeTable.json');

function createHtml(model, view) {
    gulp.src(model)
        .pipe(ejsJson({ filename: view }).on('error', gutil.log))
        .pipe(wiredep())
        .pipe(minifyHTML(
    		{
    			cdata:true,
    		    conditionals: true,
    		    spare:true,
    			quotes:true
    		}))
        .pipe(gulp.dest('dist'));
}

function notifyChange (path) {
  	gulp.src(path)
      	.pipe(notify('"<%= file.relative %>" changed.'));
}

function findRouteView(model) {
    for(var i = 0; i < routeTable.length; i++) {
    	var route = routeTable[i];
    	var models = glob.sync(route.modelGlob, { realpath: true });
    	for(var j = 0; j < models.length; j++) {
            if(model === models[j]) {
                return route.view;
            }
        }
    }

    return null;
}

// wiredep:（在模板中）依赖注入
gulp.task('wiredep', function () {
   gulp.src('dist/**/*.html')
    .pipe(wiredep({
      ignorePath: /^(\.\.\/)*\.\./
    }))
    .pipe(gulp.dest('dist'));
});

// clean:清除生成的文件
gulp.task('clean', del.bind(null, ['dist/*']));

// copy-assets:复制资源文件
gulp.task('copy-assets', function() {
	gulp.src('assets/*')
		.pipe(gulp.dest('dist'));
});

// html:根据路由表找到model对应的view，编译成html
gulp.task('html', function() {
    for(var i = 0; i < routeTable.length; i++) {
        var route = routeTable[i];
        createHtml(route.modelGlob, route.view);
    }
});

// styles:处理css文件
gulp.task('styles',function(){

});

// scripts:处理js文件
gulp.task('scripts',function(){

});

// debug:调试
gulp.task('debug', ['html', 'copy-assets'], function() {
    // webserver:临时服务器
    gulp.src('.')
        .pipe(server({
            host:'localhost',
            port:9000,
            livereload: true,
            directoryListing: true,
            // defaultFile: 'CodingSpec.html',
            open: true,
            https:false
        }));
    
    // watch:监视变化，重新编译
    gulp.watch('models/**/*.json')
        .on('change', function(event) {
            notifyChange(event.path);
            var model = path.resolve('dist',path.relative('models',event.path));
            var view = findRouteView(model);
            createHtml(model, view);
        });

    gulp.watch('views/**/*.ejs', ['html'])
        .on('change', function(event){
            notifyChange(event.path);
        });

    gulp.watch('bower.json', ['wiredep']);

});

// build:生成
gulp.task('build', ['html', 'scripts', 'styles'], function(){

});

// deploy:发布
gulp.task('deploy', ['clean'], function() {
    gulp.start('build');
});


