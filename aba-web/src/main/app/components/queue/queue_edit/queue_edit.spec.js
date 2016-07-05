import Queue_editModule from './queue_edit'
import Queue_editController from './queue_edit.controller';
import Queue_editComponent from './queue_edit.component';
import Queue_editTemplate from './queue_edit.html';

describe('Queue_edit', () => {
  let $rootScope, makeController;

  beforeEach(window.module(Queue_editModule.name));
  beforeEach(inject((_$rootScope_) => {
    $rootScope = _$rootScope_;
    makeController = () => {
      return new Queue_editController();
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
      expect(Queue_editTemplate).to.match(/{{\s?vm\.name\s?}}/g);
    });
  });

  describe('Component', () => {
      // component/directive specs
      let component = Queue_editComponent;

      it('includes the intended template',() => {
        expect(component.template).to.equal(Queue_editTemplate);
      });

      it('uses `controllerAs` syntax', () => {
        expect(component).to.have.property('controllerAs');
      });

      it('invokes the right controller', () => {
        expect(component.controller).to.equal(Queue_editController);
      });
  });
});
