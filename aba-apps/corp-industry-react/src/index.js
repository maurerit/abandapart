import React from "react";
import ReactDOM from "react-dom";
import {Router, Route, IndexRoute, hashHistory} from "react-router";
import App from "./App";
import Home from './components/view/Home';
import Tasks from './components/view/Tasks';
import Producers from './components/view/Producers';
import Buildables from './components/view/Buildables';
import "./index.css";

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
