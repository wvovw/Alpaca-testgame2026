# 游戏架构说明 (Game Architecture Documentation)

## 系统架构概览 (System Architecture Overview)

### 1. 核心层 (Core Layer)

#### GameEngine.kt - 游戏引擎
- **职责**: 60FPS游戏主循环，协调所有子系统
- **关键功能**:
  - 管理游戏更新周期
  - 协调所有游戏系统的更新
  - 处理游戏重置

#### GameState.kt - 游戏状态
- **职责**: 维护游戏全局状态
- **管理内容**:
  - 玩家和敌方基地
  - 所有角色列表
  - 游戏胜负状态
  - 游戏时间

#### GameConstants.kt - 游戏常量
- **职责**: 定义所有游戏配置常量
- **包含内容**:
  - 屏幕尺寸和FPS设置
  - 金钱系统参数
  - 基地位置和生命值
  - UI布局参数

### 2. 实体层 (Entity Layer)

#### Position.kt - 位置数据
- 二维坐标表示
- 距离计算方法

#### CharacterType.kt - 角色类型枚举
- 5种动物角色定义
- 每种角色的完整属性

#### AnimalCharacter.kt - 动物角色实体
- **职责**: 表示游戏中的战斗单位
- **属性**: 生命值、攻击力、防御力、速度、射程
- **功能**: 攻击冷却、伤害计算、生命管理

#### BaseEntity.kt - 基地实体
- **职责**: 表示玩家和敌方基地
- **功能**: 生命值管理、伤害接收

### 3. 系统层 (Systems Layer)

#### MoneyManager.kt - 金钱管理系统
- **职责**: 管理游戏经济
- **功能**:
  - 每秒自动增加金钱
  - 消费验证
  - 余额查询

#### CharacterSpawner.kt - 角色召唤系统
- **职责**: 处理玩家召唤角色
- **功能**:
  - 验证金钱是否足够
  - 在玩家基地附近生成角色
  - 扣除召唤费用

#### EnemyWaveManager.kt - 敌人波次管理
- **职责**: 自动生成敌人
- **功能**:
  - 每3秒生成一个敌人
  - 随机选择敌人类型
  - 每30秒提升难度等级
  - 根据难度强化敌人属性

#### BattleArena.kt - 战斗竞技场
- **职责**: 管理玩家角色的自动移动
- **功能**:
  - 角色向敌方基地自动前进
  - 检测攻击范围内的敌人
  - 有敌人时停止移动
  - 更新所有角色状态

#### EnemyAI.kt - 敌人AI
- **职责**: 控制敌人自动行为
- **功能**:
  - 敌人向玩家基地自动前进
  - 检测攻击范围内的玩家角色
  - 有目标时停止移动

#### CombatSystem.kt - 战斗系统
- **职责**: 处理所有战斗逻辑
- **功能**:
  - 自动寻找攻击目标
  - 计算伤害（攻击 - 防御，最低1）
  - 处理角色间战斗
  - 处理对基地的攻击
  - 管理攻击冷却

#### PhysicsEngine.kt - 物理引擎
- **职责**: 处理碰撞检测
- **功能**:
  - 距离计算
  - 范围检测
  - 最近敌人查找

### 4. UI层 (UI Layer)

#### GameView.kt - 游戏视图
- **职责**: 主游戏画布和渲染入口
- **功能**:
  - SurfaceView实现
  - 游戏线程管理
  - 触摸事件处理
  - 协调所有渲染

#### UIRenderer.kt - UI渲染器
- **职责**: 渲染游戏元素
- **功能**:
  - 绘制召唤按钮
  - 绘制角色和血条
  - 绘制基地和基地血条
  - Canvas绘图操作

#### ButtonManager.kt - 按钮管理器
- **职责**: 管理5个召唤按钮
- **功能**:
  - 按钮布局定义
  - 触摸检测
  - 触发角色召唤

#### ResourceDisplay.kt - 资源显示
- **职责**: 显示游戏信息
- **功能**:
  - 显示当前金钱
  - 显示基地生命值
  - 显示游戏结束信息

## 数据流 (Data Flow)

```
用户点击按钮
    ↓
ButtonManager.handleTouch()
    ↓
CharacterSpawner.spawnCharacter()
    ↓
MoneyManager.spend()
    ↓
创建AnimalCharacter添加到GameState
    ↓
每帧更新:
    BattleArena.update() - 移动玩家角色
    EnemyAI.update() - 移动敌人
    CombatSystem.update() - 处理战斗
    ↓
UIRenderer.draw() - 渲染到屏幕
```

## 游戏循环 (Game Loop)

```
60 FPS循环 (每帧约16.67ms):
1. GameEngine.update()
   ├─ GameState.update() - 检查游戏结束条件
   ├─ MoneyManager.update() - 增加金钱
   ├─ EnemyWaveManager.update() - 生成敌人
   ├─ BattleArena.update() - 移动角色
   ├─ EnemyAI.update() - 移动敌人
   ├─ CombatSystem.update() - 处理战斗
   └─ GameState.removeDeadCharacters() - 清理死亡单位

2. GameView.draw()
   ├─ 绘制背景
   ├─ UIRenderer.drawBases() - 绘制基地
   ├─ UIRenderer.drawCharacters() - 绘制所有角色
   ├─ ResourceDisplay.drawMoney() - 显示金钱
   ├─ ResourceDisplay.drawBases() - 显示基地信息
   ├─ UIRenderer.drawButtons() - 绘制按钮
   └─ ResourceDisplay.drawGameOver() - (如果游戏结束)
```

## 战斗机制 (Combat Mechanics)

### 伤害计算
```
实际伤害 = max(1, 攻击力 - 目标防御力)
```

### 攻击优先级
1. 首先攻击射程内最近的敌对角色
2. 如果没有角色在范围内，攻击敌方基地（如果到达）

### 移动逻辑
- 玩家角色向左移动（朝敌方基地）
- 敌人向右移动（朝玩家基地）
- 检测到攻击范围内有敌人时停止移动
- 到达对方基地时停止移动

## 性能优化 (Performance Optimization)

1. **对象复用**: 角色死亡后从列表中移除，而不是标记
2. **帧率控制**: 严格的60FPS控制，避免过度渲染
3. **碰撞检测**: 只检测必要的距离，使用简单的欧几里得距离
4. **渲染优化**: 使用SurfaceView进行硬件加速渲染

## 扩展性 (Extensibility)

系统设计支持轻松添加：
- 新的角色类型（在CharacterType枚举中添加）
- 新的游戏机制（添加新的System类）
- 新的UI元素（扩展UIRenderer）
- 技能系统（扩展AnimalCharacter类）
- 多波次设计（扩展EnemyWaveManager）
