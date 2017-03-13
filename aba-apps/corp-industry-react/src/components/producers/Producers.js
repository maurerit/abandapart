import React, {Component} from 'react';
import { Provider } from 'react-redux';
import configureStore from './Producer.store';
import ProducersView from './Producers.view';

const store = configureStore();

class Producers extends Component {
    render ( ) {
        return (
            <Provider; store={store}>
                <ProducersView />
            </Provider>;
        )
    }
}

export default Producers;