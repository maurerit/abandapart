import angular from 'angular';
import uiRouter from 'angular-ui-router';
import watchComponent from './watch.component';

let watchModule = angular.module('watch', [
  uiRouter
])
.config(/*@ngInject*/($stateProvider, $urlRouterProvider) => {
  $urlRouterProvider.otherwise('/');

  $stateProvider
    .state('watch', {
      url: '/watch',
      template: '<watch></watch>'
    });
})
.component('watch', watchComponent);

export default watchModule;
