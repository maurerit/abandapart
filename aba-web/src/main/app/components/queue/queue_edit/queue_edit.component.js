import template from './queue_edit.html';
import controller from './queue_edit.controller';
import './queue_edit.less';

let queue_editComponent = {
  restrict: 'E',
  bindings: {},
  template,
  controller,
  controllerAs: 'vm'
};

export default queue_editComponent;
