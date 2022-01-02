/*
 * LeakedReaderSpec.groovy
 *
 * Copyright (C) io.github.longfish801 All Rights Reserved.
 */
package io.github.longfish801.bltxt

import groovy.util.logging.Slf4j
import spock.lang.Specification

/**
 * LeakedReaderのテスト。
 * @version 0.3.00 2021/12/29
 * @author io.github.longfish801
 */
@Slf4j('LOG')
class LeakedReaderSpec extends Specification {
	def 'read'(){
		given:
		String result
		String expected = '''\
			aaa
			bbb
			ccc'''.stripIndent()
		Writer writer = new StringWriter()
		Reader reader = new LeakedReader(new StringReader(expected), writer)
		
		when:
		int character
		List chars = []
		while ((character = reader.read()) != -1){
			chars << character
		}
		result = new String(chars as char[])
		
		then:
		result == expected
		writer.toString() == expected
	}
	
	def 'read - long string'(){
		given:
		String result
		String expected = 'あ' * 10000
		Writer writer = new StringWriter()
		Reader reader = new LeakedReader(new StringReader(expected), writer)
		
		when:
		int character
		List chars = []
		while ((character = reader.read()) != -1){
			chars << character
		}
		result = new String(chars as char[])
		
		then:
		result == expected
		writer.toString() == expected
	}
}
