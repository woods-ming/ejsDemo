'use strict';

var del = require('del');
var gulp = require('gulp');
var gutil = require('./node_modules/gulp/node_modules/gulp-util');
var ejsJson = require('gulp-ejs-json');
var notify = require('gulp-notify');
var server = require('gulp-server-livereload');

// clean:清除生成的文件
// gulp.task('clean', function () {
//     return gulp.src('dist/*', {read: false})
//         .pipe(clean());
//         // .pipe(notify('"<%= file.relative %>" cleaned.'));
// });
gulp.task('clean', del.bind(null, ['dist/*']));

// compile:编译
gulp.task('compile', ['clean'], function() {
	return gulp.src('models/demo*.json')
	    .pipe(ejsJson({ filename: 'views/demo.ejs' }).on('error', gutil.log))
	    .pipe(gulp.dest('dist'));
});

// webserver:本机发布
gulp.task('webserver', ['compile'], function() {
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
});

// watch:监视变化，重新编译
gulp.task('watch', ['webserver'], function() {
	gulp.watch('models/**/*.json', ['compile'])
		.on('change', function(event) {
		  console.log('File:"' + event.path + ' was ' + event.type + '", running tasks...');
		});

	gulp.watch('views/**/*.ejs', ['compile'])
		.on('change', function(event) {
		  console.log('File:"' + event.path + ' was ' + event.type + '", running tasks...');
		});
});

// debug:调试
gulp.task('debug', ['watch']);
