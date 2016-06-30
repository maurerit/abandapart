import angular from 'angular';
import Header from 'components/header/header';
import Navigation from 'components/navigation/navigation';
import Home from 'components/home/home';
import About from 'components/about/about';
import Footer from 'components/footer/footer'


let componentModule = angular.module('app.components', [
  Header.name,
  Navigation.name,
  Home.name,
  About.name,
  Footer.name
]);

export default componentModule;
