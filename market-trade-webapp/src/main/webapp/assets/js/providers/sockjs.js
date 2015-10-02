/*global angular window SockJS, Stomp*/

(function withAngular(angular, window, SockJS, Stomp) {
  'use strict';

  angular.module('marketTrade.providers.sockJS', [])

  .provider('Sockjs', [function providerFunction() {

    var client
      , setSocketUrl = function setSocketUrl(url) {

          if (url) {

            var socket = new SockJS(url);
            client = Stomp.over(socket);
          } else {

            window.console.error('Please provide a valid URL.');
          }
        };

    return {
      'setSocketUrl': setSocketUrl,
      '$get': [
      function instantiateProvider() {

        return client;
      }]
    };
  }]);
}(angular, window, SockJS, Stomp));
