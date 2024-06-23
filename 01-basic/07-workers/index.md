# Threads

kotlin native でスレッドを使う方法を説明します。
Kotlin native では、スレッドを扱うには Worker API を使います。

ただし、Worker API を実行すると以下のような警告が出ます。

```
w: file:///Users/tokuhirom/work/kotlin-native-samples/01-basic/07-threads/src/commonMain/kotlin/Main.kt:11:33 Workers API is obsolete and will be replaced with threads eventually
```

worker API は、設計に問題があるため将来的に threads に置き換えられる予定です。ただし、現在のところ threads は実装されていません。

> Workers continue being supported and maintained, but they eventually will be replaced with threads API and then deprecated.
> ワーカーは引き続きサポートおよびメンテナンスされますが、最終的にはスレッド API に置き換えられ、その後は非推奨になります。
> https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.native.concurrent/-obsolete-workers-api/

つまり、現状では Worker API を使うしかありません。

もし、ライブラリなどを作る場合は、 Worker API を直接露出させるのでは無く、ラッパーを作ることをお勧めします。

また、可能ならスレッドを直接使うのではなく、コルーチンを使うようにしたほうが良いかもですね。

## 設定

`build.gradle.kts` に以下のように追記します。

<<< ./build.gradle.kts{kotlin}

## 実装

<<< ./src/commonMain/kotlin/Main.kt{kotlin}

