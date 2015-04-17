'use strict';

var del = require('del');
var gulp = require('gulp');
var gutil = require('./node_modules/gulp/node_modules/gulp-util');
var ejsJson = require('gulp-ejs-json');
var server = require('gulp-server-livereload');
var notify = require('gulp-notify');
var notifyChange = function (path) {
    gulp.src(path)
        .pipe(notify('"<%= file.relative %>" changed.'));
}

// clean:清除生成的文件
gulp.task('clean', del.bind(null, ['dist/*']));

// compile:编译
gulp.task('compile', ['clean'], function() {
    return gulp.src('models/books/CodingSpec.json')
        .pipe(ejsJson({ filename: 'views/book.ejs' }).on('error', gutil.log))
        .pipe(gulp.dest('dist'));
});

// debug:调试
gulp.task('debug', ['compile'], function() {
    // webserver:本机发布
    gulp.src('dist')
        .pipe(server({
            host:'localhost',
            port:9000,
            livereload: true,
            directoryListing: false,
            defaultFile: 'demo.html',
            open: true,
            https:false
        }));

    // watch:监视变化，重新编译
    gulp.watch('models/**/*.json', ['compile'])
        .on('change', function(event){
            notifyChange(event.path);
        });

    gulp.watch('views/**/*.ejs', ['compile'])
        .on('change', function(event){
            notifyChange(event.path);
        });
});


