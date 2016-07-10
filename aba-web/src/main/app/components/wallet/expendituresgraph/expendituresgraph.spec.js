import ExpendituresgraphModule from './expendituresgraph'
import ExpendituresgraphController from './expendituresgraph.controller';
import ExpendituresgraphComponent from './expendituresgraph.component';
import ExpendituresgraphTemplate from './expendituresgraph.html';

describe('Expendituresgraph', () => {
  let $rootScope, makeController;

  beforeEach(window.module(ExpendituresgraphModule.name));
  beforeEach(inject((_$rootScope_) => {
    $rootScope = _$rootScope_;
    makeController = () => {
      return new ExpendituresgraphController({});
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
      expect(ExpendituresgraphTemplate).to.match(/<p>Expenses by System<\/p>/g);
    });
  });

  describe('Component', () => {
      // component/directive specs
      let component = ExpendituresgraphComponent;

      it('includes the intended template',() => {
        expect(component.template).to.equal(ExpendituresgraphTemplate);
      });

      it('uses `controllerAs` syntax', () => {
        expect(component).to.have.property('controllerAs');
      });

      it('invokes the right controller', () => {
        expect(component.controller).to.equal(ExpendituresgraphController);
      });
  });
});
