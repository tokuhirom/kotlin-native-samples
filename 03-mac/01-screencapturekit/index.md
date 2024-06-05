# echo server を書いてみる

## 設定

`build.gradle.kts` に以下のように追記します。

<<< ./build.gradle.kts{kotlin}

## 実装

<<< ./src/nativeMain/kotlin/Main.kt{kotlin}

ちょっと import が多いので、ダルい感じになっているが、ほぼ C で書いたときのコードと同じです。
C で書いた場合に比べると、IDEA で書けるから書きやすい。ぐらいでそんなに差はないですね。
