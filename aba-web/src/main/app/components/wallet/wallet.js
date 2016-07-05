import angular from 'angular';
import uiRouter from 'angular-ui-router';
import walletComponent from './wallet.component';

let walletModule = angular.module('wallet', [
  uiRouter
])
.config(/*@ngInject*/($stateProvider, $urlRouterProvider) => {
  $urlRouterProvider.otherwise('/');

  $stateProvider
    .state('wallet', {
      url: '/wallet',
      template: '<wallet></wallet>'
    });
})
.component('wallet', walletComponent);

export default walletModule;
