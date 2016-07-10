import SalesgraphModule from './salesgraph'
import SalesgraphController from './salesgraph.controller';
import SalesgraphComponent from './salesgraph.component';
import SalesgraphTemplate from './salesgraph.html';

describe('Salesgraph', () => {
  let $rootScope, makeController;

  beforeEach(window.module(SalesgraphModule.name));
  beforeEach(inject((_$rootScope_) => {
    $rootScope = _$rootScope_;
    makeController = () => {
      return new SalesgraphController({});
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
      expect(SalesgraphTemplate).to.match(/<p>Sales by System<\/p>/g);
    });
  });

  describe('Component', () => {
      // component/directive specs
      let component = SalesgraphComponent;

      it('includes the intended template',() => {
        expect(component.template).to.equal(SalesgraphTemplate);
      });

      it('uses `controllerAs` syntax', () => {
        expect(component).to.have.property('controllerAs');
      });

      it('invokes the right controller', () => {
        expect(component.controller).to.equal(SalesgraphController);
      });
  });
});
