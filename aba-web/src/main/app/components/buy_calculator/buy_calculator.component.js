import template from './buy_calculator.html';
import controller from './buy_calculator.controller';
import './buy_calculator.less';

let buy_calculatorComponent = {
  restrict: 'E',
  bindings: {},
  template,
  controller,
  controllerAs: 'vm'
};

export default buy_calculatorComponent;
