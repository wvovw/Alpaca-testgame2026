# 美术资源导入指南

## 📁 资源文件夹结构

```
app/src/main/
├── res/
│   ├── drawable/          # 图片资源（PNG、JPG等）
│   ├── drawable-hdpi/     # 高分辨率图片
│   ├── drawable-mdpi/     # 中分辨率图片
│   ├── drawable-xhdpi/    # 超高分辨率图片
│   ├── drawable-xxhdpi/   # 超超高分辨率图片
│   └── drawable-xxxhdpi/  # 超超超高分辨率图片
└── assets/                # 游戏素材文件
    ├── characters/        # 角色精灵图
    ├── enemies/          # 敌人精灵图
    ├── backgrounds/      # 背景图片
    ├── effects/          # 特效动画
    ├── ui/               # UI元素
    └── sounds/           # 音效文件
```

## 🎨 美术资源规范

### 角色资源 (characters/)

#### 喵星人角色
需要的资源文件：

1. **喵星战士**
   - `cat_warrior_idle.png` - 待机动画（推荐尺寸: 128x128px）
   - `cat_warrior_attack.png` - 攻击动画
   - `cat_warrior_move.png` - 移动动画
   - `cat_warrior_skill.png` - 技能动画
   - `cat_warrior_hurt.png` - 受伤动画
   - `cat_warrior_death.png` - 死亡动画

2. **喵星法师**
   - `cat_mage_idle.png`
   - `cat_mage_attack.png`
   - `cat_mage_move.png`
   - `cat_mage_skill.png`
   - `cat_mage_hurt.png`
   - `cat_mage_death.png`

3. **喵星弓手**
   - `cat_archer_idle.png`
   - `cat_archer_attack.png`
   - `cat_archer_move.png`
   - `cat_archer_skill.png`
   - `cat_archer_hurt.png`
   - `cat_archer_death.png`

4. **喵星坦克**
   - `cat_tank_idle.png`
   - `cat_tank_attack.png`
   - `cat_tank_move.png`
   - `cat_tank_skill.png`
   - `cat_tank_hurt.png`
   - `cat_tank_death.png`

### 敌人资源 (enemies/)

1. **狗狗军团**
   - `dog_idle.png` (推荐尺寸: 96x96px)
   - `dog_attack.png`
   - `dog_move.png`

2. **机器人部队**
   - `robot_idle.png` (推荐尺寸: 96x96px)
   - `robot_attack.png`
   - `robot_shoot.png`

3. **Boss**
   - `boss_idle.png` (推荐尺寸: 256x256px)
   - `boss_attack.png`
   - `boss_skill1.png`
   - `boss_skill2.png`
   - `boss_skill3.png`
   - `boss_phase2.png`
   - `boss_phase3.png`

### 背景资源 (backgrounds/)

- `bg_level1.png` - 关卡1背景（推荐尺寸: 1920x1080px）
- `bg_level2.png` - 关卡2背景
- `bg_level3.png` - 关卡3背景
- `bg_boss.png` - Boss战背景
- `bg_menu.png` - 主菜单背景

### UI资源 (ui/)

#### 按钮
- `btn_start.png` - 开始按钮
- `btn_pause.png` - 暂停按钮
- `btn_resume.png` - 继续按钮
- `btn_settings.png` - 设置按钮
- `btn_exit.png` - 退出按钮

#### 技能图标
- `icon_meow_attack.png` - 喵喵攻击图标（64x64px）
- `icon_nine_lives.png` - 九命神功图标
- `icon_catnip_rage.png` - 猫薄荷狂暴图标
- `icon_fish_heal.png` - 鱼肉回复图标

#### 道具图标
- `icon_fish.png` - 小鱼干图标
- `icon_milk.png` - 喵喵奶图标
- `icon_yarn.png` - 毛线球图标
- `icon_bell.png` - 小铃铛图标

#### UI元素
- `health_bar_bg.png` - 血条背景
- `health_bar_fill.png` - 血条填充
- `progress_bar.png` - 进度条
- `dialog_bg.png` - 对话框背景

### 特效资源 (effects/)

- `explosion.png` - 爆炸特效（序列帧）
- `meow_projectile.png` - 喵喵弹幕
- `skill_aura.png` - 技能光环
- `hit_effect.png` - 击中特效
- `heal_effect.png` - 治疗特效
- `level_up.png` - 升级特效

### 音效资源 (sounds/)

#### 音乐
- `bgm_menu.mp3` - 菜单背景音乐
- `bgm_battle.mp3` - 战斗背景音乐
- `bgm_boss.mp3` - Boss战音乐

#### 音效
- `sfx_meow.wav` - 喵叫声
- `sfx_attack.wav` - 攻击音效
- `sfx_hit.wav` - 击中音效
- `sfx_explosion.wav` - 爆炸音效
- `sfx_skill.wav` - 技能释放音效
- `sfx_button.wav` - 按钮点击音效
- `sfx_victory.wav` - 胜利音效
- `sfx_defeat.wav` - 失败音效

## 📋 资源导入步骤

### 方法1: 通过Android Studio导入

1. 打开Android Studio
2. 在项目视图中找到 `app/src/main/res/drawable/`
3. 右键点击文件夹，选择 "Show in Explorer/Finder"
4. 将准备好的PNG图片复制到该文件夹
5. 返回Android Studio，项目会自动识别新资源

### 方法2: 手动复制

1. 导航到项目目录
2. 将图片资源放入对应的文件夹：
   ```
   app/src/main/res/drawable/
   ```
3. 将其他素材（如序列帧动画、音效）放入：
   ```
   app/src/main/assets/
   ```

### 方法3: 使用资源管理工具

在Android Studio中：
1. 右键点击 `res` 文件夹
2. 选择 `New > Image Asset`
3. 使用向导导入和配置图片资源

## 🎯 资源命名规范

### 文件命名规则

- 全部使用小写字母
- 使用下划线分隔单词
- 避免使用特殊字符和空格
- 使用有意义的描述性名称

✅ 正确示例:
```
cat_warrior_attack.png
bg_level1.png
icon_skill_meow.png
```

❌ 错误示例:
```
CatWarrior.png
背景1.png
icon-skill.png
```

## 🎨 图片格式建议

### PNG格式（推荐）
- 支持透明度
- 适合角色、UI元素
- 无损压缩

### JPG格式
- 适合背景图
- 文件较小
- 不支持透明度

### 分辨率建议

| 资源类型 | 推荐尺寸 |
|---------|---------|
| 角色精灵 | 128x128px - 256x256px |
| 敌人精灵 | 96x96px - 128x128px |
| Boss | 256x256px - 512x512px |
| UI图标 | 64x64px - 128x128px |
| 背景 | 1920x1080px（横屏）|
| 特效 | 128x128px - 256x256px |

## 💾 资源优化建议

1. **压缩图片**: 使用TinyPNG或ImageOptim压缩图片
2. **使用适当分辨率**: 不要使用过大的图片
3. **序列帧优化**: 使用精灵表（Sprite Sheet）代替多个单独文件
4. **音频压缩**: 音效使用WAV，音乐使用MP3或OGG

## 🔍 资源引用方式

### 在Kotlin代码中引用

```kotlin
// 引用drawable资源
val catSprite = R.drawable.cat_warrior_idle

// 加载图片
val bitmap = BitmapFactory.decodeResource(resources, R.drawable.cat_warrior_idle)

// 引用assets资源
val inputStream = assets.open("characters/cat_warrior_idle.png")
```

### 在XML中引用

```xml
<ImageView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:src="@drawable/cat_warrior_idle" />
```

## 📞 联系和支持

如果在资源导入过程中遇到问题，请查看：
- Android官方文档: https://developer.android.com/guide/topics/resources
- 项目Wiki
- 提交Issue到项目仓库

---

**注意**: 所有美术资源应确保拥有使用权限，避免版权问题。建议使用原创资源或获得授权的资源。
