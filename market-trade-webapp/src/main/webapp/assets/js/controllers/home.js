/*global angular*/

(function withAngular(angular) {
  'use strict';

  angular.module('marketTrade.controllers.graph', [])

  .controller('Home', ['$log', '$rootScope', '$scope', '$q', 'RandomColor', 'TradeMessages',
    function dataController($log, $rootScope, $scope, $q, RandomColor, TradeMessages) {

      var triggerCalls = function triggerCalls() {

          var toPromise = {
            'nations': TradeMessages.getNations(),
            'amountsS': TradeMessages.getAmountSell(),
            'amountsB': TradeMessages.getAmountBuy()
          };

          $q.all(toPromise).then(function onSucess(returns) {

            if (returns) {

              var nations = returns.nations
                , amountsS = returns.amountsS
                , amountsB = returns.amountsB
                , nationsLength
                , nationsIndex
                , aNation
                , amountsLength
                , amountsIndex
                , aAmount;

              if (nations) {

                nationsLength = nations.length;
                $scope.nationsDatas = {};
                $scope.nationsDatas.labels = [];
                $scope.nationsDatas.datasets = [{
                  'fillColor': 'rgba(151,187,205,0)',
                  'strokeColor': '#e67e22',
                  'pointColor': 'rgba(151,187,205,0)',
                  'pointStrokeColor': '#e67e22',
                  'data': []
                }];
                for (nationsIndex = 0; nationsIndex < nationsLength; nationsIndex += 1) {

                  aNation = nations[nationsIndex];
                  if (aNation) {

                    $scope.nationsDatas.labels.push(aNation.nation);
                    $scope.nationsDatas.datasets[0].data.push(aNation.counter);
                  }
                }
                if (nationsLength > 0) {

                  $scope.nationShow = true;
                }
              }

              if (amountsS) {

                amountsLength = amountsS.length;
                $scope.amountSellDatas = [];
                for (amountsIndex = 0; amountsIndex < amountsLength; amountsIndex += 1) {

                  aAmount = amountsS[amountsIndex];
                  if (aAmount) {

                    $scope.amountSellDatas.push({
                      'value': aAmount.amountSold,
                      'color': RandomColor.get(true),
                      'highlight': RandomColor.get(true),
                      'label': aAmount.userIdentifier
                    });
                  }
                }
                if (amountsLength > 0) {

                  $scope.amountsSShow = true;
                }
              }

              if (amountsB) {

                amountsLength = amountsB.length;
                $scope.amountBuyDatas = [];
                for (amountsIndex = 0; amountsIndex < amountsLength; amountsIndex += 1) {

                  aAmount = amountsB[amountsIndex];
                  if (aAmount) {

                    $scope.amountBuyDatas.push({
                      'value': aAmount.amountBought,
                      'color': RandomColor.get(true),
                      'highlight': RandomColor.get(true),
                      'label': aAmount.userIdentifier
                    });
                  }
                }
                if (amountsLength > 0) {

                  $scope.amountsBShow = true;
                }
              }
            }
          });
        }
        , unregisterTradeMessagesArrived = $rootScope.$on('trade-message:new', function onEventArrived(eventInfos, parsedMsg) {

          if (parsedMsg) {

            if ($scope.tradeMessages.length > 12) {

              $scope.tradeMessages.splice($scope.tradeMessages.length - 1, 1);
            }

            var toPut = angular.copy(parsedMsg);
            toPut.color = RandomColor.get(true);
            $scope.tradeMessages.unshift(toPut);
          } else {

            $log.error('Event from trade-message:new is invalid', parsedMsg);
          }

          triggerCalls();
        })
        , unregisterViewContentLoaded = $scope.$on('$viewContentLoaded', function onContentLoaded() {

          $scope.tradeMessages = [];
          $scope.nationShow = false;
          $scope.amountsSShow = false;
          $scope.amountsBShow = false;
          TradeMessages.getTheLatestTradeMessages().then(function onSucess(tradeMessages) {

            $scope.tradeMessages = tradeMessages.splice(0, 12);
            angular.forEach($scope.tradeMessages, function iterator(anElement) {

              if (anElement) {

                anElement.color = RandomColor.get(true);
              }
            });
          });

          triggerCalls();
        });

      $scope.$on('$destroy', function unregisterListener() {

        unregisterTradeMessagesArrived();
        unregisterViewContentLoaded();
      });
  }]);
}(angular));
