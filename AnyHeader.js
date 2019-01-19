import React, {Component} from 'react';
import {findNodeHandle, requireNativeComponent, UIManager,} from 'react-native';
import PropTypes from 'prop-types';
import {ViewPropTypes} from './Util'

const RCTAnyHeader = requireNativeComponent('RCTAnyHeader', RCTAnyHeader,
    {
        nativeOnly: {onHeaderMove: true, onFooterMove: true, onHeaderMoveFinished: true, onFooterMoveFinished: true}
    }
);

class AnyHeader extends Component {

    render() {
        return (
            <RCTAnyHeader
                {...this.props}
                ref={'anyHeader'}
            />

        )
    }

    scrollTo = ({y, x, animated}) => {
        this.dispatchCommand('scrollTo', [{y, x, animated}])
    };

    findNode = () => {
        return findNodeHandle(this.refs.anyHeader);
    };

    dispatchCommand = (commandName, params) => {
        UIManager.dispatchViewManagerCommand(this.findNode(), UIManager.RCTAnyHeader.Commands[commandName], params);
    }
}

AnyHeader.propTypes = {
    primaryColor: PropTypes.string,
    onHeaderMove: PropTypes.func,
    onFooterMove: PropTypes.func,
    onHeaderMoveFinished: PropTypes.func,
    onFooterMoveFinished: PropTypes.func,
    ...ViewPropTypes,
};

AnyHeader.defaultProps = {
};
export default AnyHeader;