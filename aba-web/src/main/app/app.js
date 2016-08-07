import angular from 'angular';
import uiRouter from 'angular-ui-router';
import Common from 'common/common';
import Components from 'components/components';
import AppComponent from './app.component';
//import 'normalize.css';
import angularMaterial from 'angular-material';
import angularAnimate from 'angular-animate';
import angularAria from 'angular-aria';

angular.module('app', [
    uiRouter,
    angularMaterial,
    angularAnimate,
    angularAria,
    Common.name,
    Components.name
])
.config(($locationProvider) => {
    "ngInject";
    // @see: https://github.com/angular-ui/ui-router/wiki/Frequently-Asked-Questions
    // #how-to-configure-your-server-to-work-with-html5mode
    //TODO: Figure this little bit out later...
    $locationProvider.html5Mode(true).hashPrefix('!');
})
.config(($mdThemingProvider) => {
    "ngInject";

    $mdThemingProvider.theme('default')
        .primaryPalette('brown')
        .accentPalette('red');
})
.component('app', AppComponent);
