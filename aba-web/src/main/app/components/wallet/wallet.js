import angular from 'angular';
import uiRouter from 'angular-ui-router';
import walletComponent from './wallet.component';

import chartJs from 'chart.js';
import angularCharts from 'angular-chart.js';

import SalesGraph from 'components/wallet/salesgraph/salesgraph';
import SalesAndExpenditures from 'components/wallet/salesandexpenditures/salesandexpenditures';
import ExpendituresGraph from 'components/wallet/expendituresgraph/expendituresgraph';

let walletModule = angular.module('wallet', [
  uiRouter,
  'chart.js',
  SalesGraph.name,
  SalesAndExpenditures.name,
  ExpendituresGraph.name
])
.config(/*@ngInject*/($stateProvider, $urlRouterProvider, ChartJsProvider) => {
  $urlRouterProvider.otherwise('/');

  $stateProvider
    .state('wallet', {
      url: '/wallet',
      template: '<wallet></wallet>'
    });

    // Configure all charts
    ChartJsProvider.setOptions({
      colours: ['#97BBCD', '#DCDCDC', '#F7464A', '#46BFBD', '#FDB45C', '#949FB1', '#4D5360'],
      responsive: true
    });
})
.component('wallet', walletComponent);

export default walletModule;
