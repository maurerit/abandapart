import React, {Component} from 'react';
import Paper from 'material-ui/Paper';
import RaisedButton from 'material-ui/RaisedButton';
import KeyboardArrayUp from 'material-ui/svg-icons/hardware/keyboard-arrow-up';
import KeyboardArrowDown from 'material-ui/svg-icons/hardware/keyboard-arrow-down';

const style = {
    height: 100,
    margin: 20,
    textAlign: 'center',
};

const sortSpanStyle = {
    // lineHeight: "2em",
    marginRight: "15px"
};

const sortIconStyles = {
    height: "25px",
    width: "15px",
    zDepth: 1
};

class Buildables extends Component {
    constructor( props ) {
        super(props);
        this.state = {
            buildTimeAscending: true,
            profitAscending: true
        };
    }

    touchSortButton = ( buttonName ) => {
        switch ( buttonName ) {
            case "BuildTimeUp":
            case "BuildTimeDown":
                this.setState({buildTimeAscending: !this.state.buildTimeAscending, profitAscending: this.state.profitAscending});
                break;
            case "ProfitUp":
            case "ProfitDown":
                this.setState({buildTimeAscending: this.state.buildTimeAscending, profitAscending: !this.state.profitAscending});
                break;
            default:
                break;
        }
    };


    render ( ) {
        return (
            <Paper style={style} zDepth={1}>
                Sorting/Display<br/><br/>
                <span style={sortSpanStyle}>
                    Build Time:&nbsp;
                    <RaisedButton style={sortIconStyles} primary={true} disabled={this.state.buildTimeAscending} onTouchTap={() => this.touchSortButton("BuildTimeUp")}>
                        <KeyboardArrayUp/>
                    </RaisedButton>
                    <RaisedButton style={sortIconStyles} primary={true} disabled={!this.state.buildTimeAscending} onTouchTap={() => this.touchSortButton("BuildTimeDown")}>
                        <KeyboardArrowDown/>
                    </RaisedButton>
                </span>
                <span style={sortSpanStyle}>
                    Profit Amount:&nbsp;
                    <RaisedButton style={sortIconStyles} primary={true} disabled={this.state.profitAscending} onTouchTap={() => this.touchSortButton("ProfitUp")}>
                        <KeyboardArrayUp/>
                    </RaisedButton>
                    <RaisedButton style={sortIconStyles} primary={true} disabled={!this.state.profitAscending} onTouchTap={() => this.touchSortButton("ProfitDown")}>
                        <KeyboardArrowDown/>
                    </RaisedButton>
                </span>
            </Paper>
        )
    }
}

export default Buildables;