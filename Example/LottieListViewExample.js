import React, {Component} from 'react';
import {FlatList, ScrollView, Text, View} from 'react-native';
import LottieRefreshControl from './LottieRefreshControl';

export default class LottieListViewExample extends Component {
  constructor(props) {
    super(props);
    this.state = {
      data: [
        'row 1',
        'row 2',
        'row 3',
        'row 4',
        'row 5',
        'row 6',
        'row 7',
        'row 8',
      ],
    };
  }
  _onRefresh = () => {
    setTimeout(() => {
      this._hw && this._hw.finishRefresh();
    }, 1000);
  };
  render() {
    return (
      <View style={{flex: 1}}>
        <FlatList
          data={this.state.data}
          renderRow={rowData => (
            <Text onPress={() => alert(111)} style={{height: 100}}>
              {rowData}
            </Text>
          )}
          renderScrollComponent={props => (
            <ScrollView
              style={{flex: 1}}
              refreshControl={
                <LottieRefreshControl
                  ref={ref => (this._hw = ref)}
                  onRefresh={this._onRefresh}
                />
              }
              {...props}
            />
          )}
        />
      </View>
    );
  }
}
