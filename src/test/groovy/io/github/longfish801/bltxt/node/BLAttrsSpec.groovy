/*
 * BLAttrsSpec.groovy
 *
 * Copyright (C) io.github.longfish801 All Rights Reserved.
 */
package io.github.longfish801.bltxt.node

import groovy.util.logging.Slf4j
import spock.lang.Specification

/**
 * BLAttrsのテスト。
 * @version 0.3.00 2021/12/29
 * @author io.github.longfish801
 */
@Slf4j('LOG')
class BLAttrsSpec extends Specification {
	def 'escape'(){
		given:
		BLAttrs<String> attrs
		
		when:
		attrs = new BLAttrs<String>()
		attrs << '【ここから'
		attrs << '：そして：'
		attrs << '＿'
		attrs << 'ここまで】'
		
		then:
		attrs.toString() == '：【＿ここから：＿：そして＿：：＿＿：ここまで＿】'
	}
}
