import React, { Component } from 'react';
import _ from 'lodash';
import './App.css';
import TopNav from './components/view/TopNav.view';
import NavDrawer from './components/view/NavDrawer.view';

import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import getMuiTheme from 'material-ui/styles/getMuiTheme';

import injectTapEventPlugin from 'react-tap-event-plugin';

import {
    brown700, brown500, grey400,
    darkBlack, fullWhite
} from 'material-ui/styles/colors';

const myTheme = getMuiTheme({
    palette: {
        primary1Color: brown700,
        primary2Color: brown500,
        primary3Color: grey400,
        textColor: darkBlack,
        alternateTextColor: fullWhite
    }
});

const tapInitOnce = _.once(() => injectTapEventPlugin());

const zIndex = {
    zIndex: {
        drawer: 1000
    }
};

const mainContentStyle = {
    "marginLeft": "300px",
    "paddingLeft": "20px"
};

class App extends Component {
  render() {
      tapInitOnce();
    return (
        <MuiThemeProvider muiTheme={getMuiTheme(myTheme, zIndex)}>
            <div>
                <TopNav {...this.props}/>
                <NavDrawer {...this.props}/>
                <div style={mainContentStyle}>
                    {this.props.children}
                </div>
            </div>
        </MuiThemeProvider>
    );
  }
}

export default App;
