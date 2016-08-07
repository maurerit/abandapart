import angular from 'angular';
import uiRouter from 'angular-ui-router';
import buyCalculatorComponent from './buy_calculator.component';

let buy_calculatorModule = angular.module('buy_calculator', [
  uiRouter
])
.config(/*@ngInject*/($stateProvider, $urlRouterProvider) => {
  $urlRouterProvider.otherwise('/');

  $stateProvider
    .state('buyCalculator', {
      url: '/buyCalculator',
      template: '<buy-calculator></buy-calculator>'
    });
})
.component('buyCalculator', buyCalculatorComponent);

export default buy_calculatorModule;
