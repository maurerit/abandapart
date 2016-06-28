import angular from 'angular';
import uiRouter from 'angular-ui-router';
import navigationComponent from './navigation.component';

let navigationModule = angular.module('navigation', [
  uiRouter
])

.component('navigation', navigationComponent);

export default navigationModule;
