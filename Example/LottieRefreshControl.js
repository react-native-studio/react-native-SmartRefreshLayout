/**
 * 类似华为手机的下拉刷新
 */
import React, {Component} from 'react';
import {
    StyleSheet,
    View,
    Text,
    Animated,
    Easing
} from 'react-native';
import PropTypes from 'prop-types';
import LottieView from 'lottie-react-native'
import {SmartRefreshControl, AnyHeader} from 'react-native-smartrefreshlayout';

export default class LottieRefreshControl extends Component {
    state = {
        scale: new Animated.Value(0.1)
    }
    _onRefresh = () => {
        let {onRefresh} = this.props;
        onRefresh && onRefresh();
        this.lottieView.play(this.state.scale.__getValue())

    }
    finishRefresh=(params)=>{
        this._refreshc && this._refreshc.finishRefresh(params);
        this.lottieView.reset();
    }
    _onHeaderMoving=(event)=>{
        let {percent} = event.nativeEvent;
        if(percent<=1) {
            this.state.scale.setValue(event.nativeEvent.percent);
        }
    }
    render() {
        return (
            <SmartRefreshControl
                onHeaderMoving={this._onHeaderMoving}
                style={{flex:1}}
                ref={ref => this._refreshc = ref}
                children={this.props.children}
                onRefresh={this._onRefresh}
                headerHeight={100}
                HeaderComponent={
                    <AnyHeader>
                        <Animated.View style={{height:100,justifyContent:'center',alignItems:'center',transform: [{
                            scale:this.state.scale.interpolate({
                            inputRange: [0,1,2],
                            outputRange: [0.1,1,1],
                        })
                        }]}}>
                        <LottieView speed={2} ref={obj =>this.lottieView = obj} style={{width:100,height:100}} hardwareAccelerationAndroid  progress={this.state.scale} source={require('./cycle_animation.json')} />
                        </Animated.View>
                    </AnyHeader>
                }
            />
        )
    }
}