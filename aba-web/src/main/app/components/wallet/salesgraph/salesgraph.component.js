import template from './salesgraph.html';
import controller from './salesgraph.controller';
import './salesgraph.less';

let salesgraphComponent = {
  restrict: 'E',
  bindings: {},
  template,
  controller,
  controllerAs: 'vm'
};

export default salesgraphComponent;
