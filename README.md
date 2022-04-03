# bltxt

## Overview

Structuring text.  
You can describe text having hierarchical structure with original form of tags.

This is individual development, for self-learning.  
No support such as troubleshooting, answering inquiries, and so on.

## Features

* The concept is similar to XML and HTML.  
  It is easy to input than XML or HTML that uses half-width symbols, because using the full-width symbol tag.

* Use full-width llack lenticular bracket (【, 】) to describe the tag.  
  The tags are separated by a full-width colon to specify the attributes.

* You can convert it to XML format.

The name of this library comes from using tags with Black Lenticular bracket.

## Sample Code

Here is a sample script that converting text to bltxt document (src/test/groovy/Sample.groovy).

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

Here is a target text (src/test/resources/target.txt).

```
【＃タイトル】bltxt記法について

　テキストをXMLのような階層のある構造へ変換するための記法です。
　タグ付けのために隅付き括弧（【＿、＿】）を使います。

　以下は文範囲タグのサンプルです。

【－囲み記事】
　海の日、山の日に続いて【｜強調】空の日【強調｜】という祝日はどうだろう。
【囲み記事－】
```

Converting text to bltxt document, and here is a result of output in XML format (src/test/resources/target.md).

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

This sample code is executed in the execSamples task, see build.gradle.

## Next Step

Please see the [documents](https://longfish801.github.io/maven/bltxt/) for more detail.

