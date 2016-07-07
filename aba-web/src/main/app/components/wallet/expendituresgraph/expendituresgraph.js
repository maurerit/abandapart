import angular from 'angular';
import uiRouter from 'angular-ui-router';
import expendituresgraphComponent from './expendituresgraph.component';

let expendituresgraphModule = angular.module('expendituresgraph', [
  uiRouter
])

.component('expendituresgraph', expendituresgraphComponent);

export default expendituresgraphModule;
