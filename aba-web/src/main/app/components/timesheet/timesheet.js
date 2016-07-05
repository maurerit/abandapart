import angular from 'angular';
import uiRouter from 'angular-ui-router';
import timesheetComponent from './timesheet.component';

let timesheetModule = angular.module('timesheet', [
  uiRouter
])
.config(/*@ngInject*/($stateProvider, $urlRouterProvider) => {
  $urlRouterProvider.otherwise('/');

  $stateProvider
    .state('timesheet', {
      url: '/timesheet',
      template: '<timesheet></timesheet>'
    });
})
.component('timesheet', timesheetComponent);

export default timesheetModule;
