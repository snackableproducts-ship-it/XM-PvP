# XM PvP

Standalone Fabric client project for Minecraft 1.21.11.

## Included in this first source milestone
- Fabric client entrypoint
- Module registry and lifecycle
- Right Shift ClickGUI
- Dark premium panel styling with purple accents
- Toggleable module rows
- Current chunk coordinate tracking foundation
- Practice-stat module foundation

## Build
1. Install JDK 21.
2. Open the repository in IntelliJ IDEA or another Gradle IDE.
3. Run `gradle build` (or use the Gradle task runner).
4. The mod JAR will be generated in `build/libs/`.

## Scope
This is an initial source scaffold, not a tested release. It currently focuses on client-side UI, module structure, chunk information, and practice utilities. It does not include anti-cheat evasion, hidden-chunk scanning, or automated combat.
