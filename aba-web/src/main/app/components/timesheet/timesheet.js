import angular from 'angular';
import uiRouter from 'angular-ui-router';
import timesheetComponent from './timesheet.component';

let timesheetModule = angular.module('timesheet', [
  uiRouter
])

.component('timesheet', timesheetComponent);

export default timesheetModule;
