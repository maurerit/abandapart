import angular from 'angular';
import uiRouter from 'angular-ui-router';
import pointsComponent from './points.component';

let pointsModule = angular.module('points', [
  uiRouter
])
.config(/*@ngInject*/($stateProvider, $urlRouterProvider) => {
  $urlRouterProvider.otherwise('/');

  $stateProvider
    .state('points', {
      url: '/points',
      template: '<points></points>'
    });
})
.component('points', pointsComponent);

export default pointsModule;
