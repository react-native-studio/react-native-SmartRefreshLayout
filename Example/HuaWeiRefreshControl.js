/**
 * 类似华为手机的下拉刷新
 */
import React, {Component} from 'react';
import {Animated, Easing, Text} from 'react-native';
import {SkypeIndicator} from 'react-native-indicators';
import {
  AnyHeader,
  SmartRefreshControl,
} from '@nectr-rn/react-native-smartrefreshlayout';
import Icon from 'react-native-vector-icons/Ionicons';

const AnimatedIcon = Animated.createAnimatedComponent(Icon);
export default class HuaWeiRefreshControl extends Component {
  state = {
    text: '下拉刷新',
    rotate: new Animated.Value(0),
    refreshing: false,
  };
  _onPullDownToRefresh = () => {
    this.setState({
      text: '下拉刷新',
      refreshing: false,
    });
    Animated.timing(this.state.rotate, {
      toValue: 0,
      duration: 197,
      useNativeDriver: true,
      easing: Easing.linear(),
    }).start();
  };
  _onReleased = () => {
    this.setState({
      refreshing: true,
      text: '正在刷新',
    });
  };
  _onReleaseToRefresh = () => {
    this.setState({
      text: '释放刷新',
    });
    Animated.timing(this.state.rotate, {
      toValue: 1,
      duration: 197,
      useNativeDriver: true,
      easing: Easing.linear(),
    }).start();
  };
  _onRefresh = () => {
    let {onRefresh} = this.props;
    onRefresh && onRefresh();
  };
  finishRefresh = params => {
    this._refreshc && this._refreshc.finishRefresh(params);
  };
  render() {
    return (
      <SmartRefreshControl
        primaryColor="#ffcc03"
        style={{flex: 1}}
        ref={ref => (this._refreshc = ref)}
        children={this.props.children}
        onRefresh={this._onRefresh}
        onPullDownToRefresh={this._onPullDownToRefresh}
        onHeaderReleased={this._onReleased}
        onReleaseToRefresh={this._onReleaseToRefresh}
        headerHeight={150}
        renderHeader={
          <AnyHeader
            style={{
              height: 150,
              flexDirection: 'row',
              alignItems: 'center',
              justifyContent: 'center',
            }}>
            {this.state.refreshing ? (
              <SkypeIndicator style={{flex: 0}} size={24} color={'#2783cf'} />
            ) : (
              <AnimatedIcon
                style={{
                  transform: [
                    {
                      rotate: this.state.rotate.interpolate({
                        inputRange: [0, 1],
                        outputRange: ['180deg', '0deg'],
                      }),
                    },
                  ],
                }}
                name="md-arrow-up"
                color="#2783cf"
                size={24}
              />
            )}
            <Text style={{marginLeft: 15}}>{this.state.text}</Text>
          </AnyHeader>
        }
      />
    );
  }
}
