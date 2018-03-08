# react-native-SmartRefreshLayout
基于android SmartRefreshLayout https://github.com/scwang90/SmartRefreshLayout 开发的插件，可提供类似ios的弹性刷新、加载
## 第一步
工程目录下运行 npm install --save react-native-smartrefreshlayout 或者 yarn add react-native-smartrefreshlayout(已经安装了yarn)
## 第二步
运行 react-native link react-native-smartrefreshlayout
## 第三部使用
在工程中导入：
```bash
import {SmartRefreshControl,ClassicsHeader,StoreHouseHeader} from 'react-native-smartrefreshlayout';

//使用方法和RN官方的RefreshControl类似，
<ScrollView 
  refreshControl={<SmartRefreshControl
     ref={this.refreshControl=ref}
     HeaderComponent={<ClassicsHeader/>}
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
|name|format|description|
|:---:|:---:|:---:|
|onRefresh|func|刷新触发|
|enableRefresh|bool|是否启用刷新|
|HeaderComponent|Component|refreshcontrol的header|

注意：HeaderComponet现在只能是插件提供的Header，后续我们将改进与提供任何Component
#### 方法表格
|name|format|description|
|:---:|:---:|:---:|
|finishRefresh|{delayed:number,success:bool}|完成刷新|
## 示例
<!--![image](https://github.com/2534290808/react-native-android-danmaku/blob/master/images/Screenshot_1513176625.png)-->
<!--<img src="https://github.com/2534290808/react-native-android-danmaku/blob/master/images/Screenshot_1513176625.png" width = "300"  alt="图片名称" align=center /-->

