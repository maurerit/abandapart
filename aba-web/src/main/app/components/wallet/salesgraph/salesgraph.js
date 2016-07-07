import angular from 'angular';
import uiRouter from 'angular-ui-router';
import salesgraphComponent from './salesgraph.component';

let salesgraphModule = angular.module('salesgraph', [
  uiRouter
])

.component('salesgraph', salesgraphComponent);

export default salesgraphModule;
