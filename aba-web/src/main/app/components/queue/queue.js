import angular from 'angular';
import uiRouter from 'angular-ui-router';
import queueComponent from './queue.component';

let queueModule = angular.module('queue', [
  uiRouter
])

.component('queue', queueComponent);

export default queueModule;
