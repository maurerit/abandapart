import React, {Component} from "react";
import "./App.css";
import TopNav from "./components/TopNav";
import NavDrawer from "./components/NavDrawer";
import MuiThemeProvider from "material-ui/styles/MuiThemeProvider";
import getMuiTheme from "material-ui/styles/getMuiTheme";
import {brown700, brown500, grey400, deepOrangeA700, deepOrangeA400, deepOrangeA200, darkBlack, fullWhite} from "material-ui/styles/colors";

const myTheme = getMuiTheme({
    palette: {
        primary1Color: brown700,
        primary2Color: brown500,
        primary3Color: grey400,
        accent1Color: deepOrangeA700,
        accent2Color: deepOrangeA400,
        accent3Color: deepOrangeA200,
        textColor: darkBlack,
        alternateTextColor: fullWhite
    }
});

const zIndex = {
    zIndex: {
        drawer: 1000
    }
};

const mainContentStyle = {
    "marginLeft": "300px",
    "paddingLeft": "20px",
    "paddingTop": "70px",
    "paddingBottom": "20px",
    "paddingRight": "20px"
};

class App extends Component {
    render() {
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
