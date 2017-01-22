import React, {Component, PropTypes} from 'react';
import Drawer from 'material-ui/Drawer';
import {List, ListItem, makeSelectable} from 'material-ui/List';
import GroupWork from 'material-ui/svg-icons/action/group-work';
import Person from 'material-ui/svg-icons/action/face';
import Build from 'material-ui/svg-icons/action/build';
import Home from 'material-ui/svg-icons/action/home';
import Divider from 'material-ui/Divider';
import {Link} from 'react-router';

let SelectableList = makeSelectable(List);

function wrapState(ComposedComponent) {
    return class SelectableList extends Component {
        static propTypes = {
            children: PropTypes.node.isRequired,
            defaultValue: PropTypes.string.isRequired,
        };

        componentWillMount() {
            this.setState({
                selectedIndex: this.props.defaultValue,
            });
        }

        render() {
            return (
                <ComposedComponent
                    value={this.props.selectedIndex}
                >
                    {this.props.children}
                </ComposedComponent>
            );
        }
    };
}

SelectableList = wrapState(SelectableList);

const navStyle = {
    "paddingTop": "64px",
    "width": "300px"
};

class NavDrawer extends Component {
    constructor ( props ) {
        super(props);
        this.state = {open: true};
    }

    render() {
        return (
            <div>
                <Drawer containerStyle={navStyle} open={this.state.open}>
                    <SelectableList defaultValue="/" selectedIndex={this.props.location.pathname}>
                        <ListItem value="/" containerElement={<Link to="/" />} primaryText="Home" leftIcon={<Home />} />
                        <ListItem value="/tasks" containerElement={<Link to="/tasks" />} primaryText="Tasks" leftIcon={<GroupWork />} />
                        <ListItem value="/producers" containerElement={<Link to="/producers" />} primaryText="Producers" leftIcon={<Person />} />
                        <ListItem value="/buildables" containerElement={<Link to="/buildables" />} primaryText="Buildables" leftIcon={<Build />} />
                        <Divider />
                    </SelectableList>
                </Drawer>
            </div>
        )
    }
}

export default NavDrawer;