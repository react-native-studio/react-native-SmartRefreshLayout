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

const RCTAnyHeader = requireNativeComponent('RCTAnyHeader', RCTAnyHeader);

class AnyHeader extends Component {

    render() {
        return (
            <RCTAnyHeader
                {...this.props}
            />

        )
    }
}

AnyHeader.propTypes = {
    primaryColor:PropTypes.string,
    ...ViewPropTypes,
}
export default AnyHeader;