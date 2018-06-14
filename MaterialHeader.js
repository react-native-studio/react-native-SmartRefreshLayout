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

const RCTMaterialHeader = requireNativeComponent('RCTMaterialHeader', RCTMaterialHeader);

class MaterialHeader extends Component {

    render() {
        return (
            <RCTMaterialHeader
                {...this.props}
            />

        )
    }
}

MaterialHeader.propTypes = {
    ...ViewPropTypes,
}
export default MaterialHeader;