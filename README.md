# Netty 学习记录

> 📖 本项目是我学习 Netty 过程中做的知识梳理与实战练习，记录学习的点滴，持续更新中。

## 关于 Netty

Netty 是一个基于 Java NIO 的网络应用框架，用于快速开发高性能、高可靠性的网络服务器和客户端。  
它封装了复杂的底层网络通信，让我们更专注于业务逻辑的开发。

**核心特点：**
- 异步非阻塞 IO
- 高性能事件驱动模型
- 灵活的线程模型
- 丰富的协议支持（HTTP、WebSocket、TCP、UDP 等）
- 社区活跃，文档完善

---

## 学习目标 🎯

- 理解 Netty 的基本原理：Reactor 模式、EventLoop、Channel、Pipeline 等
- 掌握使用 Netty 实现高性能的 TCP/HTTP 服务端与客户端
- 学习 Netty 中的编解码技术（ByteBuf、Codec）
- 掌握常见的应用场景，如：心跳检测、断线重连、数据包粘拆包处理
- 实战演练：开发一个简单的 IM 聊天室 / RPC 框架

---

## 目录结构 🗂
```
netty_train/
├── .gradle/
├── .idea/
├── build/
├── gradle/
├── netty-1-00/          # 第一个示例项目：基础入门
├── netty-1-01/          # 第二个示例项目：进阶用法
├── netty-1-02/          # 第三个示例项目：编解码与协议处理
├── netty-1-03/          # 第四个示例项目：粘包拆包、心跳检测等
├── netty-demo/          # 综合实战 Demo
├── src/                 # 公共源码目录
├── build.gradle
├── gradlew
├── gradlew.bat
├── HELP.md
├── settings.gradle
├── .gitignore
└── .gitattributes
```

---

## 学习计划 📝

- [x] Netty 入门与基础概念理解
- [x] 第一个 Echo Server & Client 实现
- [ ] 掌握 ChannelPipeline 和 Handler 的使用
- [ ] 编解码框架深入理解（ByteBuf / Codec）
- [ ] 粘包与拆包处理
- [ ] Netty 实现心跳检测与断线重连
- [ ] 手写一个简单的 RPC 框架
- [ ] 结合 Spring Boot 集成使用

---

## 笔记与总结 📒

> 学习过程中遇到的重点 & 易错点记录，详细笔记在 `docs/` 文件夹中。

- [ ] Netty 架构与线程模型解析
- [ ] NIO 与 Netty 的对比
- [ ] Netty 性能调优实践
- [ ] 常见异常与排查方法

---

![image](https://github.com/user-attachments/assets/07ce2102-2eb3-4fe7-9f2a-b19331cfd0d3)


## 参考资料 🔗

- [Netty 官方文档](https://netty.io/wiki/)
- 《Netty 实战：权威指南》
- [Awesome Netty](https://github.com/hsll-net/awesome-netty)
- [bugstack](https://github.com/fuzhengwei/itstack-demo-netty)

---

## 致未来的自己 🧭

学习 Netty 不是终点，而是掌握高性能网络编程的一块基石。持续精进，未来可期！

---

## License

本项目仅用于个人学习，开源共勉 🌟
