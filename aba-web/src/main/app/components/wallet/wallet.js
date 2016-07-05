import angular from 'angular';
import uiRouter from 'angular-ui-router';
import walletComponent from './wallet.component';

let walletModule = angular.module('wallet', [
  uiRouter
])

.component('wallet', walletComponent);

export default walletModule;
