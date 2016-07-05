import template from './queue.html';
import controller from './queue.controller';
import './queue.less';

let queueComponent = {
  restrict: 'E',
  bindings: {},
  template,
  controller,
  controllerAs: 'vm'
};

export default queueComponent;
