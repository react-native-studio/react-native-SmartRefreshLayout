import React, {Component} from 'react';
import {
    StyleSheet,
    View,
    Text,
    requireNativeComponent,
    ViewPropTypes,
    findNodeHandle,
    UIManager,
} from 'react-native';
import PropTypes from 'prop-types';

const RCTDefaultHeader = requireNativeComponent('RCTDefaultHeader', RCTDefaultHeader);

class DefaultHeader extends Component {

    render() {
        return (
            <RCTDefaultHeader
                ref="refreshLayout"
                {...this.props}
            />

        )
    }
}

DefaultHeader.propTypes = {
    ...ViewPropTypes,
}
export default DefaultHeader;