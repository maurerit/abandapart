import template from './watch.html';
import controller from './watch.controller';
import './watch.less';

let watchComponent = {
  restrict: 'E',
  bindings: {},
  template,
  controller,
  controllerAs: 'vm'
};

export default watchComponent;
