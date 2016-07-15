import angular from 'angular';
import uiRouter from 'angular-ui-router';
import walletComponent from './wallet.component';

import chartJs from 'chart.js';
import angularCharts from 'angular-chart.js';

let walletModule = angular.module('wallet', [
  uiRouter,
  'chart.js',
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
