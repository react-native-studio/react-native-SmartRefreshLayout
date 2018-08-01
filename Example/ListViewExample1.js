import React, {Component} from 'react';
import {StyleSheet, View, Text,ListView,ScrollView} from 'react-native';
import PropTypes from 'prop-types';
import HuaWeiRefreshControl from './HuaWeiRefreshControl';

export default class ListViewExample1 extends Component {
    constructor(props){
        super(props);
        var ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});
        this.state = {
            dataSource: ds.cloneWithRows(['row 1', 'row 2','row 3','row 4','row 5','row 6','row 7','row 8']),
        };
    }
    _onRefresh=()=>{
        setTimeout(()=>{
            this._hw && this._hw.finishRefresh()
        },1000)
    }
    render() {
        return (
            <View style={{flex: 1}}>
                <ListView
                    refreshControl={<HuaWeiRefreshControl
                        ref={ref=>this._hw = ref}
                        onRefresh={this._onRefresh}
                    />}
                    dataSource={this.state.dataSource}
                    renderRow={(rowData) => <Text onPress={()=>alert(111)} style={{height:100,borderColor:'black',borderWidth:1}}>{rowData}</Text>}
                />
            </View>
        )
    }
}