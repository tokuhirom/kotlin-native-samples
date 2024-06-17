[# CLI を書いてみる

kotlin 公式が出している kotlinx.cli がありましたが、今はもうメンテナンスされていません。

[clikt](https://ajalt.github.io/clikt/) を使うことにします。
clikt はサブコマンドにも対応しており、機能的には十分と思います。

## 設定

`build.gradle.kts` に以下のように追記します。

<<< ./build.gradle.kts{kotlin}

## 実装

<<< ./src/commonMain/kotlin/Main.kt{kotlin}

非常に簡単に使えるとともに、一通りのパースができるので機能的には十分と思います。

自動的に `--help` オプションも設定されます。

```
Usage: tool [<options>] <arg>

Options:
  --opt=<text>  an option
  -h, --help    Show this message and exit

Arguments:
  <arg>  an argument
```
