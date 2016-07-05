import template from './timesheet.html';
import controller from './timesheet.controller';
import './timesheet.less';

let timesheetComponent = {
  restrict: 'E',
  bindings: {},
  template,
  controller,
  controllerAs: 'vm'
};

export default timesheetComponent;
