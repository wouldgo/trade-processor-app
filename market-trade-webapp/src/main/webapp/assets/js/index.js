/*global angular*/

(function withAngular(angular) {
  'use strict';

  angular.module('market-trade', [
    'ngRoute',
    'angles',
    'marketTrade.providers',
    'marketTrade.factories',
    'marketTrade.controllers'
    ])

  .config(['$routeProvider', '$httpProvider', 'SockjsProvider',
    function configuration($routeProvider, $httpProvider, SockjsProvider) {

    $routeProvider
      .when('/home', {
        'templateUrl': 'assets/views/home.html',
        'controller': 'Home'
      })
      .otherwise({
        'redirectTo': '/home'
      });

    $httpProvider.defaults.withCredentials = true;
    SockjsProvider.setSocketUrl('market-trade');
  }])

  .run(['$rootScope', 'Sockjs',
    function run($rootScope, Sockjs) {

    $rootScope.curDate = new Date();

    Sockjs.connect('market-trader', 'market-trader-pwd', function onConnection() {

      Sockjs.subscribe('/new-message', function onNewTradeMessage(message) {

        if (message &&
          message.body) {

          var parsedMsg = JSON.parse(message.body);
          $rootScope.$apply(function applyFunction() {

            $rootScope.$emit('trade-message:new', parsedMsg);
          });
        }
      });
    });

    $rootScope.graphOptions = {
      'animation': false,
      'segmentShowStroke': false/*,
      'responsive': true*/
    };
  }]);
}(angular));
