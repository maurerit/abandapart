import TimesheetModule from './timesheet'
import TimesheetController from './timesheet.controller';
import TimesheetComponent from './timesheet.component';
import TimesheetTemplate from './timesheet.html';

describe('Timesheet', () => {
  let $rootScope, makeController;

  beforeEach(window.module(TimesheetModule.name));
  beforeEach(inject((_$rootScope_) => {
    $rootScope = _$rootScope_;
    makeController = () => {
      return new TimesheetController();
    };
  }));

  describe('Module', () => {
    // top-level specs: i.e., routes, injection, naming
  });

  describe('Controller', () => {
    // controller specs
    it('has a name property [REMOVE]', () => { // erase if removing this.name from the controller
      let controller = makeController();
      expect(controller).to.have.property('name');
    });
  });

  describe('Template', () => {
    // template specs
    // tip: use regex to ensure correct bindings are used e.g., {{  }}
    it('has name in template [REMOVE]', () => {
      expect(TimesheetTemplate).to.match(/{{\s?vm\.name\s?}}/g);
    });
  });

  describe('Component', () => {
      // component/directive specs
      let component = TimesheetComponent;

      it('includes the intended template',() => {
        expect(component.template).to.equal(TimesheetTemplate);
      });

      it('uses `controllerAs` syntax', () => {
        expect(component).to.have.property('controllerAs');
      });

      it('invokes the right controller', () => {
        expect(component.controller).to.equal(TimesheetController);
      });
  });
});
