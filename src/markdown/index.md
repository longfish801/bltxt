# bltxt

[TOC levels=2-6]

## 概要

　テキストを構造化します。
　独自の形式のタグで階層的な構造があるテキストを表現できます。

　個人が学習のために開発したものです。
　故障対応や問合せ回答などのサポートはしていません。

## 特徴

* XMLやHTMLと考え方は同じです。
  タグを全角記号で記述するため、半角記号を用いるXMLやHTMLより入力が容易です。
* 全角隅付き括弧（【、】）でタグを記述します。
  タグには全角コロン（：）区切りで属性を指定します。
* XML形式に変換できます。

　このライブラリの名称はタグに隅付き括弧(Black Lenticular bracket)を用いることに由来しています。

## サンプルコード

　以下にテキストをbltxt文書に変換するスクリプトのサンプルを示します（src/test/groovy/Sample.groovy）。

```
import io.github.longfish801.bltxt.BLtxt

try {
	BLtxt bltxt = new BLtxt(new File('src/test/resources/target.txt'))
	assert bltxt.toXml() == new File('src/test/resources/result.xml').getText('UTF-8')
} catch (exc){
	println "Failed to convert: ${exc.message}"
	throw exc
}
```

　以下は変換対象のテキストです（src/test/resources/target.txt）。

```
【＃タイトル】bltxt記法について

　テキストをXMLのような階層のある構造へ変換するための記法です。
　タグ付けのために隅付き括弧（【＿、＿】）を使います。

　以下は文範囲タグのサンプルです。

【－囲み記事】
　海の日、山の日に続いて【｜強調】空の日【強調｜】という祝日はどうだろう。
【囲み記事－】
```

　テキストをbltxt文書に変換し、XML形式で出力した結果です（src/test/resources/result.xml）。

```
<?xml version="1.0" encoding="UTF-8"?>
<bltxt>
  <meta tag="タイトル" lnum="2" snum="1">
    <para lnum="2" snum="1">
      <line lnum="2">
        <text>bltxt記法について</text>
      </line>
    </para>
  </meta>
  <para lnum="4" snum="2">
    <line lnum="4">
      <text>　テキストをXMLのような階層のある構造へ変換するための記法です。</text>
    </line>
    <line lnum="5">
      <text>　タグ付けのために隅付き括弧（【、】）を使います。</text>
    </line>
  </para>
  <para lnum="7" snum="3">
    <line lnum="7">
      <text>　以下は文範囲タグのサンプルです。</text>
    </line>
  </para>
  <block tag="囲み記事" lnum="9" snum="1">
    <para lnum="9" snum="4">
      <line lnum="10">
        <text>　海の日、山の日に続いて</text>
        <inline tag="強調" lnum="10" snum="1">
          <text>空の日</text>
        </inline>
        <text>という祝日はどうだろう。</text>
      </line>
    </para>
  </block>
</bltxt>
```

　このサンプルコードは build.gradle内の execSamplesタスクで実行しています。

## ドキュメント

* [Groovydoc](groovydoc/)
* [bltxt記法](notation.html)

## GitHubリポジトリ

* [bltxt](https://github.com/longfish801/bltxt)

## Mavenリポジトリ

　本ライブラリの JARファイルを [GitHub上の Mavenリポジトリ](https://github.com/longfish801/maven)で公開しています。
　build.gradleの記述例を以下に示します。

```
repositories {
	mavenCentral()
	maven { url 'https://longfish801.github.io/maven/' }
}

dependencies {
	implementation group: 'io.github.longfish801', name: 'bltxt', version: '0.3.00'
}
```

## 改版履歴

0.2.01
: ドキュメントはmavenリポジトリに出力するよう修正しました。

0.2.02
: BLtxtクラスのgrepNodesメソッドについて引数に対応する値がないときの戻り値を変更しました。
