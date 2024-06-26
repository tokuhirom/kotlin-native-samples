# Mac の tray アプリを作る

Mac の tray アプリを作るためには、以下の手順が必要です。

## 設定

`build.gradle.kts` に以下のように追記します。

<<< ./build.gradle.kts{kotlin}

## 実装

<<< ./src/nativeMain/kotlin/Main.kt{kotlin}

## 注意点

通常の kotlin native アプリケーションと同様に `./gradlew runDebugExecutableNative` とすると起動しない。

```shell
../../gradlew nativeBinaries
./build/bin/native/debugExecutable/03-trayicon.kexe
```

とすると、メニューに出る。

NSStatusItem と NSApplicationDelegateProtocol が解放されるとメニューアイテムがすっと消えるという謎挙動に悩まされることになる。
なので、解放されない場所に保持しておく必要があることに注意してください。

## 発展

- notification を送るようにすることも可能だが、その場合は .app 形式にする必要がある。
- dialog を表示させることも可能なのだが、なぜか出るときと出ないときがあって謎。。
- (このへんは、mac 自体のデバッグ力がもう少したかまらないとキツいかも)
