import angular from 'angular';
import uiRouter from 'angular-ui-router';
import queue_editComponent from './queue_edit.component';

let queue_editModule = angular.module('queue_edit', [
  uiRouter
])

.component('queue_edit', queue_editComponent);

export default queue_editModule;
