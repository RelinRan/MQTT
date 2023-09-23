### MQTT

Android MQTT连接,重新编译Service-1.1.1兼容Android高版本服务   
Paho Android Service-1.1.1      
Paho Client Mqtt3-1.1.0

### 资源

|名字|资源|
|-|-|
|AAR|[下载](https://github.com/RelinRan/MqttCore/blob/master/aar)|
|GitHub |[查看](https://github.com/RelinRan/MqttCore)|
|Gitee|[查看](https://gitee.com/relin/MqttCore)|

### Maven

1.build.grade

```
allprojects {
    repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

2./app/build.grade

```
dependencies {
	implementation 'com.github.RelinRan:MqttCore:2023.9.23.1'
}
```

### 初始化

配置权限

```
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.WAKE_LOCK" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
```

Service配置

```
<service android:name="org.eclipse.paho.android.service.MqttService"/>
```

初始化服务端

```
MqttOptions options = new MqttOptions();
options.setHost("tcp://xxx.iot-as-mqtt.cn-shanghai.aliyuncs.com:1883");
options.setClientId("xxxx.301F9A89A749|securemode=2,signmethod=hmacsha256,timestamp=1189770888615|");
options.setUserName("321F9A89A749&xxxx");
options.setPassword("64ac769d2f523d48730294bffe6323a566005543e3e372c802903f748f118a");
Imqtt mqtt = Mqtt.initialize(this,options);
```

### 添加连接监听

```
long cid = mqtt.addConnectListener(new OnConnectListener() {

    @Override
    public void onConnectionLost(Throwable cause) {
                
    }

    @Override
    public void onConnectionSuccessful(IMqttToken token) {

    }

    @Override
    public void onConnectionFailed(IMqttToken token, Throwable exception) {

    }
});
```

### 添加消息监听

```
long mid = mqtt.addMessageListener(new OnMessageListener() {

    @Override
    public void onMessageReceived(String topic, MqttMessage message) {
        
    }

    @Override
    public void onMessageDelivered(IMqttDeliveryToken token) {

    }
    
});
```

### 连接服务

```
mqtt.connect();
```

### 移除消息监听

```
mqtt.remove(mid);
```

### 移除连接监听

```
mqtt.remove(cid);
```

### 移除所有监听

```
mqtt.clear();
```

### 发送内容

```
mqtt.publish(String topic, String payload);
```

### 订阅主题

```
mqtt.subscribe(String topic);
```

