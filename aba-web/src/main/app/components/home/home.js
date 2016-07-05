import angular from 'angular';
import uiRouter from 'angular-ui-router';
import homeComponent from './home.component';

let homeModule = angular.module('home', [
  'ui.router'
])

.config(/*@ngInject*/($stateProvider, $urlRouterProvider) => {
  $urlRouterProvider.otherwise('/');

  $stateProvider
    .state('home', {
      url: '/home',
      template: '<home></home>'
    });
})

.component('home', homeComponent);

export default homeModule;
