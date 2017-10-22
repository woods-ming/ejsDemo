'use strict';

var glob = require('glob')
, wiredep = require('wiredep').stream
, browserSync = require('browser-sync')  
, reload = browserSync.reload
, routeTable = require('./app/routeTable.json')
, moment = require('moment')
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
        compileHtml(route.modelGlob, route.view, 'app/html');
    }
});

// debug:测试环境发布
gulp.task('debug', ['compile'], function() {
    // webserver:临时服务器
    browserSync({
        notify: true,
        port: 9000,
        logFileChanges: true,
        server: {
            baseDir: ['.tmp', 'app'],
            index: "/html/index.html",
            routes: {
                '/bower_components': 'bower_components'
            }
        }
    });

    // watch:监视变化
    gulp.watch([
        'app/html/**/*.html',
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
        var dest = path.resolve('app/html', path.relative('app/models', path.dirname(event.path)));
        compileHtml(model, view, dest);
    });
});

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
        require('autoprefixer')({browsers: ['last 1 version']})
        ]))
    .pipe($.sourcemaps.write())
    .pipe(gulp.dest('.tmp/styles'))
    .pipe(reload({stream: true}));
});

// html:处理html及依赖的js、css文件，并拷贝到生成目录
gulp.task('html', ['compile', 'jshint', 'styles'], function () {
    return gulp.src('app/**/*.html')
    .pipe($.useref({searchPath: ['.tmp', 'app', 'bower_components']}))
    .pipe($.if('*.js', $.uglify()))
    .pipe($.if('*.css', $.csso()))
    .pipe($.replaceTask({
        usePrefix:false,
        patterns: [
        {
            match: /href=\".*bootstrap.css\"/,
            replacement: 'href="//apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.min.css"'
        },
        {
            match: /href=\".*prettify.min.css\"/,
            replacement: 'href="//apps.bdimg.com/libs/prettify/r298/prettify.min.css"'
        },
        {
            match: /src=\".*jquery.js\"/,
            replacement: 'src="//apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"'
        },
        {
            match: /src=\".*bootstrap.js\"/,
            replacement: 'src="//apps.bdimg.com/libs/bootstrap/3.3.4/js/bootstrap.min.js"'
        },
        {
            match: /src=\".*prettify.min.js\"/,
            replacement: 'src="//apps.bdimg.com/libs/prettify/r298/prettify.min.js"'
        },
        {
            match: /src=\".*jquery.lazyload.js\"/,
            replacement: 'src="//apps.bdimg.com/libs/jquery-lazyload/1.9.5/jquery.lazyload.min.js"'
        }
        ]
    })) // 百度cdn
    .pipe($.if('*.html', $.minifyHtml({conditionals: true})))
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

// extras:复制其它资源
gulp.task('extras', function () {
    gulp.src('app/codeSnippets/**/*')
    .pipe(gulp.dest('dist/codeSnippets'));

    return gulp.src('app/favicon.ico')
    .pipe(gulp.dest('dist/'));

});

// sitemap:生成站点地图，并拷贝到生成目录
gulp.task('sitemap', function () {
    return gulp.src('app/html/**/*.html')
    .pipe($.sitemap({
        siteUrl: 'http://woods240.cn/html/'
    }))
    .pipe(gulp.dest('dist'));

});

// clean:清除生成的文件
gulp.task('clean', require('del').bind(null, ['.tmp', 'dist']));

// build:生成
gulp.task('build', ['html', 'images', 'extras', 'sitemap'], function () {
    // 复制网站首页
    return gulp.src('dist/html/index.html')
    .pipe($.rename("index.html"))
    .pipe(gulp.dest('dist/'));
});

// zip:压缩打包
gulp.task('zip', function(){
    return gulp.src('dist/**/*')
    .pipe($.size({title: 'build', gzip: true}))
    .pipe($.zip('ejsDemo_' + moment().format('YYYYMMDD_Ahhmmss') + '.zip'))
    .pipe(gulp.dest('../Publish'));
});

// rebuild:重新生成
gulp.task('rebuild', function () {
    return $.runSequence('clean', 'build', 'zip');
});

// default:生产环境发布
gulp.task('default', ['rebuild'], function () {
    // webserver:临时服务器
    return browserSync({
        port: 9001,
        server: {
            baseDir: ['dist'],
            index: "/index.html"
        }
    });
});


