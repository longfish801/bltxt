/*
 * BLtxtSpec.groovy
 *
 * Copyright (C) io.github.longfish801 All Rights Reserved.
 */
package io.github.longfish801.bltxt

import groovy.util.logging.Slf4j
import io.github.longfish801.bltxt.BLtxtMsg as msgs
import spock.lang.Specification
import spock.lang.Unroll

/**
 * BLtxtのテスト。
 * @version 0.3.00 2021/12/29
 * @author io.github.longfish801
 */
@Slf4j('LOG')
class BLtxtSpec extends Specification {
	/** ファイル入出力のテスト用フォルダ */
	static final File testDir = new File('src/test/resources/io/github/longfish801/bltxt/BLtxtSpec')
	
	def 'BLtxt - file'(){
		given:
		BLtxt bltxt = null
		
		when:
		bltxt = new BLtxt(new File(testDir, 'target.txt'))
		then:
		bltxt.toString() == 'これがテストです。'
	}
	
	def 'BLtxt - String'(){
		given:
		BLtxt bltxt = null
		
		when:
		bltxt = new BLtxt('これはテストです。')
		then:
		bltxt.toString() == 'これはテストです。'
	}
	
	@Unroll
	def 'parse, toXml'(){
		given:
		Closure convert = { String _dir, String _fname ->
			return new BLtxt(new File(testDir, "${_dir}/${_fname}.txt")).toXml()
		}
		Closure getText = { String _dir, String _fname ->
			return new File(testDir, "${_dir}/${_fname}.xml").getText()
		}
		
		expect:
		convert(dir, fname) == getText(dir, fname)
		
		where:
		dir			| fname
		'sample'	| '01'
		'sample'	| '02'
		'sample'	| '03'
		'sample'	| '04'
		'sample'	| '05'
		'sample'	| '06'
		'sample'	| '07'
		'valiation'	| '01'
		'valiation'	| '02'
		'valiation'	| '03'
	}
	
	def 'parse, toXml - complex'(){
		// Unrollで実行すると OutOfMemoryErrorが発生するため、独立して実施します。
		given:
		String text
		String result
		String expected
		
		when:
		text = new File(testDir, 'complex/blxml.txt').getText()
		result = new BLtxt(text).toXml()
		expected = new File(testDir, 'complex/blxml.xml').getText()
		LOG.debug('result=[{}]', result)
		
		then:
		result == expected
	}
	
	def 'createErrorMessageDetail'(){
		given:
		String detail = """\
			Encountered " <NEWLINE> "\\n "" at line 6, column 2.
			Was expecting one of:
			    <SAFE_TXT> ...
			    <SAFE_TXT> ...
			    <SAFE_TXT> ...
			    <SAFE_TXT> ...
			    <SAFE_TXT> ...
			    <SAFE_TXT> ...
			    -----
			bbb
			ccc
			ddd
			eee
			【
			-----""".stripIndent().replaceAll("\n", System.lineSeparator())
		String expected = String.format(msgs.exc.failedParseSyntax, detail)
		String target = '''\
			aaa
			bbb
			ccc
			ddd
			eee
			【
			fff
			ggg
			hhh
			'''.stripIndent()
		BLtxt bltxt = null
		BLtxtParseException exc = null
		
		when:
		bltxt = new BLtxt(target)
		then:
		exc = thrown(BLtxtParseException)
		exc.message == expected
	}
}
