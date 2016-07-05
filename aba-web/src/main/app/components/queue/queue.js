import angular from 'angular';
import uiRouter from 'angular-ui-router';
import queueComponent from './queue.component';

let queueModule = angular.module('queue', [
  uiRouter
])
.config(/*@ngInject*/($stateProvider, $urlRouterProvider) => {
  $urlRouterProvider.otherwise('/');

  $stateProvider
    .state('queue', {
      url: '/queue',
      template: '<queue></queue>'
    });
})
.component('queue', queueComponent);

export default queueModule;
