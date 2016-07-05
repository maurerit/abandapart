import angular from 'angular';
import uiRouter from 'angular-ui-router';
import buy_calculatorComponent from './buy_calculator.component';

let buy_calculatorModule = angular.module('buy_calculator', [
  uiRouter
])

.component('buy_calculator', buy_calculatorComponent);

export default buy_calculatorModule;
