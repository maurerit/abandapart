import React from "react";
import ReactDOM from "react-dom";
import {Router, Route, IndexRoute, hashHistory} from "react-router";
import App from "./App";
import Home from './components/Home';
import Tasks from './components/Tasks';
import Producers from './components/producers/Producers';
import Buildables from './components/Buildables';
import "./index.css";

import injectTapEventPlugin from 'react-tap-event-plugin';

// Needed for onTouchTap
// http://stackoverflow.com/a/34015469/988941
injectTapEventPlugin();

ReactDOM.render(
    <Router history={hashHistory}>
        <Route path="/" component={App}>
            <IndexRoute component={Home} />
            <Route path="/tasks" component={Tasks}/>
            <Route path="/producers" component={Producers}/>
            <Route path="/buildables" component={Buildables} />
        </Route>
    </Router>,
    document.getElementById('root')
);
