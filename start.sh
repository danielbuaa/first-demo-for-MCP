#!/bin/bash

# 检查Java版本
echo "当前Java版本:"
java -version

# 设置Java 17环境
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
export PATH=$JAVA_HOME/bin:$PATH

echo "切换后的Java版本:"
java -version

# 使用Maven编译项目
echo "开始编译项目..."
mvn clean package

# 查找并运行最新的jar文件
JAR_FILE=$(find target -name "*.jar" | head -n 1)
if [ -z "$JAR_FILE" ]; then
    echo "错误: 未找到可执行的jar文件"
    exit 1
fi

echo "运行jar文件: $JAR_FILE"
nohup java -jar $JAR_FILE > app.log 2>&1 &
echo "应用已在后台运行，日志输出到app.log"