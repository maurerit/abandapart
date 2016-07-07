import angular from 'angular';
import uiRouter from 'angular-ui-router';
import salesandexpendituresComponent from './salesandexpenditures.component';

let salesandexpendituresModule = angular.module('salesandexpenditures', [
  uiRouter
])

.component('salesandexpenditures', salesandexpendituresComponent);

export default salesandexpendituresModule;
