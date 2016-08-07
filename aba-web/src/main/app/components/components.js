import angular from 'angular';
import Header from 'components/header/header';
import Navigation from 'components/navigation/navigation';
import About from 'components/about/about';

import Points from 'components/points/points';
import Queue from 'components/queue/queue';
import Timesheet from 'components/timesheet/timesheet';
import Wallet from 'components/wallet/wallet';
import Watch from 'components/watch/watch';
import BuyCalculator from 'components/buy_calculator/buy_calculator';

let componentModule = angular.module('app.components', [
  Header.name,
  Navigation.name,
  About.name,

  Points.name,
  Queue.name,
  Timesheet.name,
  Wallet.name,
  Watch.name,
  BuyCalculator.name
]);

export default componentModule;
