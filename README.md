# 动物大作战 (Animal Battle) 🐱⚔️

一款以可爱喵星人为主角的Android横版射击对战游戏！

## 📖 项目简介

《动物大作战》是一款充满创意和趣味的动作游戏，玩家将扮演勇敢的喵星战士，使用独特的技能和能力，对抗入侵的敌人，保卫喵星世界！

### 🌟 游戏特色

- 🐱 **多样的喵星人角色**: 战士、法师、弓手、坦克四大职业
- 💫 **创新技能系统**: 喵喵攻击、九命神功、猫薄荷狂暴等独特技能
- 🎮 **丰富的玩法**: 连击系统、猫粮收集、天气系统、喵窝基地
- 👾 **多样的敌人**: 从普通敌人到多阶段Boss战
- 🏆 **成就系统**: 多种成就等你解锁
- 🎨 **可爱画风**: 萌系美术风格，色彩丰富

## 📂 项目结构

```
Alpaca-testgame2026/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/alpaca/animalbattle/  # 游戏源代码
│   │       │   ├── MainActivity.kt              # 主界面Activity
│   │       │   ├── GameActivity.kt              # 游戏Activity
│   │       │   ├── GameEngine.kt                # 游戏引擎
│   │       │   ├── GameObject.kt                # 游戏对象基类
│   │       │   ├── CatPlayer.kt                 # 喵星人玩家类
│   │       │   └── Enemy.kt                     # 敌人类
│   │       ├── res/                             # Android资源文件
│   │       │   ├── drawable/                    # 图片资源
│   │       │   ├── layout/                      # 布局文件
│   │       │   └── values/                      # 配置文件
│   │       └── assets/                          # 游戏素材
│   │           ├── characters/                  # 角色资源
│   │           ├── enemies/                     # 敌人资源
│   │           ├── backgrounds/                 # 背景资源
│   │           ├── effects/                     # 特效资源
│   │           ├── ui/                          # UI资源
│   │           └── sounds/                      # 音效资源
│   └── build.gradle                             # App构建配置
├── build.gradle                                 # 项目构建配置
├── settings.gradle                              # 项目设置
├── GAMEPLAY.md                                  # 游戏玩法详细说明
└── ART_ASSETS_README.md                         # 美术资源导入指南
```

## 🚀 快速开始

### 环境要求

- Android Studio Arctic Fox (2020.3.1) 或更高版本
- JDK 8 或更高版本
- Android SDK (API Level 21+)
- Kotlin 1.8.0

### 构建步骤

1. **克隆仓库**
```bash
git clone https://github.com/wvovw/Alpaca-testgame2026.git
cd Alpaca-testgame2026
```

2. **打开项目**
- 启动Android Studio
- 选择 "Open an Existing Project"
- 选择项目目录

3. **构建项目**
```bash
./gradlew build
```

4. **运行游戏**
- 连接Android设备或启动模拟器
- 点击Android Studio的 "Run" 按钮
- 或使用命令行: `./gradlew installDebug`

## 🎮 游戏玩法

详细的游戏玩法说明请查看 [GAMEPLAY.md](GAMEPLAY.md)

### 基本操作

- **移动**: 屏幕左侧虚拟摇杆
- **攻击**: 自动攻击最近的敌人
- **技能**: 屏幕右侧技能按钮
- **暂停**: 右上角暂停按钮

### 核心系统

1. **技能系统**: 四大独特技能，合理使用获得优势
2. **连击系统**: 连续攻击累积连击数，提升分数倍率
3. **收集系统**: 收集猫粮升级角色属性
4. **成就系统**: 完成成就解锁奖励

## 🎨 美术资源

游戏框架已经建立，需要导入美术资源才能完整运行。

详细的美术资源导入指南请查看 [ART_ASSETS_README.md](ART_ASSETS_README.md)

### 资源文件夹

美术资源应放置在以下目录：

- `app/src/main/assets/characters/` - 角色精灵图
- `app/src/main/assets/enemies/` - 敌人精灵图
- `app/src/main/assets/backgrounds/` - 背景图片
- `app/src/main/assets/effects/` - 特效动画
- `app/src/main/assets/ui/` - UI元素
- `app/src/main/assets/sounds/` - 音效文件

## 📱 技术架构

### 核心组件

- **MainActivity**: 游戏主菜单和入口
- **GameActivity**: 游戏主循环管理
- **GameEngine**: 游戏引擎，处理更新和渲染
- **GameObject**: 游戏对象基类
- **CatPlayer**: 玩家角色实现
- **Enemy**: 敌人系统实现

### 技术特点

- 基于SurfaceView的游戏渲染
- 自定义游戏循环系统（~60 FPS）
- 面向对象的游戏架构
- 灵活的技能系统设计
- 可扩展的敌人AI系统

## 🔧 开发说明

### 添加新角色

1. 继承 `GameObject` 或 `CatPlayer` 类
2. 实现 `update()` 和 `render()` 方法
3. 添加角色特有的属性和技能
4. 在资源文件中添加对应的美术资源

### 添加新敌人

1. 继承 `Enemy` 类
2. 实现敌人的AI逻辑
3. 设置血量、伤害和分数
4. 添加到 `GameEngine` 的生成系统

### 自定义关卡

修改 `GameEngine.kt` 中的关卡生成逻辑，可以：
- 调整敌人生成频率
- 设计特殊的关卡事件
- 添加关卡特有的机制

## 🤝 贡献指南

欢迎贡献代码、美术资源或提出建议！

1. Fork 本仓库
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启 Pull Request

## 📄 许可证

本项目仅用于学习和演示目的。

## 📞 联系方式

- 项目主页: https://github.com/wvovw/Alpaca-testgame2026
- Issue跟踪: https://github.com/wvovw/Alpaca-testgame2026/issues

---

**祝你游戏愉快！让我们一起保卫喵星世界！** 🐱✨
