/*global angular*/

(function withAngular(angular) {
  'use strict';

  angular.module('marketTrade.factories.tradeMessages', [])

  .factory('TradeMessages', ['$q', '$http', '$log',
    function tradeMessagesFactory($q, $http, $log) {

      var getTheLatestTradeMessages = function getTheLatestTradeMessages() {

          return $q(function deferred(resolve, reject) {

            $http({
              'method': 'GET',
              'url': 'api/trade-messages'
            }).then(function onSuccess(response) {

              if (response &&
                response.data) {

                var datas = response.data
                  , datasLength = datas.length
                  , datasIndex = 0
                  , aData
                  , toReturn = [];
                for (; datasIndex < datasLength; datasIndex += 1) {

                  aData = datas[datasIndex];
                  if (aData) {

                    toReturn.push(aData);
                  }
                }

                resolve(toReturn);
              }
            }, function onFailure(failure) {

              $log.error('Error during the /trade-messages call');
              reject(failure);
            });
          });
        }
        , getNations = function getNations() {

          return $q(function deferred(resolve, reject) {

            $http({
              'method': 'GET',
              'url': 'api/nations'
            }).then(function onSuccess(response) {

              if (response &&
                response.data) {

                resolve(response.data);
              } else {

                reject();
              }
            }, function onFailure(failure) {

              $log.error('Error during the /nations call');
              failure(failure);
            });
          });
        }
        , getAmountSell = function getNations() {

          return $q(function deferred(resolve, reject) {

            $http({
              'method': 'GET',
              'url': 'api/amount-sell'
            }).then(function onSuccess(response) {

              if (response &&
                response.data) {

                resolve(response.data);
              } else {

                reject();
              }
            }, function onFailure(failure) {

              $log.error('Error during the /amount-sell call');
              failure(failure);
            });
          });
        }
        , getAmountBuy = function getAmountBuy() {

          return $q(function deferred(resolve, reject) {

            $http({
              'method': 'GET',
              'url': 'api/amount-buy'
            }).then(function onSuccess(response) {

              if (response &&
                response.data) {

                resolve(response.data);
              } else {

                reject();
              }
            }, function onFailure(failure) {

              $log.error('Error during the /amount-buy call');
              failure(failure);
            });
          });
        };

      return {
        'getTheLatestTradeMessages': getTheLatestTradeMessages,
        'getNations': getNations,
        'getAmountSell': getAmountSell,
        'getAmountBuy': getAmountBuy
      };
  }]);
}(angular));
