# 勉強会 2015/02/15
## 概要
ペアプロの練習用に作ったレポジトリです。  
言語はJavaです。

## ビルドの仕方
Eclipseで実行する場合は、プロジェクトのルート・ディレクトリで

```
gradle wrapper
./gradlew eclipse
```

を実行し、Eclipseのプロジェクトファイルを作成して下さい。

その後、Eclipseを起動してgitパースペクティブから本ディレクトリをgitレポジトリとして追加 → 「Working Directory」を右クリックして「Import Projects」を実行して下さい。

おそらくですが、「study20150215」というプロジェクトが選択できるはずです。

なお

```
./gradlew compileJava
```

でコンパイル

```
./gradlew war 
```

で　WARファイル作成となります。