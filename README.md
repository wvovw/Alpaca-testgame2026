# 动物大作战 (Animal Battle) 🐱🐶🦅🐢🐰

可爱动物版塔防对战游戏 - Android Kotlin Tower Defense Game

## 游戏简介 (Game Overview)

这是一款自动战斗的策略塔防游戏。玩家通过召唤不同类型的动物角色来攻击敌方基地，同时防御敌人对自己基地的进攻。

This is an automatic combat strategy tower defense game. Players summon different animal characters to attack the enemy base while defending their own base from enemy attacks.

## 游戏特性 (Features)

### 🎮 完全自动战斗系统
- 角色自动移动和攻击
- 无需手动控制，完全策略导向
- 智能AI自动寻敌

### 💰 经济系统
- 初始金钱：100
- 每秒自动增长：+5金钱
- 合理管理资源召唤角色

### 🦸 5种可召唤角色

| 角色 | 成本 | 攻击 | 防御 | 生命值 | 速度 | 射程 |
|------|------|------|------|--------|------|------|
| 🐱 猫战士 | 50 | 12 | 1 | 40 | 3 | 1 |
| 🐶 狗守卫 | 75 | 8 | 3 | 60 | 2 | 1 |
| 🦅 鹰弓手 | 60 | 10 | 1 | 35 | 4 | 3 |
| 🐢 龟坦克 | 100 | 6 | 4 | 100 | 1 | 1 |
| 🐰 兔法师 | 80 | 14 | 1 | 40 | 3 | 4 |

### 🏰 双基地对战
- 玩家基地：1000 HP（右侧）
- 敌方基地：1000 HP（左侧）
- 保护己方基地，摧毁敌方基地即可获胜

### 📈 难度递增系统
- 敌人每3秒自动生成
- 每30秒难度等级+1
- 敌人属性随难度提升

## 项目结构 (Project Structure)

```
app/src/main/kotlin/com/animalbattle/
├── core/                      # 核心游戏引擎
│   ├── GameEngine.kt         # 60FPS游戏循环
│   ├── GameState.kt          # 游戏状态管理
│   └── GameConstants.kt      # 游戏常量
├── entities/                  # 游戏实体
│   ├── BaseEntity.kt         # 基地实体类
│   ├── AnimalCharacter.kt    # 动物角色基类
│   ├── CharacterType.kt      # 5种角色类型定义
│   └── Position.kt           # 位置数据类
├── systems/                   # 游戏系统
│   ├── BattleArena.kt        # 战斗竞技场管理
│   ├── MoneyManager.kt       # 金钱自动增长系统
│   ├── CharacterSpawner.kt   # 角色召唤系统
│   ├── EnemyWaveManager.kt   # 敌人自动生成
│   ├── EnemyAI.kt            # 敌人AI
│   ├── CombatSystem.kt       # 自动战斗系统
│   └── PhysicsEngine.kt      # 碰撞检测
└── ui/                        # 用户界面
    ├── GameView.kt           # 游戏画布和主渲染
    ├── UIRenderer.kt         # UI元素渲染
    ├── ButtonManager.kt      # 5个按钮管理
    └── ResourceDisplay.kt    # 资源和生命值显示
```

## 构建和运行 (Build and Run)

### 环境要求
- Android Studio Arctic Fox或更高版本
- Android SDK 24 (Android 7.0) 或更高
- Kotlin 1.9.0
- Gradle 8.0

### 构建步骤

1. 克隆仓库
```bash
git clone https://github.com/wvovw/Alpaca-testgame2026.git
cd Alpaca-testgame2026
```

2. 使用Android Studio打开项目

3. 同步Gradle依赖

4. 运行到设备或模拟器

或使用命令行：
```bash
./gradlew assembleDebug
./gradlew installDebug
```

## 游戏玩法 (Gameplay)

1. **游戏开始**：游戏自动开始，金钱开始累积
2. **召唤角色**：点击底部5个按钮之一召唤对应角色
3. **自动战斗**：角色自动向左移动并攻击敌人
4. **策略选择**：根据战况选择合适的角色类型
5. **胜利条件**：摧毁敌方基地（HP降至0）
6. **失败条件**：己方基地被摧毁（HP降至0）

## 技术特点 (Technical Features)

- ✅ 60FPS流畅游戏循环
- ✅ 基于SurfaceView的高性能渲染
- ✅ 完整的游戏状态管理
- ✅ 模块化的系统设计
- ✅ 自动化的战斗和AI系统
- ✅ 横屏全屏游戏体验

## 开发者信息 (Developer Info)

Created for Android Kotlin game development practice.

## 许可证 (License)

MIT License
