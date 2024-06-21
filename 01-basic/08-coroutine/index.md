# Coroutine

Kotlin Multiplatform で Coroutine は普通に使えます。

## 設定

`build.gradle.kts` に以下のように追記します。

<<< ./build.gradle.kts{kotlin}

## 実装

コールバックスタイルの API を coroutine で使うようにラッピングする例です。
Kotlin/JVM で coroutine を使う場合、coroutine 用にだいたいラッピングされているんですが、
Kotlin Native の場合は、OS のコールバックスタイルのAPIを coroutine で使うためにラッピングする必要があります。

<<< ./src/commonMain/kotlin/Main.kt{kotlin}
