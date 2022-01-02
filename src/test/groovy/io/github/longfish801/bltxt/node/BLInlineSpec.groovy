/*
 * BLInlineSpec.groovy
 *
 * Copyright (C) io.github.longfish801 All Rights Reserved.
 */
package io.github.longfish801.bltxt.node

import groovy.util.logging.Slf4j
import spock.lang.Specification

/**
 * BLInlineのテスト。
 * @version 0.3.00 2021/12/29
 * @author io.github.longfish801
 */
@Slf4j('LOG')
class BLInlineSpec extends Specification {
	def 'toString'(){
		given:
		BLInline inline
		
		when:
		inline = new BLInline('タグ', 1)
		then:
		inline.toString() == '【タグ】'
		
		when:
		inline = new BLInline('タグ', 1)
		inline.attrs << '属性'
		then:
		inline.toString() == '【タグ：属性】'
		
		when:
		inline = new BLInline('タグ', 1)
		inline << new BLText('テキスト', 1)
		then:
		inline.toString() == '【｜タグ】テキスト【タグ｜】'
	}
}
