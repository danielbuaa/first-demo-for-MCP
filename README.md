# GitHub MCP功能测试仓库
## 项目概述
这个仓库是专门为了在Claude AI上测试GitHub Model Context Protocol (MCP)的功能而创建的。仓库中的内容可能会看起来有些杂乱，因为它包含了各种不同类型的文件、脚本和代码示例，这些都是为了测试Claude与GitHub集成的各种功能。

## 仓库内容
本仓库目前包含一个基本的Spring Boot应用程序结构，具有以下组件：

- 控制器层 (Controller)
- 服务层 (Service)
- 数据访问层 (DAO/Repository)
- 模型/实体类 (Model/Entity)
- 配置文件 (包括MySQL, Redis, ZooKeeper等配置模板)

## 运行方法
### 前提条件


- JDK 17或更高版本
- Maven 3.6+
- 可选：MySQL, Redis, ZooKeeper (具体依赖于您启用的功能)

### 本地运行


1. 克隆仓库到本地：
```bash
git clone https://github.com/danielbuaa/first-demo-for-MCP.git
```

2. 导入到您喜欢的IDE中 (IntelliJ IDEA, Eclipse, VS Code等)


3. 配置application.properties或application.yml文件中的必要设置


4. 运行FirstDemoApplication类启动应用程序


### 使用Maven运行

```bash
mvn spring-boot:run
```

## 项目目的
这个仓库主要用于：

- 测试Claude AI的GitHub MCP功能
- 展示Claude能够自动生成和组织代码结构的能力
- 提供一个可以被二次开发的Spring Boot项目模板
- 验证通过AI创建的项目是否可以直接运行和使用

## 注意事项

- 这个仓库中的代码仅用于测试和演示目的
- 配置文件中的敏感信息位置已被留空，需要在本地运行时填入
- 项目结构可能会随着测试需求而变化