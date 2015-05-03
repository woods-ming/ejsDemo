'use strict';

var glob = require('glob')
  , wiredep = require('wiredep').stream
  , browserSync = require('browser-sync')  
  , reload = browserSync.reload
  , routeTable = require('./app/routeTable.json')

  , gulp = require('gulp')
  , $ = require('gulp-load-plugins')();


function compileHtml(model, view, dest) {
    gulp.src(model)
        .pipe($.ejsJson({ filename: view }).on('error', $.util.log))
        .pipe(wiredep())
        .pipe(gulp.dest(dest));
}

function notifyChange (path) {
  	gulp.src(path)
      	.pipe($.notify('"<%= file.relative %>" changed.'));
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

// compile:根据路由表找到model对应的view，编译成html
gulp.task('compile', function() {
    for(var i = 0; i < routeTable.length; i++) {
        var route = routeTable[i];
        compileHtml(route.modelGlob, route.view, 'app');
    }
});

// debug:调试
gulp.task('debug', ['compile'], function() {
    // webserver:临时服务器
    browserSync({
        notify: true,
        port: 9000,
        logFileChanges: true,
        server: {
            baseDir: ['.tmp', 'app'],
            index: "basic/CodingSpec.html",
            routes: {
                '/bower_components': 'bower_components'
            }
        }
    });

    // watch:监视变化
    gulp.watch([
        'app/**/*.html',
        'app/scripts/**/*.js',
        'app/styles/**/*.css',
        'app/images/**/*.*'
    ]).on('change', reload);

    gulp.watch('app/styles/**/*.css', ['styles']);
    gulp.watch('app/views/**/*.ejs', ['compile']);

    var path = require('path');
    gulp.watch('app/models/**/*.json')
        .on('change', function(event) {
            notifyChange(event.path);
            var model = event.path; 
            var view = findRouteView(model);
            var dest = path.resolve('app', path.relative('app/models', path.dirname(event.path)));
            compileHtml(model, view, dest);
        });
});


// clean:清除生成的文件
gulp.task('clean', require('del').bind(null, ['.tmp', 'dist']));

// jshint:检查js代码中的错误
gulp.task('jshint', function () {
  return gulp.src('app/scripts/**/*.js')
    .pipe(reload({stream: true, once: true}))
    .pipe($.jshint())
    .pipe($.jshint.reporter('jshint-stylish'))
    .pipe($.if(!browserSync.active, $.jshint.reporter('fail')));
});

// styles:处理css文件
gulp.task('styles', function () {
  return gulp.src('app/styles/*.css')
    .pipe($.sourcemaps.init())
    .pipe($.postcss([
      require('autoprefixer-core')({browsers: ['last 1 version']})
    ]))
    .pipe($.sourcemaps.write())
    .pipe(gulp.dest('.tmp/styles'))
    .pipe(reload({stream: true}));
});

// html:处理html及依赖的js、css文件，并拷贝到生成目录
gulp.task('html', ['compile', 'styles'], function () {
  var assets = $.useref.assets({searchPath: ['.tmp', 'app', 'bower_components']});

  return gulp.src('app/**/*.html')
    .pipe(assets)
    .pipe($.if('*.js', $.uglify()))
    .pipe($.if('*.css', $.csso()))
    .pipe(assets.restore())
    .pipe($.useref())
    .pipe($.if('*.html', $.minifyHtml({conditionals: true, loose: true})))
    .pipe(gulp.dest('dist'));
});

// images:优化图片，并拷贝到生成目录
gulp.task('images', function () {
  return gulp.src('app/images/**/*')
    .pipe($.cache($.imagemin({
      progressive: true,
      interlaced: true,
      // don't remove IDs from SVGs, they are often used
      // as hooks for embedding and styling
      svgoPlugins: [{cleanupIDs: false}]
    })))
    .pipe(gulp.dest('dist/images'));
});

// sitemap:生成站点地图，并拷贝到生成目录
gulp.task('sitemap', function () {
    gulp.src('app/**/*.html')
        .pipe($.sitemap({
            siteUrl: 'http://codeExperience.sinaapp.com'
        }))
        .pipe(gulp.dest('dist'));
});

// build:生成
gulp.task('build', ['jshint', 'html', 'images', 'sitemap'], function () {
    return gulp.src('dist/**/*').pipe($.size({title: 'build', gzip: true}));
});

gulp.task('default', ['clean'], function () {
    gulp.start('build');

    // webserver:临时服务器
    browserSync({
        port: 9001,
        server: {
            baseDir: ['dist'],
            index: "basic/CodingSpec.html"
        }
    });
});


