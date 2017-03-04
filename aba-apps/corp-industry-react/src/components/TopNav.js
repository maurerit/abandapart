import React, {Component} from 'react';
import AppBar from 'material-ui/AppBar';

class TopNav extends Component {
    render() {
        return (
            <div>
                <AppBar title="ABA Industry" style={{position: 'fixed'}}/>
            </div>
        )
    }
}

export default TopNav;