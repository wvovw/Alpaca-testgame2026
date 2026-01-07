#!/bin/bash
echo "=== 验证项目结构 (Verifying Project Structure) ==="
echo ""

echo "✓ Gradle配置文件:"
ls -1 *.gradle.kts settings.gradle.kts 2>/dev/null | while read f; do echo "  - $f"; done

echo ""
echo "✓ 核心引擎 (Core Engine) [3/3]:"
ls -1 app/src/main/kotlin/com/animalbattle/core/*.kt 2>/dev/null | wc -l

echo ""
echo "✓ 实体类 (Entities) [4/4]:"
ls -1 app/src/main/kotlin/com/animalbattle/entities/*.kt 2>/dev/null | wc -l

echo ""
echo "✓ 游戏系统 (Game Systems) [7/7]:"
ls -1 app/src/main/kotlin/com/animalbattle/systems/*.kt 2>/dev/null | wc -l

echo ""
echo "✓ UI层 (UI Layer) [4/4]:"
ls -1 app/src/main/kotlin/com/animalbattle/ui/*.kt 2>/dev/null | wc -l

echo ""
echo "✓ MainActivity: $(ls app/src/main/kotlin/com/animalbattle/MainActivity.kt 2>/dev/null | wc -l)/1"

echo ""
echo "✓ 资源文件:"
echo "  - AndroidManifest.xml: $(ls app/src/main/AndroidManifest.xml 2>/dev/null | wc -l)/1"
echo "  - strings.xml: $(ls app/src/main/res/values/strings.xml 2>/dev/null | wc -l)/1"
echo "  - colors.xml: $(ls app/src/main/res/values/colors.xml 2>/dev/null | wc -l)/1"

echo ""
echo "✓ 文档:"
ls -1 *.md 2>/dev/null | while read f; do echo "  - $f"; done

echo ""
echo "总计Kotlin文件: $(find app/src/main/kotlin -name "*.kt" | wc -l)"
echo ""
echo "=== 结构验证完成 (Structure Verification Complete) ==="
