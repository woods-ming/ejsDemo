var gulp = require('gulp');
var gutil = require('./node_modules/gulp/node_modules/gulp-util');
var clean = require('gulp-clean');
var notify = require('gulp-notify');
var connect = require('gulp-connect');
var ejsJson = require('gulp-ejs-json');

// clean:清除生成的文件
gulp.task('clean', function () {
    return gulp.src('dist/*', {read: false})
        .pipe(clean());
});

// compile:编译
gulp.task('compile', ['clean'], function() {
	return gulp.src('models/demo*.json')
	    .pipe(ejsJson({ filename: 'views/demo.ejs' }).on('error', gutil.log))
	    .pipe(gulp.dest('dist'));
});

// connect:搭建服务器
gulp.task('connect', ['compile'], function() {
  connect.server({
    root: 'dist',
    livereload: true
  });
});

// reload:重新加载
gulp.task('reload', function () {
  gulp.src('dist/**/*.html')
    .pipe(connect.reload())
    .pipe(notify("reload File: <%= file.relative %>"));
});

// watch:监视变化，重新编译
gulp.task('watch', function() {
	gulp.watch('models/**/*.json', ['compile']);
	gulp.watch('views/**/*.ejs', ['compile']);
	gulp.watch('dist/**/*.html', ['reload'])
		.on('change', function(event) {
		  console.log('File:"' + event.path + ' was ' + event.type + '", running tasks...');
		});
});

// debug:调试
gulp.task('debug', ['connect', 'watch']);
