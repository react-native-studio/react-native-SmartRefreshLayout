import React, {Component} from 'react';
import {
    StyleSheet,
    View,
    Text,
    requireNativeComponent,
    findNodeHandle,
    UIManager,
} from 'react-native';
import {ViewPropTypes,PropTypes} from './Util'
const RCTDefaultHeader = requireNativeComponent('RCTDefaultHeader', RCTDefaultHeader);

class DefaultHeader extends Component {

    render() {
        return (
            <RCTDefaultHeader
                {...this.props}
            />

        )
    }
}

DefaultHeader.propTypes = {
    primaryColor:PropTypes.string,
    accentColor:PropTypes.string,
    ...ViewPropTypes,
}
export default DefaultHeader;