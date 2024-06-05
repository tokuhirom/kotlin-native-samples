# ScreenCaptureKit を使ってみる

ScreenCaptureKit は、macOS 12.3 以降で使える API です。
https://developer.apple.com/documentation/screencapturekit

特定のウィンドウの音声や画像をキャプチャすることができます。めっちゃ便利です。

## 設定

`build.gradle.kts` に以下のように追記します。

<<< ./build.gradle.kts{kotlin}

## 実装

<<< ./src/nativeMain/kotlin/Main.kt{kotlin}

Window, Application, Display のリストを出力するとともに、ウィンドウのスクショをとってファイルに保存するプログラムです。
このようなプログラムを作るのは、通常は難しいですが、、使い慣れた kotlin でさらさら書けるので、めっちゃ便利です。

chatgpt が Apple の ScreenCaptureKit のドキュメントと swift to kotlin native の変換方法を知っているので、
ほとんどこのコードでやりたいことは、chatgpt に書いてもらいました。

通常、Mac の API はドキュメントが少なくて大変なのですが、良い時代になったものです。
