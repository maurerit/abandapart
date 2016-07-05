import angular from 'angular';
import uiRouter from 'angular-ui-router';
import pointsComponent from './points.component';

let pointsModule = angular.module('points', [
  uiRouter
])

.component('points', pointsComponent);

export default pointsModule;
