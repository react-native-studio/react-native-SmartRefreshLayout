import React, {Component} from 'react';
import {requireNativeComponent} from 'react-native';
import {ViewPropTypes, PropTypes} from './Util'

const RCTDefaultHeader = requireNativeComponent('RCTDefaultHeader', RCTDefaultHeader);

export default class DefaultHeader extends Component {
    static propTypes = {
        primaryColor: PropTypes.string,
        accentColor: PropTypes.string,
        ...ViewPropTypes,
    }

    render() {
        return (<RCTDefaultHeader {...this.props}/>)
    }
}