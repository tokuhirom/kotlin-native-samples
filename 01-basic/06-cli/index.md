[# CLI を書いてみる

kotlin 公式が出している kotlinx.cli を使うと、CLI ツールを簡単に書くことができます。
subcommand にも対応しているので、これを使うのが基本となってくると思います。

## 設定

`build.gradle.kts` に以下のように追記します。

<<< ./build.gradle.kts{kotlin}

## 実装

<<< ./src/nativeMain/kotlin/Main.kt{kotlin}

非常に簡単に使えるとともに、一通りのパースができるので機能的には十分と思います。

自動的に `--help` オプションも設定されます。

```
Usage: example options_list
Options: 
    --input, -i -> Input file (always required) { String }
    --help, -h -> Usage info 
```

