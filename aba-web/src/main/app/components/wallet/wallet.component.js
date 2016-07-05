import template from './wallet.html';
import controller from './wallet.controller';
import './wallet.less';

let walletComponent = {
  restrict: 'E',
  bindings: {},
  template,
  controller,
  controllerAs: 'vm'
};

export default walletComponent;
