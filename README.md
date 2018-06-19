# react-native-SmartRefreshLayout(完全可使用RN自定义的下拉刷新插件)[![npm version](https://badge.fury.io/js/react-native-smartrefreshlayout.svg)](https://badge.fury.io/js/react-native-smartrefreshlayout)
基于android SmartRefreshLayout https://github.com/scwang90/SmartRefreshLayout 开发的插件，可提供类似ios的弹性刷新,如果你喜欢，请不要吝啬你的 :smile: star :smile:
## 第一步
工程目录下运行<br> `npm install --save react-native-smartrefreshlayout`<br> 或者<br> `yarn add react-native-smartrefreshlayout`(已经安装了yarn)
## 第二步
运行 `react-native link react-native-smartrefreshlayout`
## 第三部使用
在工程中导入：
```js
import {SmartRefreshControl,ClassicsHeader,StoreHouseHeader,DefaultHeader} from 'react-native-smartrefreshlayout';

//使用方法和RN官方的RefreshControl类似，
<ScrollView 
  refreshControl={<SmartRefreshControl
     ref={refreshcontrol=>this.refreshControl=refreshcontrol}
     HeaderComponent={<DefaultHeader/>}
     onRefresh={()=>{
       setTimeout(()=>{
       this.refreshControl && this.refreshControl.finishRefresh();
       },1000)
     }}
  />}
>
</ScrollView>
```
## 组件
### SmartRefreshControl
#### 属性表格
|属性名|类型|描述|
|:---:|:---:|:---:|
|onRefresh|func|刷新触发|
|onPullDownToRefresh|func|可下拉刷新时触发|
|onReleaseToRefresh|func|可释放刷新时触发|
|onHeaderPulling|func|header下拉过程触发|
|onHeaderReleasing|func|header释放过程触发|
|enableRefresh|bool|是否启用刷新|
|headerHeight|number|设置Header的高度|
|primaryColor|string|刷新控件主调色|
|autoRefresh|{refresh,timeout}|设置自动刷新|
|HeaderComponent|Component|refreshcontrol的header|

注意：HeaderComponet现在支持任意的RN组件，但是需要放在AnyHeader的组件中，其中onHeaderPulling和onHeaderReleasing的参数为{nativeEvent:{percent,offset,headerHeight}},可用来控制下拉和释放过程中更为精细的动画
如果下拉和释放过程不需要过程动画，则使用onPullDownToRefresh和onReleaseToRefresh即可实现，请看示例：Example
#### 方法表格
|方法名|参数|描述|
|:---:|:---:|:---:|
|finishRefresh|{delayed:number,success:bool}|完成刷新|

### AnyHeader
#### 属性表格
|属性名|类型|描述|
|:---:|:---:|:---:|
|primaryColor|string|主题色|

### ClassicsHeader/DefaultHeader
#### 属性表格
|属性名|类型|描述|
|:---:|:---:|:---:|
|primaryColor|string|主题色|
|accentColor|string|强调色|

### StoreHouseHeader
#### 属性表格
|属性名|类型|描述|
|:---:|:---:|:---:|
|text|string|文字(目前只支持英文)|
|textColor|string|文字颜色|
|lineWidth|number|线宽|
## 示例
<!--![image](https://github.com/2534290808/react-native-android-danmaku/blob/master/images/Screenshot_1513176625.png)-->
<img src="https://github.com/react-native-studio/react-native-smartrefreshlayout/blob/master/images/Screenshot_1520489605.png" width = "300"  alt="图片名称" align=center />
<img src="https://github.com/react-native-studio/react-native-smartrefreshlayout/blob/master/images/Screenshot_1520489593.png" width = "300"  alt="图片名称" align=center />

