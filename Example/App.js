/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, {Component} from 'react';
import {
    Platform,
    StyleSheet,
    Text,
    View,
    ScrollView,
    Animated,
    Easing
} from 'react-native';
import {SmartRefreshControl, AnyHeader} from 'react-native-smartrefreshlayout';
import Icon from 'react-native-vector-icons/Ionicons'
import {DotIndicator,SkypeIndicator} from 'react-native-indicators';
const AnimatedIcon = Animated.createAnimatedComponent(Icon)
const instructions = Platform.select({
    ios: 'Press Cmd+R to reload,\n' +
    'Cmd+D or shake for dev menu',
    android: 'Double tap R on your keyboard to reload,\n' +
    'Shake or press menu button for dev menu',
});
Date.prototype.Format = function (fmt) { // author: meizz
    var o = {
        "M+": this.getMonth() + 1, // 月份
        "d+": this.getDate(), // 日
        "h+": this.getHours(), // 小时
        "m+": this.getMinutes(), // 分
        "s+": this.getSeconds(), // 秒
        "q+": Math.floor((this.getMonth() + 3) / 3), // 季度
        "S": this.getMilliseconds() // 毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
type Props = {};
export default class App extends Component<Props> {
    state = {
        text:'下拉刷新',
        rotate:new Animated.Value(0),
        refreshing:false
    }

    render() {
        return (
            <ScrollView
                refreshControl={
                    <SmartRefreshControl
                        ref={ref=>this._refreshc = ref}
                        onRefresh={()=>{
                            console.log('onRefresh'+((new Date()).Format("yyyy-MM-dd hh:mm:ss.S")))

                            setTimeout(()=>{
                                this._refreshc && this._refreshc.finishRefresh();
                            },1000)
                        }}
                        onPullDownToRefresh={()=>{
                            this.setState({
                                text:'下拉刷新',
                                refreshing:false
                            })
                            Animated.timing(this.state.rotate,{
                                toValue:0,
                                duration:197,
                                useNativeDriver:true,
                                easing:Easing.linear()
                            }).start()
                            }
                        }
                        onHeaderReleased={(e)=>{
                            console.log('onHeaderReleased'+((new Date()).Format("yyyy-MM-dd hh:mm:ss.S")))
                            this.setState({
                                refreshing:true,
                                text:'正在刷新'
                            });
                        }}
                        onReleaseToRefresh={()=>{
                            this.setState({
                                text:'释放刷新'
                            })
                            Animated.timing(this.state.rotate,{
                                toValue:1,
                                duration:197,
                                useNativeDriver:true,
                                easing:Easing.linear()
                            }).start()
                        }}
                        headerHeight={100}
                        HeaderComponent={
                            <AnyHeader style={{
                                height: 100,
                                flexDirection: 'row',
                                alignItems: 'center',
                                justifyContent: 'center',
                            }}>
                                {this.state.refreshing?<SkypeIndicator style={{flex:0}} size={24} color={'#2783cf'}/>:<AnimatedIcon style={{
                                    transform:[{
                                        rotate:this.state.rotate.interpolate({
                                            inputRange:[0,1],
                                            outputRange:['180deg','0deg']
                                        })
                                    }]
                                }} name="md-arrow-up" color="#2783cf" size={24}/>}
                                <Text style={{marginLeft:15}}>{this.state.text}</Text>
                            </AnyHeader>
                        }
                    />
                }
            >
                <View style={styles.container}>
                    <Text style={styles.instructions}>
                        To get started, edit App.js
                    </Text>
                    <Text style={styles.instructions}>
                        {instructions}
                    </Text>
                </View>
            </ScrollView>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#F5FCFF',
    },
    welcome: {
        fontSize: 20,
        textAlign: 'center',
        margin: 10,
    },
    instructions: {
        textAlign: 'center',
        color: '#333333',
        marginBottom: 5,
    },
});
