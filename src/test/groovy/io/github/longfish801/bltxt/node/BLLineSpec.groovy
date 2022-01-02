/*
 * BLLineSpec.groovy
 *
 * Copyright (C) io.github.longfish801 All Rights Reserved.
 */
package io.github.longfish801.bltxt.node

import groovy.util.logging.Slf4j
import spock.lang.Specification

/**
 * BLLineのテスト。
 * @version 0.3.00 2021/12/29
 * @author io.github.longfish801
 */
@Slf4j('LOG')
class BLLineSpec extends Specification {
	def 'getAttrs'(){
		given:
		BLLine line
		
		when:
		line = new BLLine(1)
		then:
		line.attrs == null
	}
	
	def 'toString'(){
		given:
		BLLine line
		
		when:
		line = new BLLine(1)
		line << new BLText('テキスト１', 1)
		line << new BLText('テキスト２', 1)
		then:
		line.toString() == 'テキスト１テキスト２'
	}
}
