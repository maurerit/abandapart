import Buy_calculatorModule from './buy_calculator'
import Buy_calculatorController from './buy_calculator.controller';
import Buy_calculatorComponent from './buy_calculator.component';
import Buy_calculatorTemplate from './buy_calculator.html';

describe('Buy_calculator', () => {
  let $rootScope, makeController;

  beforeEach(window.module(Buy_calculatorModule.name));
  beforeEach(inject((_$rootScope_) => {
    $rootScope = _$rootScope_;
    makeController = () => {
      return new Buy_calculatorController();
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
      expect(Buy_calculatorTemplate).to.match(/{{\s?vm\.name\s?}}/g);
    });
  });

  describe('Component', () => {
      // component/directive specs
      let component = Buy_calculatorComponent;

      it('includes the intended template',() => {
        expect(component.template).to.equal(Buy_calculatorTemplate);
      });

      it('uses `controllerAs` syntax', () => {
        expect(component).to.have.property('controllerAs');
      });

      it('invokes the right controller', () => {
        expect(component.controller).to.equal(Buy_calculatorController);
      });
  });
});
