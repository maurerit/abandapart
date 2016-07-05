import angular from 'angular';
import Header from 'components/header/header';
import Navigation from 'components/navigation/navigation';
import Home from 'components/home/home';
import About from 'components/about/about';
import Footer from 'components/footer/footer'

import Points from 'components/points/points';
import Queue from 'components/queue/queue';
import Timesheet from 'components/timesheet/timesheet';
import Wallet from 'components/wallet/wallet';


let componentModule = angular.module('app.components', [
  Header.name,
  Navigation.name,
  Home.name,
  About.name,
  Footer.name,

  Points.name,
  Queue.name,
  Timesheet.name,
  Wallet.name,
]);

export default componentModule;
