import React, { Component } from 'react';
import {
    requireNativeComponent,
    ViewPropTypes
} from 'react-native';

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