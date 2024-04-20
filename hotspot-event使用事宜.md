## 使用事宜

1.技术栈：Spring Boot 3 + Vue 3 + MyBatis + MySQL + Redis 前后分离且并未整合到一个服务器中，可自行操作

2.注意：因为使用到了阿里云提供的第三方上传图片到服务器的接口，其中涉及到了一些服务器的关键属性无法外传（存在时无法上传到github上），因此需要自行去阿里云官网创建Bucket，并使用自己的对应的信息，我在相应文件夹中的代码出有说明

![image](https://github.com/JhcZ/big-event/blob/master/image-20240410122412509.png)


3.MySQL与Redis配置在/resources/application.yml文件中，记得改成自己的配置

![image](https://github.com/JhcZ/big-event/blob/master/image-20240410122455337.png)



4.自行到对应目录下使用npm install 安装对应插件

5.任何事宜请微信联系：17349970086，备注来意
