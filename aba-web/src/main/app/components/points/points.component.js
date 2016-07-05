import template from './points.html';
import controller from './points.controller';
import './points.less';

let pointsComponent = {
  restrict: 'E',
  bindings: {},
  template,
  controller,
  controllerAs: 'vm'
};

export default pointsComponent;
