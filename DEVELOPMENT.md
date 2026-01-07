# 开发指南

## 项目开发说明

本文档为开发者提供详细的开发指南。

## 代码结构

### 核心类说明

#### MainActivity.kt
主界面Activity，负责：
- 显示游戏主菜单
- 处理开始游戏、设置、退出等按钮事件
- 跳转到游戏界面

#### GameActivity.kt  
游戏Activity，负责：
- 初始化游戏视图
- 创建和管理GameEngine
- 更新UI（分数、关卡、生命值）
- 处理游戏暂停/继续

#### GameEngine.kt
游戏引擎，核心功能：
- 游戏主循环（约60FPS）
- 更新所有游戏对象
- 渲染游戏画面
- 碰撞检测
- 敌人生成
- 游戏状态管理

#### GameObject.kt
游戏对象基类，所有游戏实体的父类：
- 位置和大小属性
- 速度属性
- update() 方法：更新对象状态
- render() 方法：渲染对象
- intersects() 方法：碰撞检测

#### CatPlayer.kt
喵星人玩家类，包含：
- 角色属性（血量、攻击力）
- 技能系统
- 技能冷却管理
- 状态效果（无敌、狂暴）
- 自定义渲染（绘制喵星人外观）

#### Enemy.kt
敌人系统，包含：
- Enemy基类
- DogEnemy: 基础敌人
- RobotEnemy: 中级敌人
- BossEnemy: Boss敌人，多阶段战斗

## 扩展功能指南

### 添加新技能

1. 在 `CatPlayer.kt` 中添加技能方法：
```kotlin
fun newSkill(): Boolean {
    if (skillCooldown <= 0) {
        skillCooldown = skillCooldownTime
        // 技能效果代码
        return true
    }
    return false
}
```

2. 在 `update()` 方法中更新冷却时间
3. 在 `strings.xml` 中添加技能名称
4. 在UI中添加技能按钮

### 添加新的游戏对象

1. 创建新类继承 `GameObject`
2. 实现 `update()` 和 `render()` 方法
3. 在 `GameEngine` 中管理该对象

例如，添加道具：
```kotlin
class PowerUp(x: Float, y: Float) : GameObject(x, y) {
    override fun update(deltaTime: Float) {
        // 道具逻辑
    }
    
    override fun render(canvas: Canvas) {
        // 绘制道具
    }
}
```

### 实现碰撞检测

在 `GameEngine.kt` 的 `checkCollisions()` 方法中：
```kotlin
private fun checkCollisions() {
    enemies.forEach { enemy ->
        if (catPlayer.intersects(enemy)) {
            // 处理碰撞
            catPlayer.takeDamage(enemy.damage)
        }
    }
}
```

### 添加音效

1. 将音效文件放入 `assets/sounds/`
2. 创建 SoundManager 类：
```kotlin
class SoundManager(private val context: Context) {
    private val soundPool = SoundPool.Builder().setMaxStreams(10).build()
    private val sounds = mutableMapOf<String, Int>()
    
    fun loadSound(name: String, assetPath: String) {
        val afd = context.assets.openFd(assetPath)
        val soundId = soundPool.load(afd, 1)
        sounds[name] = soundId
    }
    
    fun playSound(name: String) {
        sounds[name]?.let { soundPool.play(it, 1f, 1f, 0, 0, 1f) }
    }
}
```

### 保存游戏数据

使用 SharedPreferences 保存数据：
```kotlin
class GameDataManager(private val context: Context) {
    private val prefs = context.getSharedPreferences("game_data", Context.MODE_PRIVATE)
    
    fun saveHighScore(score: Int) {
        prefs.edit().putInt("high_score", score).apply()
    }
    
    fun getHighScore(): Int {
        return prefs.getInt("high_score", 0)
    }
}
```

## 性能优化建议

### 1. 对象池模式
避免频繁创建和销毁对象：
```kotlin
class ObjectPool<T>(private val factory: () -> T) {
    private val pool = mutableListOf<T>()
    
    fun obtain(): T {
        return if (pool.isNotEmpty()) {
            pool.removeAt(pool.size - 1)
        } else {
            factory()
        }
    }
    
    fun free(obj: T) {
        pool.add(obj)
    }
}
```

### 2. 减少绘制调用
- 合并相同类型的绘制操作
- 使用离屏渲染缓存静态内容
- 只绘制可见区域的对象

### 3. 优化碰撞检测
- 使用空间分区（四叉树等）
- 粗略检测 + 精确检测两步走
- 只检测可能碰撞的对象

## 调试技巧

### 显示调试信息
在 `GameEngine.render()` 中：
```kotlin
// 显示FPS
val fps = 1f / deltaTime
val debugPaint = Paint().apply {
    color = Color.WHITE
    textSize = 30f
}
canvas.drawText("FPS: ${fps.toInt()}", 10f, 50f, debugPaint)
```

### 日志记录
```kotlin
import android.util.Log

private val TAG = "GameEngine"
Log.d(TAG, "Game started")
Log.w(TAG, "Warning message")
Log.e(TAG, "Error message")
```

## 测试建议

### 单元测试
创建测试文件 `app/src/test/java/com/alpaca/animalbattle/`

### UI测试
使用 Espresso 框架测试UI交互

### 性能测试
使用 Android Profiler 监控：
- CPU使用率
- 内存使用
- 网络活动
- 电池消耗

## 发布流程

### 1. 生成签名密钥
```bash
keytool -genkey -v -keystore animal-battle.keystore -alias animal-battle -keyalg RSA -keysize 2048 -validity 10000
```

### 2. 配置 build.gradle
```gradle
android {
    signingConfigs {
        release {
            storeFile file("animal-battle.keystore")
            storePassword "your_password"
            keyAlias "animal-battle"
            keyPassword "your_password"
        }
    }
    buildTypes {
        release {
            signingConfig signingConfigs.release
        }
    }
}
```

### 3. 构建发布版本
```bash
./gradlew assembleRelease
```

### 4. 测试APK
在多个设备上测试发布版本APK

## 常见问题

### Q: 游戏卡顿怎么办？
A: 检查游戏循环中是否有耗时操作，使用Profiler定位性能瓶颈。

### Q: 如何添加多语言支持？
A: 在 `res/` 下创建 `values-zh/`, `values-en/` 等文件夹，放入对应的 strings.xml。

### Q: 如何适配不同屏幕尺寸？
A: 使用相对坐标，根据屏幕宽高计算位置，而不是使用固定值。

## 参考资源

- [Android官方文档](https://developer.android.com/)
- [Kotlin官方文档](https://kotlinlang.org/docs/home.html)
- [游戏开发模式](https://gameprogrammingpatterns.com/)

---

祝开发顺利！🚀
