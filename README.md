# java-simple-sdk

#### 参考自：https://blog.csdn.net/qq_35433926/article/details/99317773；https://www.jdon.com/idea/publish-to-center-maven.html

#### 主要目的：上传自己开发的sdk到maven中央仓库

* 实现效果，java项目pom.xml引入maven依赖
    ```$xslt
    <dependency>
      <groupId>io.github.misszgdzdx</groupId>
      <artifactId>java-simple-sdk</artifactId>
      <version>1.0.0</version>
    </dependency>
    ```

* 实现步骤如下：
  * 注册github.com或gitee.comm或其他代码托管平台
  
  * 将本地项目上传至上述代码托管平台
  
  * 注册sonatype: https://issues.sonatype.org/secure/Signup!default.jspa；注册后请保管好用户名和密码
  
  * 登陆sonatype，点击顶部tab新建表单；这里需要注意groupId和自己的域名有关
    ```$xslt
    项目：Community Support - Open...
    问题类型：New Project
    Group Id: 需要参考https://central.sonatype.org/pages/choosing-your-coordinates.html
    Project URL: https://github.com/misszgdzdx/java-simple-sdk
    Scm URL: https://github.com/misszgdzdx/java-simple-sdk.git
    ```
    
  * 表单创建审批：sonatype会给你发一条评论，这里主要是groupId可能会有问题，因为需要验证你是否是groupId域名的拥有者
    ```$xslt
    如果是自己的域名：配置域名验证，添加解析txt类型指向jira地址，子域名取jira地址最后如OSSRH-63133
    如果没有域名，修改groupId：eg: io.github.username或者com.github.username
    ```
    
  * 回复sonatype让他们审核确定下即可，审核完后他们会发一条确认评论，这是我的审核jira，可参考：
    ```$xslt
    https://issues.sonatype.org/browse/OSSRH-63133
    ```
  
  * 安装gpg：https://www.gnupg.org/download/
    ```$xslt
    下拉页面下载：Windows | Gpg4win	 | Full featured Windows version of GnuPG
    ```
  
  * 生成秘钥：打开cmd，输入`gpg --full-gen-key`; 会让你选择秘钥类型，不懂就一直回车，后面会让你输入用户名、邮箱以及秘钥密码
  
  * 上传秘钥：打开cmd，输入`gpg --list-key`显示如下：
    ```$xslt
    本地地址/pubring.kbx
    ----------------------------------------------
    pub   rsa3072 2021-01-01 [SC]
          这里是需要上传的秘钥
    uid           [ultimate] 用户名 (用户名) <邮箱>
    sub   rsa3072 2021-01-01 [E]
    ```
    上传秘钥有四个地址，输入如下：
    ```$xslt
    gpg --keyserver http://keyserver.ubuntu.com:11371 --send-keys 上传的秘钥
    gpg --keyserver http://keys.openpgp.org:11371 --send-keys 上传的秘钥
    gpg --keyserver http://keys.gnupg.net:11371 --send-keys 上传的秘钥
    gpg --keyserver http://pool.sks-keyservers.net:11371 --send-keys 上传的秘钥
    ```
    
  * 设置本地setting.xml：添加如下设置：
    ```$xslt
    <servers>
      <server>
        <id>ossrh</id>
        <username>sonatype用户名</username>
        <password>sonatype密码</password>
      </server>
    </servers>
    
    <profiles>
      <profile>
          <id>ossrh</id>
          <activation>
            <activeByDefault>true</activeByDefault>
          </activation>
          <properties>
            <gpg.passphrase>秘钥密码</gpg.passphrase>
            <gpg.executable>gpg安装目录\bin\gpg.exe</gpg.executable>
            <gpg.homedir>上传秘钥那里的本地地址</gpg.homedir>
          </properties>
      </profile>
    </profiles>
    ```
    
  * 设置pom.xml：参考release-pom.xml即可，`<version><version>`建议用正式版本号，不然会有未知问题
  
  * 上传项目：在项目根目录下输入`mvn clean deploy -Dmaven.test.skip=true`等待完成上传
  
  * 查看项目：https://oss.sonatype.org/#welcome；这里大概两个小时会同步过来：https://mvnrepository.com
  
  * 关闭jira：关闭sonatype的jira并说句感谢，sdk上传至maven仓库完毕
  
    
> 备注：上传到中央仓库的pom.xml是用的release-pom.xml中设置
> 结束：感谢参考的两个地址，我只是自己写一遍加深印象，有需要参考的朋友们，也可以参考，不足之处请指出