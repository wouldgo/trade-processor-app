/*global module*/

(function setUp(module) {
  'use strict';

  module.exports = function doGrunt(grunt) {

    grunt.initConfig({
      'csslint': {
        'options': {
          'csslintrc': 'config/csslintrc.json'
        },
        'strict': {
          'src': [
            'src/main/webapp/assets/css/**/*.css'
          ]
        }
      },
      'eslint': {
        'options': {
          'config': 'config/eslintrc.json'
        },
        'target': [
          'Gruntfile.js',
          'src/main/webapp/assets/js/**/*.js'
        ]
      }});

    grunt.loadNpmTasks('grunt-contrib-csslint');
    grunt.loadNpmTasks('grunt-eslint');

    grunt.registerTask('cli', [
      'csslint',
      'eslint'
    ]);
  };
}(module));
