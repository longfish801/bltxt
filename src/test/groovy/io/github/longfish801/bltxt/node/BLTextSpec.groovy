/*
 * BLTextSpec.groovy
 *
 * Copyright (C) io.github.longfish801 All Rights Reserved.
 */
package io.github.longfish801.bltxt.node

import groovy.util.logging.Slf4j
import spock.lang.Specification

/**
 * BLTextのテスト。
 * @author io.github.longfish801
 */
@Slf4j('LOG')
class BLTextSpec extends Specification {
	def 'getAttrs'(){
		given:
		BLText text
		
		when:
		text = new BLText('テスト', 1)
		then:
		text.attrs == null
	}
	
	def 'getNodes'(){
		given:
		BLText text
		
		when:
		text = new BLText('テスト', 1)
		then:
		text.nodes == null
	}
	
	def 'leftShift'(){
		given:
		BLText text
		
		when:
		text = new BLText('テスト', 1)
		text << new BLText('テスト２', 1)
		then:
		thrown(UnsupportedOperationException)
	}
	
	def 'escape'(){
		given:
		BLText text
		
		when:
		text = new BLText('【ここから：そして：ここまで】', 1)
		then:
		text.toString() == '【＿ここから：そして：ここまで＿】'
	}
}
