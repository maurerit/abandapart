import { createStore, applyMiddleware } from 'redux';
import thunkMiddleware from 'redux-thunk';
import createLogger from 'redux-logger';
import producers from './Producers.reducers';

const loggerMiddleware = createLogger();

export default function configureStore(preloadedState) {
    return createStore(
        producers,
        preloadedState,
        applyMiddleware(
            thunkMiddleware,
            loggerMiddleware
        )
    )
}