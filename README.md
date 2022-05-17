# PE_PPG_Mobie
for android

## 移植调整PC端软件说明
1. 当前版本 0.16.4
2. 具体操作
   1. 一些包的调整
      1. logging ——>Android 下的Log
      2. logging 功能暂时屏蔽
   2. 删除以下package
      1. batprocess/*.*
      2. nets/*.*
      3. output/pdf/*.*
      4. resource/batprocess/*.*
      5. ui/*.*
      6. util/Figure2Base64.java
